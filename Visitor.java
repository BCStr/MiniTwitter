import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

// Visitor interface
interface Visitor {
    void visit(UserNode userNode);

    void visit(GroupNode groupNode);
}

// TreeNode interface
interface TreeNode {
    void accept(Visitor visitor);
}

// Concrete class for user nodes
class UserNode implements TreeNode {
    private String userID;

    public UserNode(String userID) {
        this.userID = userID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getUserID() {
        return userID;
    }
}

// Concrete class for group nodes
class GroupNode implements TreeNode {
    private String groupID;
    private List<TreeNode> children = new ArrayList<>();

    public GroupNode(String groupID) {
        this.groupID = groupID;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (TreeNode child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public String getGroupID() {
        return groupID;
    }
}

// ExampleVisitor class implementing the Visitor interface
class ExampleVisitor implements Visitor {
    @Override
    public void visit(UserNode userNode) {
        System.out.println("Visiting UserNode: " + userNode.getUserID());
    }

    @Override
    public void visit(GroupNode groupNode) {
        System.out.println("Visiting GroupNode: " + groupNode.getGroupID());
    }
}

// Tree class with main method for testing
class Tree {
    private DefaultTreeModel treeModel;

    public Tree(TreeNode root) {
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode(root));
    }

    public void addNode(TreeNode parent, TreeNode child) {
        DefaultMutableTreeNode parentNode = findNode(parent);
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
    }

    private DefaultMutableTreeNode findNode(TreeNode node) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        return findNode(root, node);
    }

    private DefaultMutableTreeNode findNode(DefaultMutableTreeNode current, TreeNode target) {
        if (current.getUserObject() == target) {
            return current;
        }

        for (int i = 0; i < current.getChildCount(); i++) {
            DefaultMutableTreeNode found = findNode((DefaultMutableTreeNode) current.getChildAt(i), target);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public void acceptVisitor(Visitor visitor) {
        TreeNode root = (TreeNode) ((DefaultMutableTreeNode) treeModel.getRoot()).getUserObject();
        root.accept(visitor);
    }

    public static void main(String[] args) {
        // Example usage
        GroupNode root = new GroupNode("Root");
        Tree tree = new Tree(root);

        GroupNode groupNode1 = new GroupNode("Group1");
        UserNode userNode1 = new UserNode("User1");
        UserNode userNode2 = new UserNode("User2");

        tree.addNode(root, groupNode1);
        tree.addNode(groupNode1, userNode1);
        tree.addNode(groupNode1, userNode2);

        // Create and apply a visitor
        Visitor visitor = new ExampleVisitor();
        tree.acceptVisitor(visitor);
    }
}
