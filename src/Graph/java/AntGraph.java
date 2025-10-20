package Graph.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AntGraph extends GraphInputGenerator {
    private final Image antIcon = new ImageIcon("A:/NLCSnganh/nest1.png").getImage();
    private final Image criticalAntIcon = new ImageIcon("A:/NLCSnganh/DoiQuanKien.png").getImage();
    private int draggedNode = -1;
    private int hoveredNode = -1; // Node được hover
    private boolean isLocked = false; // Cờ khóa giao diện
    private boolean isProcessing = false; // Đánh dấu quá trình xử lý đang diễn ra (khớp đồ thị)

    public AntGraph() {
        // Xử lý click + kéo
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isLocked || isProcessing) return; // Nếu đang xử lý thì không cho phép thao tác

                for (int i = 0; i < V; i++) {
                    if (positions[i].distance(e.getPoint()) <= 30) { // Kiểm tra xem có nhấn vào đỉnh nào không
                        draggedNode = i; // Cho phép kéo bất kỳ tổ nào
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Nếu đang kéo thì thả
                draggedNode = -1;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredNode = -1; // Reset hoveredNode khi chuột rời khỏi
                repaint();
            }
        });

        // Lắng nghe sự kiện kéo node và hover
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Nếu giao diện bị khóa hoặc đang trong quá trình xử lý thì không cho phép kéo
                if (isLocked || isProcessing || draggedNode == -1) return;

                // Cập nhật vị trí của node đang được kéo
                positions[draggedNode] = e.getPoint();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Kiểm tra xem chuột có hover vào tổ kiến nào không
                int prevHovered = hoveredNode;
                hoveredNode = -1;
                for (int i = 0; i < V; i++) {
                    if (positions[i].distance(e.getPoint()) <= 30) { // Kiểm tra hover vào vị trí node
                        hoveredNode = i;
                        break;
                    }
                }
                // Chỉ repaint khi trạng thái hover thay đổi để tránh nháy
                if (hoveredNode != prevHovered) {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        // Vẽ đường nối kiểu "kiến bò"
        Graphics2D g2 = (Graphics2D) g;
        float[] dash = {5, 10};
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, dash, 0));
        for (int[] edge : edges) {
            g2.drawLine(positions[edge[0]].x, positions[edge[0]].y, positions[edge[1]].x, positions[edge[1]].y);
        }

        // Kiểm tra còn đỉnh khớp nào không
        boolean hasCritical = false;

        // Vẽ từng tổ kiến
        for (int i = 0; i < V; i++) {
            Image icon = isArticulationPoint(i) ? criticalAntIcon : antIcon;
            if (isArticulationPoint(i)) hasCritical = true;

            // Điều chỉnh kích thước khi hover
            int size = (i == hoveredNode) ? 66 : 60;
            int x = positions[i].x - size / 2;
            int y = positions[i].y - size / 2;

            g.drawImage(icon, x, y, size, size, this);

            // Thêm hiệu ứng viền sáng khi hover
            if (i == hoveredNode) {
                g.setColor(new Color(255, 255, 0, 150));  // Màu vàng mờ
                g2.setStroke(new BasicStroke(3));
                g.drawOval(x, y, size, size);
            }
        }

        // Kiểm tra nếu không còn đỉnh khớp nào và chưa khóa giao diện
        if (!hasCritical && !isLocked && !isProcessing) {
            isLocked = true;  
        }
    }

    private boolean isArticulationPoint(int node) {
        return articulationPoints[node];  // Kiểm tra node có phải là điểm khớp không
    }

    // Hàm để bắt đầu quá trình duyệt khớp
    public void startProcessing() {
        isProcessing = true;
        isLocked = true;  // Khóa giao diện trong quá trình duyệt khớp
        repaint();
    }

    // Hàm để kết thúc quá trình duyệt khớp
    public void endProcessing() {
        isProcessing = false;
        isLocked = false;  // Mở khóa giao diện khi quá trình duyệt khớp kết thúc
        repaint();
    }
}
