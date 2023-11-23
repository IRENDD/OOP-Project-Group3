package hk.edu.polyu.comp.comp2021.tms.model;
import hk.edu.polyu.comp.comp2021.tms.Application;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import static hk.edu.polyu.comp.comp2021.tms.Application.taskMap;

public class CompositeTask extends TMS implements Serializable {
    public CompositeTask (){
        super ();
    }
    public CompositeTask(String name, String description, String[] subtasks){
        super(name, description, subtasks);
    }
    //Map<String, TMS> taskMap = Application.taskMap;
    public void create(String instruction, Map<String,TMS>taskMap) {
        // add code
        String[] tokens = instruction.split(" ");
        if (tokens.length >= 4) {
            String name = tokens[1];
            String description = tokens[2];
            String[] subtaskNames = tokens[3].split(",");
            if (!taskMap.containsKey(name)) {
                TMS TMS = new CompositeTask (name, description, subtaskNames); // Duration is 0 for composite tasks
                for (String subtaskName : subtaskNames) {
                    if (!taskMap.containsKey(subtaskName)) {
                        System.out.println("Failed to Create Composite Task.\nSubtask not found: " + subtaskName);
                        return;
                    }
                }
                taskMap.put(name, TMS);
                System.out.println("Composite task created: " + name);
            } else {
                System.out.println("Task with the same name already exists: " + name);
            }
        } else {
            System.out.println("Invalid CreateCompositeTask command format.");
        }
    }
    public String delete (String instruction, Map<String,TMS>taskMap) {
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        if (taskMap.containsKey(name)) {
            TMS task = taskMap.get(name);
            if (task instanceof CompositeTask) {
                // Check if any subtasks are prerequisites for other tasks
                for (String subtaskName : ((CompositeTask) task).getPrerequisites()) {
                    for (TMS t : taskMap.values()) {
                        if (t.getPrerequisites().contains(subtaskName)) {
                            return "Cannot delete, subtask is a prerequisite for other tasks: " + subtaskName;
                        }
                    }
                }
                taskMap.remove(name);
                return "Composite task deleted: " + name;
            }
        }
        return "Task not found: " + name;
    }
    public void changeTask (String instruction, Map<String,TMS>taskMap) {
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        String property = tokens[2];
        String newValue = tokens[3];

        if (taskMap.containsKey(name)) {
            CompositeTask task = (CompositeTask) taskMap.get(name);
            switch (property) {
                case "name":
                    task.setName(newValue);
                    break;
                case "description":
                    task.setDescription(newValue);
                    break;
                case "subtasks":
                    task.setPrerequisites(Arrays.asList(newValue.split(",")));
                    break;
                default:
                    System.out.println("Invalid property for a composite task");
                    return;
            }
            System.out.println("Task updated: " + name);
        } else {
            System.out.println("Task not found: " + name);
        }
    }

    // [REQ5] Print Task
    public void printTask (String instruction, Map<String,TMS>taskMap) {
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        if (taskMap.containsKey(name)) {
            TMS task = taskMap.get(name);
            System.out.println("Task Name: " + task.getName());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Subtasks: " + String.join(", ", task.getPrerequisites()));
        } else {
            System.out.println("Task not found: " + name);
        }
    }
    public double reportDuration(String taskName, Map<String,TMS>taskMap) {
        if (taskMap.containsKey(taskName)) {
            TMS task = taskMap.get(taskName);
            if (task instanceof PrimitiveTask) {
                return ((PrimitiveTask) task).getDuration();
            } else if (task instanceof CompositeTask) {
                //PrimitiveTask primitiveTaskTask = (PrimitiveTask) task;
                double duration = 0;
                for (String subtaskName : task.getPrerequisites()) {
                    TMS subtask = taskMap.get(subtaskName);
                    duration += subtask.getDuration();
                }
                duration += task.getDuration();
                return duration;
            }
        } else {
            return 0;
        }
        return 0;
    }
    public double reportEarliestFinishTime(String taskName, Map<String,TMS>taskMap) {
        if (taskMap.containsKey(taskName)) {
            TMS task = taskMap.get(taskName);
            if (task instanceof PrimitiveTask) {
                return ((PrimitiveTask) task).getEarliestFinishTime();
            } else if (task instanceof CompositeTask) {
               // PrimitiveTask primitiveTaskTask = (PrimitiveTask) task;
                double earliestFinishTime = 0;
                for (String subtaskName : task.getPrerequisites()) {
                    TMS subtask = taskMap.get(subtaskName);
                    earliestFinishTime = Math.max(earliestFinishTime, subtask.getEarliestFinishTime());
                }
                earliestFinishTime = Math.max(earliestFinishTime, task.getDuration());
                return earliestFinishTime;
            }
        }
        return 0;
    }
}
