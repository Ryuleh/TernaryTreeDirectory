import java.util.ArrayList;
import java.util.List;

class Node {
    private String name;
    private List<Node> children;
    private boolean isDirectory;
    private Node parent;

    public Node(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = isDirectory ? new ArrayList<>() : null;
        this.parent = null;
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public Node getChildByName(String name) {
        if (children != null) {
            for (Node child : children) {
                if (child.getName().equals(name)) {
                    return child;
                }
            }
        }
        return null;
    }

    public void addChild(Node child) {
        if (children != null) {
            children.add(child);
            child.setParent(this); // Set the parent of the child node
        }
    }

    public void removeChild(Node child) {
        if (children != null) {
            children.remove(child);
        }
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }
}
