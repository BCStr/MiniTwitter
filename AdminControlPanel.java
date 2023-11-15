import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminControlPanel extends JPanel {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JTextArea userIdTextArea;
    private JTextArea groupIdTextArea;

    private int userCount = 0;
    private int groupCount = 0;
    private int messageCount = 0;
    private int positiveMessageCount = 0;

    public AdminControlPanel() {
        initializeGUI();
    }

    private void initializeGUI() {
        // Set dark background color
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextArea.foreground", Color.WHITE);

        // Set the background color of the JTree to dark gray
        UIManager.put("Tree.background", Color.DARK_GRAY);

        // Create the root node for the tree
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);

        // Set the background color of the JTree
        tree.setBackground(Color.DARK_GRAY);

        // Create a split pane to separate the tree view and controls
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree),
                createControlsPanel());
        splitPane.setDividerLocation(200); // Adjust the divider location

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createControlsPanel() {
        JPanel controlsPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        controlsPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        userIdTextArea = createJTextArea();
        groupIdTextArea = createJTextArea();

        JButton addUserButton = new JButton("Add User");
        JButton addGroupButton = new JButton("Add Group");
        JButton openUserViewButton = new JButton("Open User View");

        JButton showUserTotalButton = new JButton("Show User Total");
        JButton showGroupTotalButton = new JButton("Show Group Total");

        JButton showMessageTotalButton = new JButton("Show Message Total");
        JButton showPositivePercentageButton = new JButton("Show Positive Percentage");

        // Set button foreground color
        addUserButton.setForeground(Color.WHITE);
        addGroupButton.setForeground(Color.WHITE);
        openUserViewButton.setForeground(Color.WHITE);
        showUserTotalButton.setForeground(Color.WHITE);
        showGroupTotalButton.setForeground(Color.WHITE);
        showMessageTotalButton.setForeground(Color.WHITE);
        showPositivePercentageButton.setForeground(Color.WHITE);

        // settings for buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Top, left, bottom, right padding
        gbc.weightx = 0.5; // Give each button half the extra horizontal space
        gbc.ipadx = 40;
        gbc.ipady = 20;

        // User ID label and text area
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        controlsPanel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        controlsPanel.add(userIdTextArea, gbc);

        // Group ID label and text area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        controlsPanel.add(new JLabel("Group ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        controlsPanel.add(groupIdTextArea, gbc);

        // Add User and Add Group buttons in the same row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        controlsPanel.add(addUserButton, gbc);
        gbc.gridx = 1;
        controlsPanel.add(addGroupButton, gbc);

        // Open User View button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        controlsPanel.add(openUserViewButton, gbc);

        // Show User Total and Show Group Total buttons in the same row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        controlsPanel.add(showUserTotalButton, gbc);
        gbc.gridx = 1;
        controlsPanel.add(showGroupTotalButton, gbc);

        // Show Message Total and Show Positive Percentage buttons in the same row
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        controlsPanel.add(showMessageTotalButton, gbc);
        gbc.gridx = 1;
        controlsPanel.add(showPositivePercentageButton, gbc);

        // Add action listeners to the buttons
        addUserButton.addActionListener(e -> addUser());
        addGroupButton.addActionListener(e -> addGroup());
        openUserViewButton.addActionListener(e -> openUserView());
        showUserTotalButton.addActionListener(e -> showUserTotal());
        showGroupTotalButton.addActionListener(e -> showGroupTotal());
        showMessageTotalButton.addActionListener(e -> showMessageTotal());
        showPositivePercentageButton.addActionListener(e -> showPositivePercentage());

        return controlsPanel;
    }

    private JTextArea createJTextArea() {
        JTextArea textArea = new JTextArea(4, 20);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        return textArea;
    }

    private void addUser() {
        String userID = userIdTextArea.getText().trim();
        if (!userID.isEmpty()) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null) {
                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userID);
                treeModel.insertNodeInto(userNode, selectedNode, selectedNode.getChildCount());
                userIdTextArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a group to add the user to.");
            }
        }
    }

    private void addGroup() {
        String groupID = groupIdTextArea.getText().trim();
        if (!groupID.isEmpty()) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null) {
                DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(groupID);
                treeModel.insertNodeInto(groupNode, selectedNode, selectedNode.getChildCount());
                groupIdTextArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a group to add the subgroup to.");
            }
        }
    }

    private void openUserView() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            // String nodeName = selectedNode.toString();
            List<User> users = new ArrayList<>();

            UserViewPanel userViewPanel = new UserViewPanel(users);

            JFrame userViewFrame = new JFrame("User View");
            userViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userViewFrame.setSize(600, 400);

            // Add the UserViewPanel to the userViewFrame
            userViewFrame.getContentPane().add(userViewPanel, BorderLayout.CENTER);

            userViewFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to open the User View.");
        }
    }

    private void showUserTotal() {
        userCount = countUsers((DefaultMutableTreeNode) tree.getModel().getRoot());
        JOptionPane.showMessageDialog(this, "Total number of users: " + userCount);
    }

    private void showGroupTotal() {
        groupCount = countGroups((DefaultMutableTreeNode) tree.getModel().getRoot());
        JOptionPane.showMessageDialog(this, "Total number of groups: " + groupCount);
    }

    private void showMessageTotal() {
        messageCount = countMessages((DefaultMutableTreeNode) tree.getModel().getRoot());
        JOptionPane.showMessageDialog(this, "Total number of messages: " + messageCount);
    }

    private void showPositivePercentage() {
        calculatePositivePercentage((DefaultMutableTreeNode) tree.getModel().getRoot());
        JOptionPane.showMessageDialog(this, "Positive message percentage: " + positiveMessageCount + "%");
    }

    private int countUsers(DefaultMutableTreeNode node) {
        int count = 0;
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            if (childNode.getUserObject() instanceof User) {
                count++;
            } else {
                count += countUsers(childNode);
            }
        }
        return count;
    }

    private int countGroups(DefaultMutableTreeNode node) {
        int count = 0;
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            if (!(childNode.getUserObject() instanceof User)) {
                count++;
                count += countGroups(childNode); // Recursively count groups within this group
            }
        }
        return count;
    }

    private int countMessages(DefaultMutableTreeNode node) {
        int count = 0;
        if (node.getUserObject() instanceof User) {
            // If the node is a user, get the message count
            count += ((User) node.getUserObject()).getMessageCount();
        }

        // Recursively count messages
        for (int i = 0; i < node.getChildCount(); i++) {
            count += countMessages((DefaultMutableTreeNode) node.getChildAt(i));
        }
        return count;
    }

    private void calculatePositivePercentage(DefaultMutableTreeNode node) {
        int totalMessages = countMessages(node);
        int positiveMessages = 0;
        return totalMessages;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin Control Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            AdminControlPanel adminControlPanel = new AdminControlPanel();
            frame.getContentPane().add(adminControlPanel);

            frame.setVisible(true);
        });
    }
}

class UserViewPanel extends JPanel {
    private List<User> users;

    public UserViewPanel(List<User> users) {
        this.users = users;
        initializePanel();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        // Call a method to update the user information display
        updateUserInfoDisplay();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        JLabel userLabel = new JLabel("User IDs: ");
        StringBuilder userIDs = new StringBuilder();
        for (User user : users) {
            userIDs.append(user.getUserId()).append(", ");
        }
        userLabel.setText("User IDs: " + userIDs.toString());

        add(userLabel, BorderLayout.CENTER);
    }

    private void updateUserInfoDisplay() {
        removeAll(); // Clear the existing components
        initializePanel(); // Reinitialize the panel with updated user information
        revalidate();
        repaint();
    }
}

class User {
    private String userId;

    public String getUserId() {
        return userId;
    }
}