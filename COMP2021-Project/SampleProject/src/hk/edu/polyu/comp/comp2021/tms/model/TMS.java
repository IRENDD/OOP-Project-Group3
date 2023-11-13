package hk.edu.polyu.comp.comp2021.tms.model;
import java.util.*;

public class Task {
    String name;
    String description;
    double duration;
    List<String> prerequisites;

    public Task(){

    }
    public Task (String name, String description, double duration, List<String> prerequisites) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.prerequisites = prerequisites;
    }

    Map<String, Task> taskMap = new HashMap<>();

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
