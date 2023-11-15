import javax.swing.*;
import java.awt.*;

public class UserView {
    private JFrame frame;

    public UserView() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("User View");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Instantiate UserViewPanel and add it to the frame
        UserViewPanel userViewPanel = new UserViewPanel();
        frame.getContentPane().add(userViewPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserView();
        });
    }
}

class UserViewPanel extends JPanel {
    private JTextArea userIdTextArea;
    private JButton followUserButton;
    private JTextArea tweetMessageTextArea;
    private JButton postTweetButton;
    private JList<String> followingList;
    private JList<String> newsFeedList;

    public UserViewPanel() {
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userIdTextArea = createJTextArea();
        userIdTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(userIdTextArea);

        followUserButton = new JButton("Follow User");
        followUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        followUserButton.addActionListener(e -> followUser());
        add(followUserButton);

        tweetMessageTextArea = createJTextArea();
        tweetMessageTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tweetMessageTextArea);

        postTweetButton = new JButton("Post Tweet");
        postTweetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        postTweetButton.addActionListener(e -> postTweet());
        add(postTweetButton);

        // Use the followingList and newsFeedList fields
        followingList = new JList<>();
        add(new JScrollPane(followingList));

        newsFeedList = new JList<>();
        add(new JScrollPane(newsFeedList));
    }

    // Add this method to create a JTextArea with caret visibility set
    private JTextArea createJTextArea() {
        JTextArea textArea = new JTextArea(6, 20);
        textArea.setCaretColor(Color.BLACK); // Set the caret color
        textArea.setBackground(Color.WHITE); // Set the background color
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        return textArea;
    }

    private void followUser() {
        // Implement the follow user logic
        // For example, update the following list
    }

    private void postTweet() {
        // Implement the post tweet logic
        // For example, update the news feed list
    }
}
