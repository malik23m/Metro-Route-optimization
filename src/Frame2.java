import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Frame2 extends JFrame {
    private Graph_M graph;
    private Map<String, Point> stationPositions;
    private GraphPanel graphPanel;

    public Frame2(Graph_M graph, Map<String, Point> stationPositions) {
        this.graph = graph;
        this.stationPositions = stationPositions;

        // Set up the frame
        setTitle("Graph Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Metro Stations", JLabel.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for the graph
        graphPanel = new GraphPanel();
        JScrollPane scrollPane = new JScrollPane(graphPanel);
        graphPanel.setPreferredSize(new Dimension(2000, 2000)); // Adjust size as needed
        add(scrollPane, BorderLayout.CENTER);

        // Panel for user interaction
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());

        JButton btnMinDistance = new JButton("Get Minimum Distance");
        JButton btnMinTime = new JButton("Get Minimum Time");
        JButton list = new JButton("All Stations");

        controlsPanel.add(btnMinDistance);
        controlsPanel.add(btnMinTime);
        controlsPanel.add(list);
        add(controlsPanel, BorderLayout.SOUTH);

        // Set button styles
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        JButton[] buttons = {btnMinDistance, btnMinTime, list};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(new Color(173, 216, 230)); // Light blue background
            button.setFocusPainted(false); // Remove focus border
        }
        // Action listeners for buttons
        btnMinDistance.addActionListener(e -> {
            String startStation = JOptionPane.showInputDialog(this, "Enter Start Station:");
            String endStation = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (startStation == null || endStation == null || startStation.trim().isEmpty() || endStation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Start or End Station cannot be empty.");
                return;
            }
            String result = graph.Get_Minimum_Distance(startStation, endStation);
            JOptionPane.showMessageDialog(this, "Shortest Path: " + result);
            graphPanel.highlightPath(startStation, endStation, "distance");
        });

        btnMinTime.addActionListener(e -> {
            String startStation = JOptionPane.showInputDialog(this, "Enter Start Station:");
            String endStation = JOptionPane.showInputDialog(this, "Enter End Station:");
            if (startStation == null || endStation == null || startStation.trim().isEmpty() || endStation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Start or End Station cannot be empty.");
                return;
            }
            String result = graph.Get_Minimum_Time(startStation, endStation);
            JOptionPane.showMessageDialog(this, "Shortest Path: " + result);
            graphPanel.highlightPath(startStation, endStation, "time");
        });

        // all stations
        list.addActionListener(e -> {
          Frame3 f3 =new Frame3(graph,stationPositions);
          f3.setVisible(true);
        });
    }

    // Method to refresh the panel
    public void refresh() {
        graphPanel.repaint();
    }

    // Custom panel to draw the graph
    private class GraphPanel extends JPanel {
        private String highlightStart = null;
        private String highlightEnd = null;
        private String highlightType = null;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Draw edges
            g2.setColor(Color.BLACK);
            for (String station : stationPositions.keySet()) {
                Graph_M.Vertex vertex = graph.vtces.get(station); // Assuming vtces is a map of station names to Vertex objects
                if (vertex != null) {
                    for (Map.Entry<String, Integer> neighbor : vertex.nbrs.entrySet()) {
                        String neighborStation = neighbor.getKey();
                        Point p1 = stationPositions.get(station);
                        Point p2 = stationPositions.get(neighborStation);
                        if (p1 != null && p2 != null) {
                            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                            String distance = String.valueOf(neighbor.getValue());
                            g2.drawString(distance, (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
                        }
                    }
                }
            }

            // Highlight shortest path
            if (highlightStart != null && highlightEnd != null && highlightType != null) {
                g2.setColor(Color.RED);
                // Highlighting logic here (simplified for now)
            }

            // Draw nodes
            g2.setColor(Color.BLUE);
            for (Map.Entry<String, Point> entry : stationPositions.entrySet()) {
                Point p = entry.getValue();
                g2.fillOval(p.x - 5, p.y - 5, 10, 10);
                g2.drawString(entry.getKey(), p.x + 5, p.y - 5);
            }
        }

        public void highlightPath(String start, String end, String type) {
            this.highlightStart = start;
            this.highlightEnd = end;
            this.highlightType = type;
            repaint();
        }
    }
}
