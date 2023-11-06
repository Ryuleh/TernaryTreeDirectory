public class DirectoryNode {
    DirectoryNode left;
    DirectoryNode middle;
    DirectoryNode right;
    String name;
    boolean isFile;

    public DirectoryNode(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
    }

    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException {
        if (this.isFile) {
            throw new NotADirectoryException("Cannot add child to a file.");
        } else if (this.left == null) {
            this.left = newChild;
        } else if (this.middle == null) {
            this.middle = newChild;
        } else if (this.right == null) {
            this.right = newChild;
        } else {
            throw new FullDirectoryException("Directory is full.");
        }
    }
}
