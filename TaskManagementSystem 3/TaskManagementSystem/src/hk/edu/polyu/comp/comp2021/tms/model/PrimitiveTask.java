package hk.edu.polyu.comp.comp2021.tms.model;
import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.Application.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import java.util.Map;


public class PrimitiveTask extends TMS implements Serializable {
    /* Constructors */
    public PrimitiveTask (){
        super ();
    }
    public PrimitiveTask(String name, String description, double duration){
        super (name, description, duration);
    }
    // HashMap containing task information
    //Map<String, TMS> taskMap = Application.taskMap;
    public void create(String instruction, Map <String, TMS> taskMap) {
        // Method to create an object of the primitive task
        String[] tokens = instruction.split(" ");
        if (tokens.length >= 5 && tokens[4].strip().equals(",") && isName(tokens[1])) {
            String name = tokens[1];
            String description = tokens[2];
            double duration = Double.parseDouble(tokens[3]);
            //List<String> prerequisites = Arrays.asList(tokens[4].split(","));
            if ((!taskMap.containsKey(name)) || taskMap.isEmpty()) {
                TMS TMS = new PrimitiveTask(name, description, duration);
                taskMap.put(name, TMS);
                System.out.println("Simple task created: " + name);
            } else {
                System.out.println("Task with the same name already exists: " + name);
            }
            //System.out.print (prerequisites);
        } else {
            System.out.println("Invalid CreateSimpleTask command format.");
        }
    }
    public String delete (String instruction, Map <String, TMS> taskMap) {
        // add code
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        if (taskMap.containsKey(name)) {
            taskMap.remove(name);
            return "Task deleted: " + name;
        }
        return "Task not found: " + name;
    }


    public void changeTask (String instruction, Map<String, TMS> taskMap) {
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        String property = tokens[2];
        String newValue = tokens[3];

        if (taskMap.containsKey(name)) {
            PrimitiveTask task = (PrimitiveTask) taskMap.get(name);
            switch (property) {
                case "name":
                    task.setName(newValue);
                    break;
                case "description":
                    task.setDescription(newValue);
                    break;
                case "duration":
                    task.setDuration(Double.parseDouble(newValue));
                    break;
                default:
                    System.out.println("Invalid property for a primitive task");
                    return;
            }
            System.out.println("Task updated: " + name);
        } else {
            System.out.println("Task not found: " + name);
        }
    }
    public void printTask(String instruction, Map<String, TMS> taskMap) {
        String[] tokens = instruction.split(" ");
        if (tokens.length >= 2) {
            String name = tokens[1];
            if (taskMap.containsKey(name)) {
                TMS task = taskMap.get(name);
                System.out.println("Task Name: " + task.getName());
                System.out.println("Description: " + task.getDescription());
                if (task instanceof CompositeTask) {
                    CompositeTask compositeTask = (CompositeTask) task;
                    System.out.println("Subtasks: " + String.join(", ", compositeTask.getPrerequisites()));
                }
            } else {
                System.out.println("Task not found: " + name);
            }
        } else {
            System.out.println("Invalid PrintTask command format.");
        }}

    public double reportDuration(String taskName, Map<String, TMS> taskMap) {
        if (taskMap.containsKey(taskName)) {
            TMS task = taskMap.get(taskName);
            if (task instanceof PrimitiveTask) {
                return ((PrimitiveTask) task).getDuration();
            } else if (task instanceof CompositeTask) {
                double duration = 0;
                for (String subtaskName : task.getPrerequisites()) {
                    TMS subtask = taskMap.get(subtaskName);
                    duration += reportDuration(subtaskName, taskMap);
                }
                duration += task.getDuration();
                return duration;
            }
        }
        return 0;
    }

}
