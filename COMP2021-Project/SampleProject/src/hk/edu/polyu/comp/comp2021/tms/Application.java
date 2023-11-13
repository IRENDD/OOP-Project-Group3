package hk.edu.polyu.comp.comp2021.tms;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;

package hk.edu.polyu.comp.comp2021.tms;
import java.util.*;
import hk.edu.polyu.comp.comp2021.tms.model.Task;

import java.util.*;
import java.util.stream.Collectors; 

public class Application{
    Map<String, Task> taskMap = new HashMap<>();

    public static void main(String[] args) {
        Task tms = new Task();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (CreateSimpleTask or CreateCompositeTask):");
            String input = scanner.nextLine();

            if (input.startsWith("CreateSimpleTask")) {
                String[] tokens = input.split(" ");
                if (tokens.length >= 5) {
                    String name = tokens[1];
                    String description = tokens[2];
                    double duration = Double.parseDouble(tokens[3]);
                    List<String> prerequisites = Arrays.asList(tokens[4].split(","));
                    tms.createSimpleTask(name, description, duration, prerequisites);
                } else {
                    System.out.println("Invalid CreateSimpleTask command format.");
                }
            } else if (input.startsWith("CreateCompositeTask")) {
                String[] tokens = input.split(" ");
                if (tokens.length >= 4) {
                    String name = tokens[1];
                    String description = tokens[2];
                    String[] subtaskNames = tokens[3].split(",");
                    tms.createCompositeTask(name, description, subtaskNames);
                } else {
                    System.out.println("Invalid CreateCompositeTask command format.");
                }
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }

    public void createSimpleTask(String name, String description, double duration, List<String> prerequisites) {
        if (!taskMap.containsKey(name)) {
            Task task = new Task(name, description, duration, prerequisites);
            taskMap.put(name, task);
            System.out.println("Simple task created: " + name);
        } else {
            System.out.println("Task with the same name already exists: " + name);
        }
    }

    public void createCompositeTask(String name, String description, String[] subtaskNames) {
        if (!taskMap.containsKey(name)) {
            List<String> subtaskList = Arrays.asList(subtaskNames);
            Task task = new Task(name, description, 0.0, subtaskList); // Duration is 0 for composite tasks
            taskMap.put(name, task);
            System.out.println("Composite task created: " + name);

            for (String subtaskName : subtaskNames) {
                if (!taskMap.containsKey(subtaskName)) {
                    System.out.println("Subtask not found: " + subtaskName);
                    taskMap.remove(name); // Remove the composite task if any subtask is missing
                    return;
                }
            }
        } else {
            System.out.println("Task with the same name already exists: " + name);
        }
    }
}


