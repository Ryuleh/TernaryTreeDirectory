import java.util.ArrayList;
import java.util.List;

class Node {
    private String name;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public boolean removeChild(Node child) {
        return children.remove(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public Node getChildByName(String name) {
        for (Node child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }
}
