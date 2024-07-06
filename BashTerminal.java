import java.util.Scanner;

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
            System.out.println(child.getName() + (child.isDirectory() ? "/" : ""));
        }
    }

    public void cd(String directoryName) {
        if (directoryName.equals("..")) {
            if (currentDirectory.getParent() != null) {
                currentDirectory = currentDirectory.getParent();
            } else {
                System.out.println("Already at the root directory.");
            }
        } else {
            Node newDirectory = currentDirectory.getChildByName(directoryName);
            if (newDirectory != null && newDirectory.isDirectory()) {
                currentDirectory = newDirectory;
            } else {
                System.out.println("Not a directory or directory not found.");
            }
        }
    }

    public void mkdir(String directoryName) {
        if (currentDirectory.getChildByName(directoryName) == null) {
            fileSystem.addDirectory(currentDirectory, directoryName);
        } else {
            System.out.println("Directory already exists.");
        }
    }

    public void rmdir(String directoryName) {
        Node directoryToRemove = currentDirectory.getChildByName(directoryName);
        if (directoryToRemove != null && directoryToRemove.isDirectory() && !directoryToRemove.hasChildren()) {
            fileSystem.removeDirectory(currentDirectory, directoryToRemove);
        } else {
            System.out.println("Directory not found or not empty.");
        }
    }

    public void pwd() {
        Node dir = currentDirectory;
        StringBuilder path = new StringBuilder(dir.getName());
        while (dir.getParent() != null) {
            dir = dir.getParent();
            path.insert(0, dir.getName() + "/");
        }
        System.out.println("Current directory: /" + path.toString());
    }

    public void touch(String fileName) {
        if (currentDirectory.getChildByName(fileName) == null) {
            Node newFile = new Node(fileName, false);
            currentDirectory.addChild(newFile);
        } else {
            System.out.println("File already exists.");
        }
    }

    public void rm(String fileName) {
        Node fileToRemove = currentDirectory.getChildByName(fileName);
        if (fileToRemove != null && !fileToRemove.isDirectory()) {
            currentDirectory.removeChild(fileToRemove);
        } else {
            System.out.println("File not found or is a directory.");
        }
    }

    public void cat(String fileName) {
        Node fileToRead = currentDirectory.getChildByName(fileName);
        if (fileToRead != null && !fileToRead.isDirectory()) {
            System.out.println("Contents of " + fileName + ":");
            // Simulate file content
            System.out.println("This is a file content simulation.");
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
            String command = scanner.nextLine().trim();
            String[] tokens = command.split("\\s+");

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
                    scanner.close();
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
