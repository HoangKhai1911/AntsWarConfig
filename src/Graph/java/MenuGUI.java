package Graph.java;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//import Graph.java.RoundedButton;

public class MenuGUI {
	public void show() {
		JFrame chooseFrame = new JFrame("Chiến Tranh Loài Kiến");
		chooseFrame.setSize(800, 600);
		chooseFrame.setLocationRelativeTo(null);
		chooseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon bgIcon = new ImageIcon("A:/NLCSnganh/ChatGPT Image 22_23_39 21 thg 4, 2025.png");
		Image scaledImage = bgIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(scaledImage));
		background.setLayout(new BorderLayout());

		// Tiêu đề
		Color redOrange = new Color(255, 40, 0);
		JLabel titleLabel = new JLabel("The War of Ants Empire!", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Old English Text MT", Font.BOLD, 60));
		titleLabel.setForeground(redOrange);
		titleLabel.setOpaque(false);

		// Viền chữ bằng hiệu ứng đổ bóng
		titleLabel.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
			protected void paintSafely(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g2.setFont(titleLabel.getFont());

				// Đổ bóng (viền chữ)
				g2.setColor(Color.BLACK);
				g2.drawString(titleLabel.getText(), titleLabel.getInsets().left + 1,
						titleLabel.getFont().getSize() + 1);

				// Chữ chính
				g2.setColor(titleLabel.getForeground());
				g2.drawString(titleLabel.getText(), titleLabel.getInsets().left, titleLabel.getFont().getSize());

				g2.dispose();
			}
		});

		background.add(titleLabel, BorderLayout.NORTH);

		// Panel chứa nút
		JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 280, 100, 280)); // Thu gọn

		// Nút custom
		RoundedButton btnInput = new RoundedButton("Nhập số đỉnh");
		btnInput.addActionListener(e -> {
			chooseFrame.dispose();
			AntGraphGUI gui = new AntGraphGUI();
			gui.userInput();
		});

		RoundedButton btnAuto = new RoundedButton("Tự động sinh");
		btnAuto.addActionListener(e -> {
			chooseFrame.dispose();
			AntGraphGUI gui = new AntGraphGUI();
			gui.autoGenerate();
		});

		RoundedButton btnHelp = new RoundedButton("Hướng dẫn");
		btnHelp.addActionListener(e -> {
			JOptionPane.showMessageDialog(chooseFrame,
					"Chọn 'Nhập số đỉnh' để--- tự nhập đồ thị.\nChọn 'Tự động sinh' để hệ thống tự tạo đồ thị.\nLuật chơi:\n Một đế chế kiến đang "
					+ "rất hỗn loạn với rất nhiều 'Phiến Quân' và các 'Tổ Kiến' thường.\n"
					+ " Các cư dân kiến sống bên trong tổ đang đối mặt với sự diệt vong do các 'Phiến Quân' có chiến lực rất mạnh và hung hãn đàn áp các chế độ dân chủ.\n"
					+ " Nhiệm vụ của bạn là tiêu diệt toàn bộ 'Phiến Quân' để hoàn thành trò chơi. Cùng nhau 'BẢO VỆ HÒA BÌNH, GIẢI CỨU LOÀI KIẾN' nào!!!!",
					"Hướng dẫn", JOptionPane.INFORMATION_MESSAGE);
		});

		buttonPanel.add(btnInput);
		buttonPanel.add(btnAuto);
		buttonPanel.add(btnHelp);

		background.add(buttonPanel, BorderLayout.CENTER);
		chooseFrame.setContentPane(background);
		chooseFrame.setVisible(true);
	}
}
