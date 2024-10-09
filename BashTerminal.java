import java.util.*;

public class BashTerminal {
    private Node currentDirectory;
    private Tree fileSystem;

    public BashTerminal() {
        fileSystem = new Tree("root");
        currentDirectory = fileSystem.getRoot(); // Home directory
        drawTree(); // Draw the tree structure on startup
    }

    public void startTerminal() {
        Scanner scanner = new Scanner(System.in);
        // Greeting message
        System.out.println("Welcome to Bash Terminal! Type 'help' to see available commands or 'exit' to quit.");

        while (true) {
            System.out.print(currentDirectory.getName() + " $ ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ", 2); // Split into command and arguments

            switch (tokens[0]) {
                case "help":
                    displayHelp();
                    break;

                case "pwd":
                    pwd();
                    break;

                case "ls":
                    ls();
                    break;

                case "mkdir":
                    if (tokens.length < 2) {
                        System.out.println("Usage: mkdir <directory>");
                    } else {
                        mkdir(tokens[1]);
                    }
                    break;

                case "touch":
                    if (tokens.length < 2) {
                        System.out.println("Usage: touch <filename>");
                    } else {
                        touch(tokens[1]);
                    }
                    break;

                case "write":
                    if (tokens.length < 2) {
                        System.out.println("Usage: write <filename> <content>");
                    } else {
                        String[] writeArgs = tokens[1].split(" ", 2); // Split filename and content
                        if (writeArgs.length < 2) {
                            System.out.println("Usage: write <filename> <content>");
                        } else {
                            write(writeArgs[0], writeArgs[1]); // Pass filename and content
                        }
                    }
                    break;

                case "cat":
                    if (tokens.length < 2) {
                        System.out.println("Usage: cat <filename>");
                    } else {
                        cat(tokens[1]);
                    }
                    break;

                case "cd":
                    if (tokens.length < 2) {
                        System.out.println("Usage: cd <directory>");
                    } else {
                        cd(tokens[1]);
                    }
                    break;

                case "exit":
                    System.out.println("Exiting terminal.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Command not recognized.");
            }
        }
    }

    // Method to print the current directory structure as a tree
    private void drawTree() {
        System.out.println("Current directory structure:");
        drawTreeRecursive(fileSystem.getRoot(), "");
    }

    private void drawTreeRecursive(Node node, String prefix) {
        System.out.println(prefix + node.getName());
        if (node.hasChildren()) {
            for (Node child : node.getChildren()) {
                drawTreeRecursive(child, prefix + "    "); // Indent child nodes
            }
        }
    }

    // Method to print working directory (pwd)
    private void pwd() {
        StringBuilder path = new StringBuilder();
        Node current = currentDirectory;

        while (current != null) {
            path.insert(0, "/" + current.getName());
            current = current.getParent();
        }

        System.out.println(path.toString());
    }

    // Method to list directory contents (ls)
    private void ls() {
        if (currentDirectory.hasChildren()) {
            for (Node child : currentDirectory.getChildren()) {
                System.out.print(child.getName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No contents.");
        }
    }

    // Method to create a new directory (mkdir)
    private void mkdir(String directoryName) {
        Node newDirectory = new Node(directoryName, true);
        currentDirectory.addChild(newDirectory);
        System.out.println("Directory created: " + directoryName);
    }

    // Method to create a new file (touch)
    private void touch(String fileName) {
        Node newFile = new Node(fileName, false);
        currentDirectory.addChild(newFile);
        System.out.println("File created: " + fileName);
    }

    // Method to write content to a file
    private void write(String fileName, String content) {
        Node file = currentDirectory.getChildByName(fileName);
        if (file != null && !file.isDirectory()) {
            file.writeContent(content); // Ensure the writeContent method is implemented in Node
            System.out.println("Content written to " + fileName);
        } else {
            System.out.println("File not found or it's a directory.");
        }
    }

    // Method to read and display file content (cat)
    private void cat(String fileName) {
        Node file = currentDirectory.getChildByName(fileName);
        if (file != null && !file.isDirectory()) {
            System.out.println(file.getContent());
        } else {
            System.out.println("File not found or it's a directory.");
        }
    }

    // Method to change directories (cd)
    private void cd(String directoryName) {
        if (directoryName.equals("..")) {
            // Navigate to the parent directory
            if (currentDirectory.getParent() != null) {
                currentDirectory = currentDirectory.getParent();
            } else {
                System.out.println("Already at the root directory.");
            }
        } else if (directoryName.equals("~")) {
            // Navigate to the home directory (root)
            currentDirectory = fileSystem.getRoot();
        } else {
            // Navigate to a child directory
            Node child = currentDirectory.getChildByName(directoryName);
            if (child != null && child.isDirectory()) {
                currentDirectory = child;
            } else {
                System.out.println("Directory not found.");
            }
        }
    }

    // Method to display help
    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("ls - List contents of the current directory");
        System.out.println("pwd - Print the current working directory");
        System.out.println("mkdir <directory> - Create a new directory");
        System.out.println("touch <filename> - Create a new file");
        System.out.println("write <filename> <content> - Write content to a file");
        System.out.println("cat <filename> - Display the contents of a file");
        System.out.println("cd <directory> - Change directory (use '..' to go up, '~' to go home)");
        System.out.println("exit - Exit the terminal");
    }

    public static void main(String[] args) {
        BashTerminal terminal = new BashTerminal();
        terminal.startTerminal();
    }
}
