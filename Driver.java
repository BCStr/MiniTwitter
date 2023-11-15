import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminControlPanel adminControlPanel = new AdminControlPanel();

                JFrame frame = new JFrame("Admin Control Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.getContentPane().add(adminControlPanel);
                frame.setVisible(true);
            }
        });
    }
}