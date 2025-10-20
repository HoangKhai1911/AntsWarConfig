package Graph.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphInputGenerator extends JPanel {
    protected int V;
    protected ArrayList<int[]> edges;
    protected Point[] positions;
    protected boolean[] articulationPoints;
    private boolean warnedEnemyOccupied = false;
    private boolean warnedArticulationReady = false;
    protected boolean isProcessing = false; // Trạng thái xử lý duyệt khớp

    public GraphInputGenerator() {
        this.edges = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleNodeClick(e.getPoint());
            }
        });
    }

    public void userInputGraphGUI() {
        JTextField vertexField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Số đỉnh (3 - 35):"));
        panel.add(vertexField);

        SwingUtilities.invokeLater(() -> vertexField.requestFocusInWindow());

        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin đồ thị", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int input = Integer.parseInt(vertexField.getText());

                if (input <= 2 || input >= 36) {
                    throw new IllegalArgumentException("Số đỉnh phải từ 3 - 35.");
                }

                V = input;
                edges.clear();
                autoGenerateTree();
                repaint();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                userInputGraphGUI();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                userInputGraphGUI();
            }
        } else {
            MenuGUI menu = new MenuGUI();
            menu.show();
        }
    }

    public void autoGenerateTree() {
        // Reset cờ
        warnedEnemyOccupied = false;
        warnedArticulationReady = false;

        // Sinh ngẫu nhiên cây
        Random rand = new Random();
        V = (V == 0) ? rand.nextInt(8) + 14 : V;
        edges.clear();
        for (int i = 1; i < V; i++) {
            int parent = rand.nextInt(i);
            edges.add(new int[]{ parent, i });
        }

        // Trước khi vẽ đồ thị, hiển thị thông báo ở giữa frame
        Window parent = SwingUtilities.getWindowAncestor(this);

        // Đảm bảo thông báo được hiển thị trước khi vẽ đồ thị
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(
                    parent,
                    "<html><div style='text-align: center;'>⚠️ Kẻ địch đã chiếm đóng thành phố!</div></html>",
                    "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE
                );

                // Tính vị trí rồi cho Swing queue vẽ lại ngay
                generateTreePositions();
                repaint();

                // Sau khi panel đã vẽ xong (~500ms), mới bắt đầu duyệt khớp
                Timer t = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Timer)e.getSource()).stop();
                        // Bắt đầu duyệt khớp
                        findArticulationPointsAsync();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });
    }


    private void generateTreePositions() {
        positions = new Point[V];
        int frameWidth = 800;
        int frameHeight = 600;
        int levelGap = 100;

        Map<Integer, List<Integer>> treeLevels = new HashMap<>();
        treeLevels.put(0, new ArrayList<>(Arrays.asList(0)));

        for (int[] edge : edges) {
            int child = edge[1];
            int level = (int) (Math.log(child + 1) / Math.log(2));
            treeLevels.putIfAbsent(level, new ArrayList<>());
            treeLevels.get(level).add(child);
        }

        int totalHeight = treeLevels.size() * levelGap;
        int startY = (frameHeight - totalHeight) / 2;

        for (Map.Entry<Integer, List<Integer>> entry : treeLevels.entrySet()) {
            int level = entry.getKey();
            List<Integer> nodes = entry.getValue();

            int siblingGap = Math.max(50, frameWidth / (nodes.size() + 1));
            int totalWidth = siblingGap * (nodes.size() - 1);
            int startX = (frameWidth - totalWidth) / 2;

            for (int i = 0; i < nodes.size(); i++) {
                positions[nodes.get(i)] = new Point(startX + i * siblingGap, startY + level * levelGap);
            }
        }
    }

    private void findArticulationPointsAsync() {
        isProcessing = true; // 👉 Bắt đầu duyệt, chặn thao tác

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                articulationPoints = new boolean[V];
                int[] disc = new int[V];
                int[] low = new int[V];
                boolean[] visited = new boolean[V];
                int[] time = {0};

                List<List<Integer>> adj = new ArrayList<>();
                for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
                for (int[] edge : edges) {
                    adj.get(edge[0]).add(edge[1]);
                    adj.get(edge[1]).add(edge[0]);
                }

                for (int i = 0; i < V; i++) {
                    if (!visited[i]) {
                        dfsAnimated(i, -1, visited, disc, low, time, adj);
                    }
                }

                return null;
            }

            private void dfsAnimated(int u, int parent, boolean[] visited, int[] disc, int[] low, int[] time, List<List<Integer>> adj) {
                visited[u] = true;
                disc[u] = low[u] = ++time[0];
                int children = 0;

                for (int v : adj.get(u)) {
                    if (v == parent) continue;

                    if (!visited[v]) {
                        children++;
                        dfsAnimated(v, u, visited, disc, low, time, adj);
                        low[u] = Math.min(low[u], low[v]);

                        boolean isArticulation = false;
                        if (parent == -1 && children > 1) isArticulation = true;
                        if (parent != -1 && low[v] >= disc[u]) isArticulation = true;

                        if (isArticulation) {
                            articulationPoints[u] = true;
                            publish(u);
                            try {
                                Thread.sleep(500); // Hiệu ứng
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        low[u] = Math.min(low[u], disc[v]);
                    }
                }
            }

            @Override
            protected void process(List<Integer> chunks) {
                repaint();
            }

            @Override
            protected void done() {
                isProcessing = false; // 👉 Kết thúc duyệt, cho thao tác lại

                if (!warnedArticulationReady) {
                    warnedArticulationReady = true;
                    JOptionPane.showMessageDialog(null,
                            "🛡️ Toàn bộ Phiến Quân Kiến phản đã lộ diện!\nNgười chơi, hãy chiến đấu và bảo vệ Vương Quốc Kiến!");
                }
            }
        };

        worker.execute();
    }

    protected void handleNodeClick(Point click) {
        if (isProcessing) {
            JOptionPane.showMessageDialog(null,
                "⛔ Kiến Phản Động đang xâm chiếm bản đồ!\nVui lòng chờ hoàn tất trước khi thao tác.");
            return;
        }

        for (int i = 0; i < V; i++) {
            if (positions[i].distance(click) <= 30) {
                if (articulationPoints[i]) {
                    removeNode(i);
                    JOptionPane.showMessageDialog(null, "✅ Đã tiêu diệt Phiến Quân Kiến " + i);

                    if (noArticulationPointsLeft()) {
                        JOptionPane.showMessageDialog(null,
                            "🎉 Cuộc chiến đã kết thúc! Không còn tổ địch nào nữa.");
                        String[] options = {"Quay về Menu", "Chơi lại"};
                        int choice = JOptionPane.showOptionDialog(null,
                            "Bạn muốn làm gì tiếp theo?", "Chiến thắng!",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0]);

                        if (choice == 0) {
                            MenuGUI menu = new MenuGUI();
                            menu.show();
                        } else if (choice == 1) {
                            autoGenerateTree();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                        "🚫 Tổ " + i + " không phải là Tổ Kiến Phản Động!",
                        "Cảnh báo!", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        }
    }

    private boolean noArticulationPointsLeft() {
        for (boolean ap : articulationPoints) {
            if (ap) return false;
        }
        return true;
    }

    private void removeNode(int node) {
        edges.removeIf(edge -> edge[0] == node || edge[1] == node);
        findArticulationPointsAsync();
    }
}
