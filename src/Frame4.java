import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Frame4 extends JFrame {
    private Graph_M graph;
    private Map<String, Point> stationPositions;

    public Frame4() {
        // Set up the frame
        setTitle("Metro App");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Metro App", JLabel.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        add(headerLabel, BorderLayout.NORTH);

        // Load the image
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/metro.jpeg"));
            Image scaledImage = imageIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("Image not found!");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        }

        // Panel for the image
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.CENTER);

        // Buttons for user selection
        JButton adminButton = new JButton("Enter as Admin");
        JButton userButton = new JButton("Enter as User");

        adminButton.setFont(new Font("Arial", Font.BOLD, 24));
        userButton.setFont(new Font("Arial", Font.BOLD, 24));

        // Set button colors
        adminButton.setBackground(new Color(173, 216, 230));
        userButton.setBackground(new Color(173, 216, 230));
        adminButton.setPreferredSize(new Dimension(250, 50));
        userButton.setPreferredSize(new Dimension(250, 50));

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(adminButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(userButton, gbc);

        // Add the button panel to a new panel at the bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        adminButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new Frame1().setVisible(true); // Open Frame1 for admin
        });

        userButton.addActionListener(e -> {
            dispose(); // Close the current frame
            initializeGraph(); // Initialize the graph before opening Frame2
            new Frame2(graph, stationPositions).setVisible(true); // Open Frame2 for user
        });

        // Initialize the graph and station positions
        graph = new Graph_M();
        stationPositions = new HashMap<>();
    }

    private void initializeGraph() {
        // Initialize the graph according to your specified vertices and edges
        addStationToGraph("Station1", new Point(100, 100));
        addStationToGraph("Station2", new Point(200, 200));
        addStationToGraph("Station3", new Point(300, 100));
        addStationToGraph("Station4", new Point(400, 200));
        addStationToGraph("Station5", new Point(500, 100));
        addStationToGraph("Station6", new Point(600, 200));
        addStationToGraph("Station7", new Point(700, 100));
        addStationToGraph("Station8", new Point(800, 200));

        graph.addEdge("Station1", "Station2", 10);
        graph.addEdge("Station2", "Station3", 15);
        graph.addEdge("Station3", "Station1", 11);
        graph.addEdge("Station3", "Station4", 20);
        graph.addEdge("Station3", "Station5", 20);
        graph.addEdge("Station5", "Station4", 30);
        graph.addEdge("Station6", "Station7", 40);
        graph.addEdge("Station7", "Station8", 50);
        graph.addEdge("Station8", "Station1", 20);
        graph.addEdge("Station4", "Station6", 5);
    }

    private void addStationToGraph(String name, Point position) {
        graph.addVertex(name);
        stationPositions.put(name, position);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame4().setVisible(true));
    }
}
