import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Frame1 extends JFrame {
    private Graph_M graph;
    private JTextArea textArea;
    private Map<String, Point> stationPositions;

    public Frame1() {
        graph = new Graph_M();
        stationPositions = new HashMap<>();

        // Set up the frame
        setTitle("Graph Operations");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Add/Remove Stations", JLabel.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        add(headerLabel, BorderLayout.NORTH);

        // Text area for display
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.white);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 20, 20));

        // Buttons
        JButton btnAddStation = new JButton("Add Station");
        JButton btnDisplayStations = new JButton("Display Stations");
        JButton btnCheckPath = new JButton("Check Path");
        JButton btnGetMinDistance = new JButton("Get Minimum Distance");
        JButton btnShowMinTime = new JButton("Show Minimum Time");
        JButton btnAddEdge = new JButton("Add Edge");
        JButton btnOpenGraphVisualization = new JButton("Visualize Graph");
        JButton btnExit = new JButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(btnAddStation);
        buttonPanel.add(btnDisplayStations);
        buttonPanel.add(btnCheckPath);
        buttonPanel.add(btnGetMinDistance);
        buttonPanel.add(btnShowMinTime);
        buttonPanel.add(btnAddEdge);
        buttonPanel.add(btnOpenGraphVisualization);
        buttonPanel.add(btnExit);

        // Add panel to frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        btnAddStation.addActionListener(e -> addStation());
        btnDisplayStations.addActionListener(e -> displayStations());
        btnCheckPath.addActionListener(e -> checkPath());
        btnGetMinDistance.addActionListener(e -> getMinDistance());
        btnShowMinTime.addActionListener(e -> showMinTime());
        btnAddEdge.addActionListener(e -> addEdge());
        btnOpenGraphVisualization.addActionListener(e -> openGraphVisualization());
        btnExit.addActionListener(e -> System.exit(0));

        // Initialize graph
        initializeGraph();
    }

    private void initializeGraph() {
        addStationToGraph("Station1", new Point(100, 100));
        addStationToGraph("Station2", new Point(200, 200));
        addStationToGraph("Station3", new Point(300, 100));
        addStationToGraph("Station4", new Point(400, 200));
        addStationToGraph("Station5", new Point(500, 100));
        addStationToGraph("Station6", new Point(600, 200));
        graph.addEdge("Station1", "Station2", 10);
        graph.addEdge("Station2", "Station3", 15);
    }

    private void addStationToGraph(String name, Point position) {
        graph.addVertex(name);
        stationPositions.put(name, position);
        textArea.append("Added station: " + name + "\n");
    }

    private void addStation() {
        try {
            String stationName = JOptionPane.showInputDialog(this, "Enter Station Name:");
            if (stationName == null || stationName.trim().isEmpty()) {
                textArea.append("Station addition cancelled or invalid input.\n");
                return;
            }
            String xInput = JOptionPane.showInputDialog(this, "Enter X coordinate:");
            if (xInput == null || xInput.trim().isEmpty()) {
                textArea.append("Station addition cancelled or invalid X coordinate.\n");
                return;
            }
            String yInput = JOptionPane.showInputDialog(this, "Enter Y coordinate:");
            if (yInput == null || yInput.trim().isEmpty()) {
                textArea.append("Station addition cancelled or invalid Y coordinate.\n");
                return;
            }
            int x = Integer.parseInt(xInput.trim());
            int y = Integer.parseInt(yInput.trim());
            addStationToGraph(stationName.trim(), new Point(x, y));
        } catch (NumberFormatException e) {
            textArea.append("Invalid coordinates entered. Please use numeric values.\n");
        }
    }

    private void displayStations() {
        textArea.setText("Stations in the Graph:\n" + graph.display_Stations());
    }

    private void checkPath() {
        try {
            String startStation = JOptionPane.showInputDialog(this, "Enter Start Station:");
            if (startStation == null || startStation.trim().isEmpty()) {
                textArea.append("Path checking cancelled or invalid Start Station.\n");
                return;
            }
            String endStation = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (endStation == null || endStation.trim().isEmpty()) {
                textArea.append("Path checking cancelled or invalid End Station.\n");
                return;
            }
            boolean pathExists = graph.hasPath(startStation.trim(), endStation.trim(), new HashMap<>());
            textArea.append("Path exists between " + startStation + " and " + endStation + ": " + pathExists + "\n");
        } catch (Exception e) {
            textArea.append("An error occurred while checking the path.\n");
        }
    }

    private void getMinDistance() {
        try {
            String startStation = JOptionPane.showInputDialog(this, "Enter Start Station:");
            if (startStation == null || startStation.trim().isEmpty()) {
                textArea.append("Minimum distance query cancelled or invalid Start Station.\n");
                return;
            }
            String endStation = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (endStation == null || endStation.trim().isEmpty()) {
                textArea.append("Minimum distance query cancelled or invalid End Station.\n");
                return;
            }
            String minDistance = graph.Get_Minimum_Distance(startStation.trim(), endStation.trim());
            textArea.append("Minimum Distance between " + startStation + " and " + endStation + ": " + minDistance + "\n");
        } catch (Exception e) {
            textArea.append("An error occurred while calculating the minimum distance.\n");
        }
    }

    private void showMinTime() {
        try {
            String startStation = JOptionPane.showInputDialog(this, "Enter Start Station:");
            if (startStation == null || startStation.trim().isEmpty()) {
                textArea.append("Minimum time query cancelled or invalid Start Station.\n");
                return;
            }
            String endStation = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (endStation == null || endStation.trim().isEmpty()) {
                textArea.append("Minimum time query cancelled or invalid End Station.\n");
                return;
            }
            String minTime = graph.Get_Minimum_Time(startStation.trim(), endStation.trim());
            textArea.append("Minimum Time between " + startStation + " and " + endStation + ": " + minTime + "\n");
        } catch (Exception e) {
            textArea.append("An error occurred while calculating the minimum time.\n");
        }
    }

    private void addEdge() {
        try {
            String station1 = JOptionPane.showInputDialog(this, "Enter Start Station:");
            if (station1 == null || station1.trim().isEmpty()) {
                textArea.append("Edge addition cancelled or invalid Start Station.\n");
                return;
            }
            String station2 = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (station2 == null || station2.trim().isEmpty()) {
                textArea.append("Edge addition cancelled or invalid End Station.\n");
                return;
            }
            String distanceInput = JOptionPane.showInputDialog(this, "Enter Distance:");
            if (distanceInput == null || distanceInput.trim().isEmpty()) {
                textArea.append("Edge addition cancelled or invalid Distance.\n");
                return;
            }
            int distance = Integer.parseInt(distanceInput.trim());
            graph.addEdge(station1.trim(), station2.trim(), distance);
            textArea.append("Added edge between " + station1 + " and " + station2 + " with distance " + distance + "\n");
        } catch (NumberFormatException e) {
            textArea.append("Invalid distance entered. Please use a numeric value.\n");
        } catch (Exception e) {
            textArea.append("An error occurred while adding the edge.\n");
        }
    }

    private void openGraphVisualization() {
        SwingUtilities.invokeLater(() -> new Frame2(graph, stationPositions).setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame1().setVisible(true));
    }
}
