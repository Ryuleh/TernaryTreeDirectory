import java.util.*;

public class BashTerminal {
  public static void main(String[] args) {
    DirectoryTree directoryTree = new DirectoryTree();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();
      String[] command = input.split(" ", 2);

      switch (command[0]) {
        case "pwd":
          System.out.println(directoryTree.presentWorkingDirectory());
          break;
        case "ls":
          System.out.println(directoryTree.listDirectory());
          break;
        case "ls-R":
          directoryTree.printDirectoryTree();
          break;
        case "cd":
          try {
            directoryTree.changeDirectory(command[1]);
          } catch (NotADirectoryException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "mkdir":
          try {
            directoryTree.makeDirectory(command[1]);
          } catch (IllegalArgumentException | FullDirectoryException | NotADirectoryException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "touch":
          try {
            directoryTree.makeFile(command[1]);
          } catch (IllegalArgumentException | FullDirectoryException | NotADirectoryException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "exit":
          System.out.println("Exiting the Bash Terminal...");
          return;
        default:
          System.out.println("Invalid command. Please try again.");
          break;
      }
    }
  }
}
