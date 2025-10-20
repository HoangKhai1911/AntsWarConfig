package Graph.java;

import javax.swing.*;
import java.awt.*;

public class AntGraphGUI extends JFrame {
    private AntGraph graphGen;
    private JLayeredPane layeredPane;
    private JPanel controlPanel;

    public AntGraphGUI() {
        // Khởi tạo đồ thị
        graphGen = new AntGraph();

        // Tạo giao diện
        setupGUI();
    }
    
    public void userInput() {
        // Delay nhẹ để đảm bảo frame đã hiện lên rồi mới mở dialog
        SwingUtilities.invokeLater(() -> {
            graphGen.userInputGraphGUI(); 
        });
        setVisible(true);
    }


    public void autoGenerate() {
        graphGen.autoGenerateTree();
        setVisible(true);
    }


    private void setupGUI() {
        setTitle("Ant's War Ragnarok");

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Hình nền
        String imagePath = "A:/NLCSnganh/back.jpg";
        ImageIcon backgroundIcon = new ImageIcon(imagePath);
        JLabel backgroundLabel = new JLabel(
                new ImageIcon(backgroundIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 800, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Bọc graphGen vào JScrollPane
        graphGen.setPreferredSize(new Dimension(1200, 1000)); // Kích thước đủ lớn để có thể cuộn
        graphGen.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(graphGen);
        scrollPane.setBounds(0, 0, 800, 600);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); // Xóa viền nếu muốn
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        layeredPane.add(scrollPane, Integer.valueOf(1));

        // Panel điều khiển
        controlPanel = new JPanel();
        controlPanel.setOpaque(false);
        JButton resetButton = new JButton("Reset");
        resetButton.setToolTipText("Reset đồ thị");
        resetButton.addActionListener(e -> graphGen.autoGenerateTree());
        controlPanel.add(resetButton);

        add(layeredPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
