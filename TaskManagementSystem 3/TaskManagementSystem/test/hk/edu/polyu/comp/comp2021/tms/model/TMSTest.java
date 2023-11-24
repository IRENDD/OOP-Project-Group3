package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class TMSTest {

    @Test
    public void testPrimitiveTaskCreation() {
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);

        Assertions.assertEquals("Task1", task.getName());
        Assertions.assertEquals("Description for Task1", task.getDescription());
        Assertions.assertEquals(2.5, task.getDuration());
    }

    @Test
    public void testCompositeTaskCreation() {
        CompositeTask task = new CompositeTask("CompositeTask1", "Description for CompositeTask1",
                new String[]{"Task1", "Task2"});

        Assertions.assertEquals("CompositeTask1", task.getName());
        Assertions.assertEquals("Description for CompositeTask1", task.getDescription());
        Assertions.assertArrayEquals(new String[]{"Task1", "Task2"}, task.getPrerequisites().toArray());
    }

    @Test
    public void testTaskDeletion() {
        Map<String, TMS> taskMap = new HashMap<>();
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        taskMap.put(task.getName(), task);

        Assertions.assertTrue(taskMap.containsKey("Task1"));

        taskMap.remove("Task1");

        Assertions.assertFalse(taskMap.containsKey("Task1"));
    }

    @Test
    public void testTaskPropertyChange() {
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);

        Assertions.assertEquals("Task1", task.getName());
        Assertions.assertEquals("Description for Task1", task.getDescription());

        task.setName("NewTaskName");
        task.setDescription("New description");

        Assertions.assertEquals("NewTaskName", task.getName());
        Assertions.assertEquals("New description", task.getDescription());
    }

    @Test
    public void testReportDuration() {
        PrimitiveTask task1 = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        PrimitiveTask task2 = new PrimitiveTask("Task2", "Description for Task2", 3.0);
        CompositeTask compositeTask = new CompositeTask("CompositeTask1", "Description for CompositeTask1",
                new String[]{"Task1", "Task2"});

        Map<String, TMS> taskMap = new HashMap<>();
        taskMap.put(task1.getName(), task1);
        taskMap.put(task2.getName(), task2);
        taskMap.put(compositeTask.getName(), compositeTask);

        Assertions.assertEquals(2.5, task1.reportDuration(task1.getName(), taskMap));
        Assertions.assertEquals(3.0, task2.reportDuration(task2.getName(), taskMap));
        Assertions.assertEquals(5.5, compositeTask.reportDuration(compositeTask.getName(), taskMap));
    }

    @Test
    public void testReportEarliestFinishTime_CompositeTask() {
        Map<String, TMS> taskMap = new HashMap<>();
        CompositeTask task = new CompositeTask("Task1", "Description for Task1",
                new String[]{"Subtask1", "Subtask2"});
        taskMap.put(task.getName(), task);

        // Add subtasks to the task map
        taskMap.put("Subtask1", new PrimitiveTask("Subtask1", "Description for Subtask1", 1.0));
        taskMap.put("Subtask2", new PrimitiveTask("Subtask2", "Description for Subtask2", 2.0));

        // Calculate the earliest finish time for the composite task
        double earliestFinishTime = task.reportEarliestFinishTime("Task1", taskMap);

        // Verify the result
        Assertions.assertEquals(3.0, earliestFinishTime);
    }

    @Test
    public double reportEarliestFinishTime(String taskName, Map<String, TMS> taskMap) {
        double earliestFinishTime = 0.0;
        TMS task = taskMap.get(taskName);

        if (task instanceof CompositeTask) {
            CompositeTask compositeTask = (CompositeTask) task;
            for (String subtaskName : compositeTask.getPrerequisites()) {
                double subtaskFinishTime = compositeTask.reportEarliestFinishTime(subtaskName, taskMap);
                earliestFinishTime = Math.max(earliestFinishTime, subtaskFinishTime);
            }
        } else if (task instanceof PrimitiveTask) {
            earliestFinishTime = task.getDuration();
        }

        earliestFinishTime += task.getDuration();
        return earliestFinishTime;
    }

    @Test
    public void testConstructorAndGetters() {
        TMS task1 = new TMS("Task1", "Description1", 2.5) {
            @Override
            public void create(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public String delete(String instruction, Map<String, TMS> taskMap) {
                return null;
            }

            @Override
            public void changeTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public void printTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public double reportDuration(String instruction, Map<String, TMS> taskMap) {
                return 0;
            }
        };
        Assertions.assertEquals("Task1", task1.getName());
        Assertions.assertEquals("Description1", task1.getDescription());
        Assertions.assertEquals(2.5, task1.getDuration(), 0.001);

        TMS task2 = new TMS("Task2", "Description2", 1.5) {
            @Override
            public void create(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public String delete(String instruction, Map<String, TMS> taskMap) {
                return null;
            }

            @Override
            public void changeTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public void printTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public double reportDuration(String instruction, Map<String, TMS> taskMap) {
                return 0;
            }
        };
        Assertions.assertEquals("Task2", task2.getName());
        Assertions.assertEquals("Description2", task2.getDescription());
        Assertions.assertEquals(1.5, task2.getDuration(), 0.001);
    }

    @Test
    public void testSetters() {
        TMS task = new TMS("Task1", "Description1", 2.5) {
            @Override
            public void create(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public String delete(String instruction, Map<String, TMS> taskMap) {
                return null;
            }

            @Override
            public void changeTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public void printTask(String instruction, Map<String, TMS> taskMap) {

            }

            @Override
            public double reportDuration(String instruction, Map<String, TMS> taskMap) {
                return 0;
            }
        };

        task.setName("NewTaskName");
        Assertions.assertEquals("NewTaskName", task.getName());

        task.setDescription("NewDescription");
        Assertions.assertEquals("NewDescription", task.getDescription());

        task.setDuration(3.0);
        Assertions.assertEquals(3.0, task.getDuration(), 0.001);
    }
}