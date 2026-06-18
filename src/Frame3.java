
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Frame3 extends JFrame {
    private Graph_M graph;
    private Map<String, Point> stationPositions;

    public Frame3(Graph_M graph, Map<String, Point> stationPositions) {
        this.graph = graph;
        this.stationPositions = stationPositions;

        // Set up the frame
        setTitle("All Metro Stations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("All Stations", JLabel.CENTER);
        headerLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        add(headerLabel, BorderLayout.NORTH);


        // Create the table
        String[] columnNames = {"Station Number", "Station Name", "X Position", "Y Position"};
        Object[][] data = new Object[stationPositions.size()][4];
        int stationNumber = 1;
        int row = 0;
        for (Map.Entry<String, Point> entry : stationPositions.entrySet()) {
            data[row][0] = stationNumber++;
            data[row][1] = entry.getKey();
            data[row][2] = entry.getValue().x;
            data[row][3] = entry.getValue().y;
            row++;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Set custom header renderer
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new CustomHeaderRenderer());

        // Button to close the frame
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Georgia", Font.BOLD, 14));
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        closeButton.setBackground(new Color(173, 216, 230));
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Custom header renderer class
    private static class CustomHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setBackground(new Color(173, 216, 230)); // Light blue background
            component.setFont(new Font("Georgia", Font.BOLD, 14)); // Custom font
            setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
            return component;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Graph_M graph = new Graph_M();
            Map<String, Point> stationPositions = new HashMap<>();

            // Initialize the graph with vertices and edges
            graph.addVertex("Station1");
            graph.addVertex("Station2");
            graph.addVertex("Station3");
            graph.addVertex("Station4");
            graph.addVertex("Station5");
            graph.addVertex("Station6");
            graph.addVertex("Station7");
            graph.addVertex("Station8");
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

            // Initialize station positions
            stationPositions.put("Station1", new Point(100, 100));
            stationPositions.put("Station2", new Point(200, 200));
            stationPositions.put("Station3", new Point(300, 100));
            stationPositions.put("Station4", new Point(400, 200));
            stationPositions.put("Station5", new Point(500, 100));
            stationPositions.put("Station6", new Point(600, 200));
            stationPositions.put("Station7", new Point(700, 100));
            stationPositions.put("Station8", new Point(800, 200));


            new Frame3(graph, stationPositions).setVisible(true);
        });
    }
}
