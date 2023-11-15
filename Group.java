import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Group {
    private JFrame frame;
    private JTextArea groupIdTextArea;
    private JButton addGroupButton;
    private Tree tree; // Add a reference to the Tree

    public Group(Tree tree, DefaultMutableTreeNode selectedNode) {
        this.tree = tree; // Initialize the Tree reference
        initializeGUI(selectedNode);
    }

    private void initializeGUI(DefaultMutableTreeNode selectedNode) {
        frame = new JFrame("Add Group");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        groupIdTextArea = new JTextArea(1, 20);
        groupIdTextArea.setCaretColor(Color.WHITE);
        groupIdTextArea.setBackground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(groupIdTextArea, gbc);

        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGroup(selectedNode);
            }
        });
        gbc.gridx = 1;
        panel.add(addGroupButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addGroup(DefaultMutableTreeNode selectedNode) {
        String groupID = groupIdTextArea.getText().trim();
        if (!groupID.isEmpty()) {
            // Use the Tree class method to add a group node
            tree.addGroup(groupID, selectedNode);
            groupIdTextArea.setText("");
            frame.dispose(); // Close the frame after adding the group
        }
    }

    public static void main(String[] args) {
        // This main method is for testing purposes
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Group(null, null).frame; // Provide necessary parameters
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
