import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ensure the UI updates are made on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize and display Frame1
                new Frame4().setVisible(true);
            }
        });
    }
}
