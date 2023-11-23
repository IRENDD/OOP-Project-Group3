package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TMSTest {

    @Test
    public void testPrimitiveTaskCreation() {
        // Test case for PrimitiveTask creation
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);

        Assertions.assertEquals("Task1", task.getName());
        Assertions.assertEquals("Description for Task1", task.getDescription());
        Assertions.assertEquals(2.5, task.getDuration());
    }

    @Test
    public void testCompositeTaskCreation() {
        // Test case for CompositeTask creation
        CompositeTask task = new CompositeTask("CompositeTask1", "Description for CompositeTask1",
                new String[]{"Task1", "Task2"});

        Assertions.assertEquals("CompositeTask1", task.getName());
        Assertions.assertEquals("Description for CompositeTask1", task.getDescription());
        Assertions.assertArrayEquals(new String[]{"Task1", "Task2"}, task.getPrerequisites().toArray());
    }

    @Test
    public void testTaskDeletion() {
        // Test case for task deletion
        TMS tms = new TMS() {
            @Override
            public void create(String instruction) {

            }

            @Override
            public String delete(String instruction) {
                return null;
            }

            @Override
            public void changeTask(String instruction) {

            }

            @Override
            public void printTask(String instruction) {

            }

            @Override
            public double reportDuration(String instruction) {
                return 0;
            }
        };
        PrimitiveTask task = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        tms.addTask(task);

        Assertions.assertTrue(tms.containsTask("Task1"));

        tms.deleteTask("Task1");

        Assertions.assertFalse(tms.containsTask("Task1"));
    }

    @Test
    public void testTaskPropertyChange() {
        // Test case for task property change
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
        // Test case for reporting task duration
        TMS tms = new TMS() {
            @Override
            public void create(String instruction) {

            }

            @Override
            public String delete(String instruction) {
                return null;
            }

            @Override
            public void changeTask(String instruction) {

            }

            @Override
            public void printTask(String instruction) {

            }

            @Override
            public double reportDuration(String instruction) {
                return 0;
            }
        }; // Instantiate a concrete subclass of TMS

        // Create tasks
        PrimitiveTask task1 = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        PrimitiveTask task2 = new PrimitiveTask("Task2", "Description for Task2", 3.0);
        CompositeTask compositeTask = new CompositeTask("CompositeTask1", "Description for CompositeTask1",
                new String[]{"Task1", "Task2"});

        tms.addTask(task1);
        tms.addTask(task2);
        tms.addTask(compositeTask);

        Assertions.assertEquals(2.5, tms.reportDuration("Task1"));
        Assertions.assertEquals(3.0, tms.reportDuration("Task2"));
        Assertions.assertEquals(5.5, tms.reportDuration("CompositeTask1"));
    }

    @Test
    public void testReportEarliestFinishTime() {
        // Test case for reporting the earliest finish time
        TMS tms = new TMS() {
            @Override
            public void create(String instruction) {

            }

            @Override
            public String delete(String instruction) {
                return null;
            }

            @Override
            public void changeTask(String instruction) {

            }

            @Override
            public void printTask(String instruction) {

            }

            @Override
            public double reportDuration(String instruction) {
                return 0;
            }
        };
        PrimitiveTask task1 = new PrimitiveTask("Task1", "Description for Task1", 2.5);
        PrimitiveTask task2 = new PrimitiveTask("Task2", "Description for Task2", 3.0);
        CompositeTask compositeTask = new CompositeTask("CompositeTask1", "Description for CompositeTask1",
                new String[]{"Task1", "Task2"});

        tms.addTask(task1);
        tms.addTask(task2);
        tms.addTask(compositeTask);

        Assertions.assertEquals(2.5, tms.getEarliestFinishTime());
        Assertions.assertEquals(3.0, tms.getEarliestFinishTime());
        Assertions.assertEquals(5.5, tms.getEarliestFinishTime());
    }

}