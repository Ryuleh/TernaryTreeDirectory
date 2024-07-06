class Tree {
    private Node root;

    public Tree(String rootName) {
        root = new Node(rootName, true);
    }

    public Node getRoot() {
        return root;
    }

    public void addDirectory(Node parent, String directoryName) {
        Node newDirectory = new Node(directoryName, true);
        parent.addChild(newDirectory);
    }

    public void removeDirectory(Node parent, Node directory) {
        parent.removeChild(directory);
    }
}
