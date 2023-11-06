import java.util.*;

public class DirectoryTree {
  DirectoryNode root;
  DirectoryNode cursor;

  public DirectoryTree() {
    this.root = new DirectoryNode("root", false);
    this.cursor = this.root;
  }

  public void resetCursor() {
    this.cursor = this.root;
  }

  public void changeDirectory(String name) throws NotADirectoryException {
    if (this.cursor.isFile) {
      throw new NotADirectoryException("Cursor is at a file.");
    }

    if (this.cursor.left != null && this.cursor.left.name.equals(name)) {
      this.cursor = this.cursor.left;
    } else if (this.cursor.middle != null && this.cursor.middle.name.equals(name)) {
      this.cursor = this.cursor.middle;
    } else if (this.cursor.right != null && this.cursor.right.name.equals(name)) {
      this.cursor = this.cursor.right;
    } else {
      throw new NotADirectoryException("Directory not found.");
    }
  }

  public String presentWorkingDirectory() {
    if (this.cursor == this.root) {
      return this.root.name;
    } else {
      ArrayList<String> path = new ArrayList<>();
      DirectoryNode temp = this.cursor;
      while (temp != this.root) {
        path.add(0, temp.name);
        temp = findParent(temp, this.root);
      }
      return String.join("/", path);
    }
  }

  private DirectoryNode findParent(DirectoryNode node, DirectoryNode parent) {
    if (parent.left == node || parent.middle == node || parent.right == node) {
      return parent;
    }
    DirectoryNode temp = null;
    if (parent.left != null) {
      temp = findParent(node, parent.left);
    }
    if (temp == null && parent.middle != null) {
      temp = findParent(node, parent.middle);
    }
    if (temp == null && parent.right != null) {
      temp = findParent(node, parent.right);
    }
    return temp;
  }

  public String listDirectory() {
    StringBuilder result = new StringBuilder();
    if (this.cursor.left != null) {
      result.append(this.cursor.left.name).append(" ");
    }
    if (this.cursor.middle != null) {
      result.append(this.cursor.middle.name).append(" ");
    }
    if (this.cursor.right != null) {
      result.append(this.cursor.right.name).append(" ");
    }
    return result.toString().trim();
  }

  public void printDirectoryTree() {
    printDirectoryTree(this.root, 0);
  }

  private void printDirectoryTree(DirectoryNode node, int depth) {
    if (node == null) {
      return;
    }
    for (int i = 0; i < depth; i++) {
      System.out.print("  ");
    }
    System.out.println("|_" + node.name);
    printDirectoryTree(node.left, depth + 1);
    printDirectoryTree(node.middle, depth + 1);
    printDirectoryTree(node.right, depth + 1);
  }

  public void makeDirectory(String name)
      throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
    if (name.contains(" ") || name.contains("/")) {
      throw new IllegalArgumentException("Invalid directory name.");
    }
    DirectoryNode newDir = new DirectoryNode(name, false);
    try {
      this.cursor.addChild(newDir);
    } catch (FullDirectoryException e) {
      System.out.println(e.getMessage());
    }
  }

  public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
    if (name.contains(" ") || name.contains("/")) {
      throw new IllegalArgumentException("Invalid file name.");
    }
    DirectoryNode newFile = new DirectoryNode(name, true);
    try {
      this.cursor.addChild(newFile);
    } catch (FullDirectoryException e) {
      System.out.println(e.getMessage());
    }
  }
}
