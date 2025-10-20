🐜 Ant’s War — Ant Colony Network Disruption Simulator
📖 Overview

Ant’s War is an interactive Java Swing application that simulates a connected ant colony network modeled as an undirected graph.
The project visualizes how removing specific nodes (“colonies”) affects network connectivity, providing a hands-on tool for graph theory education, especially for concepts such as articulation points and connected components.

🎯 Features

🧩 Graph Modeling: Create and visualize an undirected, connected graph representing ant colonies and tunnels.

✏️ Manual & Auto Generation: Input graphs manually or let the system generate random connected graphs.

➕➖ Interactive Editing: Add or remove vertices and edges in real time.

🎯 Target Simulation: “Shoot” a colony (remove a node) and observe the connectivity impact instantly.

🧠 Tarjan’s Algorithm Visualization:

Implements Tarjan’s algorithm via DFS to detect articulation points.

Visual step-by-step animation and color highlighting of algorithm execution.

📊 Educational Purpose: Designed as an educational and analytical tool for teaching and demonstrating graph algorithms.

🧠 Technical Implementation
Component	Description
Language	Java
GUI Framework	Swing
Core Algorithm	Tarjan’s DFS-based articulation point detection
Visualization	Real-time graph updates, vertex highlighting, animation
Project Structure	Modular classes for GUI (AntGraphGUI, MenuGUI) and graph logic (AntGraph, GraphInputGenerator)
⚙️ How to Run

Clone the repository:

git clone https://github.com/HoangKhai1911/AntsWarConfig.git
cd AntsWarConfig


Compile the source code:

javac -d bin src/**/*.java


Run the program:

java -cp bin App

🧩 Main Classes
Class	Role
App.java	Entry point of the program
AntGraph.java	Graph data structure & Tarjan’s algorithm
AntGraphGUI.java	handles graph drawing and visualization
MenuGUI.java	Main menu interface
RoundedButton.java	Custom button UI (rounded corners, glow effects)
GraphInputGenerator.java	: Auto graph generation for simulation

🧾 Project Outcome

This project provides an interactive visualization tool that bridges theoretical graph algorithms and real-world educational applications — a practical approach to exploring network resilience and disruption strategies.

👨‍💻 Author

Đỗ Nguyễn Hoàng Khải
Student ID: B2205990
Course Project — Graph Algorithms & Java GUI Programming

📜 License

This project is licensed under the MIT License — feel free to use, modify, and distribute for educational purposes.
