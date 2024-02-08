import java.util.*;

public class BashTerminal {
    private Tree fileSystem;
    private Node currentDirectory;

    public BashTerminal() {
        fileSystem = new Tree("root");
        currentDirectory = fileSystem.getRoot();
    }

    public void ls() {
        System.out.println("Contents of current directory:");
        for (Node child : currentDirectory.getChildren()) {
            System.out.println(child.getName());
        }
    }

    public void cd(String directoryName) {
        Node newDirectory = currentDirectory.getChildByName(directoryName);
        if (newDirectory != null && newDirectory.hasChildren()) {
            currentDirectory = newDirectory;
        } else {
            System.out.println("Not a directory or directory not found.");
        }
    }

    public void mkdir(String directoryName) {
        fileSystem.addDirectory(currentDirectory, directoryName);
    }

    public void rmdir(String directoryName) {
        Node directoryToRemove = currentDirectory.getChildByName(directoryName);
        if (directoryToRemove != null && !directoryToRemove.hasChildren()) {
            fileSystem.removeDirectory(currentDirectory, directoryToRemove);
        } else {
            System.out.println("Directory not found or not empty.");
        }
    }

    public void pwd() {
        System.out.println("Current directory: " + currentDirectory.getName());
    }

    public void touch(String fileName) {
        Node newFile = new Node(fileName);
        currentDirectory.addChild(newFile);
    }

    public void rm(String fileName) {
        Node fileToRemove = currentDirectory.getChildByName(fileName);
        if (fileToRemove != null) {
            currentDirectory.removeChild(fileToRemove);
        } else {
            System.out.println("File not found.");
        }
    }

    public void cat(String fileName) {
        Node fileToRead = currentDirectory.getChildByName(fileName);
        if (fileToRead != null) {
            System.out.println("Contents of " + fileName + ":");
            // Read contents of the file and print
        } else {
            System.out.println("File not found.");
        }
    }

    public void help() {
        System.out.println("Available commands:");
        System.out.println("ls - List contents of current directory");
        System.out.println("cd <directory> - Change directory");
        System.out.println("mkdir <directory> - Create a new directory");
        System.out.println("rmdir <directory> - Remove an empty directory");
        System.out.println("pwd - Print current directory");
        System.out.println("touch <filename> - Create a new file");
        System.out.println("rm <filename> - Remove a file");
        System.out.println("cat <filename> - Display the contents of a file");
        System.out.println("help - Display this help message");
        System.out.println("exit - Exit the terminal");
    }

    public void startTerminal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bash Terminal!");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("$ ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");

            switch (tokens[0]) {
                case "ls":
                    ls();
                    break;
                case "cd":
                    if (tokens.length < 2) {
                        System.out.println("Usage: cd <directory>");
                    } else {
                        cd(tokens[1]);
                    }
                    break;
                case "mkdir":
                    if (tokens.length < 2) {
                        System.out.println("Usage: mkdir <directory>");
                    } else {
                        mkdir(tokens[1]);
                    }
                    break;
                case "rmdir":
                    if (tokens.length < 2) {
                        System.out.println("Usage: rmdir <directory>");
                    } else {
                        rmdir(tokens[1]);
                    }
                    break;
                case "pwd":
                    pwd();
                    break;
                case "touch":
                    if (tokens.length < 2) {
                        System.out.println("Usage: touch <filename>");
                    } else {
                        touch(tokens[1]);
                    }
                    break;
                case "rm":
                    if (tokens.length < 2) {
                        System.out.println("Usage: rm <filename>");
                    } else {
                        rm(tokens[1]);
                    }
                    break;
                case "cat":
                    if (tokens.length < 2) {
                        System.out.println("Usage: cat <filename>");
                    } else {
                        cat(tokens[1]);
                    }
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    System.out.println("Exiting terminal.");
                    return;
                default:
                    System.out.println("Command not recognized. Type 'help' to see available commands.");
            }
        }
    }

    public static void main(String[] args) {
        BashTerminal terminal = new BashTerminal();
        terminal.startTerminal();
    }
}