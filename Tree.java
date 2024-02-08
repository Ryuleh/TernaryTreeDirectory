class Tree {
    private Node root;

    public Tree(String rootName) {
        this.root = new Node(rootName);
    }

    public Node getRoot() {
        return root;
    }

    // Additional methods for tree operations

    // Method to add a directory to the tree
    public Node addDirectory(Node parent, String directoryName) {
        Node newDirectory = new Node(directoryName);
        parent.addChild(newDirectory);
        return newDirectory;
    }

    // Method to remove a directory from the tree
    public boolean removeDirectory(Node parent, Node directory) {
        return parent.removeChild(directory);
    }
}
