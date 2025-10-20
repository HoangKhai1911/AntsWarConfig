Skip to content
Navigation Menu
HoangKhai1911
AntsWarConfig

Type / to search
Code
Issues
Pull requests
Actions
Projects
Wiki
Security
Insights
Settings
AntsWarConfig/
Go to file
t
HoangKhai1911
HoangKhai1911
Initialize README with project details and instructions
c8a318d
 · 
1 minute ago
AntsWarConfig/
Name	Last commit message	Last commit date
src/Graph/java
Ant war commit
39 minutes ago
.gitignore
Add .gitignore and clean up ignored files
14 minutes ago
AntHill.jpg
Ant war commit
39 minutes ago
AntHill.webp
Ant war commit
39 minutes ago
AntNest.jpg
Ant war commit
39 minutes ago
AntNest.webp
Ant war commit
39 minutes ago
Black-Red-Ants.png
Ant war commit
39 minutes ago
Black-Red-Ants.webp
Ant war commit
39 minutes ago
ChatGPT Image 22_23_39 21 thg 4, 2025.png
Ant war commit
39 minutes ago
DinhKhop.png
Ant war commit
39 minutes ago
DinhKhop.webp
Ant war commit
39 minutes ago
DoiQuanKien.png
Ant war commit
39 minutes ago
NutThuong.png
Ant war commit
39 minutes ago
NutThuong.webp
Ant war commit
39 minutes ago
README.md
Initialize README with project details and instructions
1 minute ago
SetBackground.jpg
Ant war commit
39 minutes ago
SetBackground.png
Ant war commit
39 minutes ago
back.jpg
Ant war commit
39 minutes ago
back.webp
Ant war commit
39 minutes ago
nen.jpg
Ant war commit
39 minutes ago
nene.jpg.png
Ant war commit
39 minutes ago
nest1.png
Ant war commit
39 minutes ago
vỗ tay.gif
Ant war commit
39 minutes ago
README.md
🐜 Ant’s War — Ant Colony Network Disruption Simulator
📖 Overview
Ant’s War là một ứng dụng Java Swing mô phỏng mạng lưới tổ kiến dưới dạng đồ thị vô hướng có liên thông.
Người dùng có thể trực quan hóa quá trình phá vỡ mạng lưới khi loại bỏ các “tổ kiến” (đỉnh trong đồ thị), giúp hiểu rõ hơn về các khái niệm như điểm khớp (articulation points) và thành phần liên thông (connected components) trong lý thuyết đồ thị.

🎯 Features
🧩 Mô hình hóa đồ thị: Biểu diễn mạng lưới tổ kiến dưới dạng đồ thị vô hướng liên thông.
✏️ Tạo đồ thị linh hoạt: Cho phép nhập tay hoặc tạo ngẫu nhiên đồ thị liên thông.
🧠 Thuật toán Tarjan:
Áp dụng thuật toán Tarjan (DFS) để phát hiện điểm khớp.
Hiển thị trực quan từng bước thực thi bằng màu sắc và hoạt ảnh.
➕➖ Chỉnh sửa tương tác: Thêm hoặc xóa đỉnh/cạnh trong thời gian thực.
🎯 Mô phỏng bắn tổ: Loại bỏ một đỉnh để xem ảnh hưởng đến mạng lưới.
🧠 Mục đích học thuật: Phục vụ học tập, giảng dạy và minh họa lý thuyết đồ thị.
🧠 Technical Implementation
Thành phần	Mô tả
Ngôn ngữ	Java
Giao diện	Swing
Thuật toán chính	Tarjan DFS cho điểm khớp
Hiển thị	Cập nhật trực tiếp, tô màu và hoạt ảnh
Cấu trúc thư mục	Chia module cho GUI (AntGraphGUI, MenuGUI) và logic (AntGraph, GraphInputGenerator)
⚙️ Cách chạy chương trình
1️⃣ Clone repository
git clone https://github.com/HoangKhai1911/AntsWarConfig.git
cd AntsWarConfig
2️⃣ Biên dịch mã nguồn
bash
Sao chép mã
javac -d bin src/**/*.java
3️⃣ Chạy chương trình
bash
Sao chép mã
java -cp bin App
🧩 Các lớp chính
Lớp	Chức năng
App.java	Điểm bắt đầu chương trình
AntGraph.java	Xử lý cấu trúc đồ thị và thuật toán Tarjan
AntGraphGUI.java	Vẽ đồ thị và xử lý hiển thị
MenuGUI.java	Giao diện menu chính
RoundedButton.java	Nút bo tròn, hiệu ứng phát sáng khi hover
GraphInputGenerator.java	Sinh ngẫu nhiên đồ thị để mô phỏng

📸 Hình minh họa
Giao diện khi khởi động trò chơi
![alt text](image.png)
Giao diện khi thiết lập trò chơi
![alt text](image-1.png)
Giao diện khi khởi tạo đồ thị
![alt text](image-2.png)

🧾 Kết quả đạt được
Dự án mang lại một công cụ trực quan giúp người học hiểu sâu hơn về thuật toán đồ thị, đặc biệt là khả năng phục hồi mạng lưới khi có điểm khớp bị loại bỏ.
Phần mềm này cũng là một ví dụ ứng dụng thực tiễn của Tarjan’s algorithm trong mô phỏng.

👨‍💻 Tác giả
Đỗ Nguyễn Hoàng Khải
📘 Mã sinh viên: B2205990
📚 Môn học: Thuật toán đồ thị & Lập trình Java GUI

📜 License
Dự án này được cấp phép theo MIT License.
Bạn có thể tự do sử dụng, chỉnh sửa và phân phối cho mục đích học tập và nghiên cứu.

🌐 English Summary
Ant’s War is an interactive Java Swing simulation for exploring graph theory concepts such as articulation points.
It visualizes the effect of node removal in a connected graph, helping learners understand network resilience and Tarjan’s algorithm in a practical, visual way.





AntsWarConfig/ at main · HoangKhai1911/AntsWarConfig 