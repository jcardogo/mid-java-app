package taskmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to Task Manager!");
        while (true) {
            System.out.println("\nOptions: [list, add, done, exit]");
            System.out.print("> ");
            input = scanner.nextLine().trim();

            switch (input) {
                case "list":
                    manager.listTasks();
                    break;
                case "add":
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    manager.addTask(desc);
                    break;
                case "done":
                    System.out.print("Enter task number to mark done: ");
                    int num = Integer.parseInt(scanner.nextLine()) - 1;
                    manager.markTaskDone(num);
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
