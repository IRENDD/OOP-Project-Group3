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
    public void testReportEarliestFinishTime_PrimitiveTask() {
        Map<String, TMS> taskMap = new HashMap<>();
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        taskMap.put(task.getName(), task);

        // Calculate the earliest finish time for the primitive task
        double earliestFinishTime = task.getDuration();

        // Verify the result
        Assertions.assertEquals(2.5, earliestFinishTime);
    }}