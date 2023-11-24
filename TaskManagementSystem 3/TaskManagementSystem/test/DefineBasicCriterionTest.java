import hk.edu.polyu.comp.comp2021.tms.utilities.*;
import org.junit.Test;
import hk.edu.polyu.comp.comp2021.tms.model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DefineBasicCriterionTest {

    @Test
    public void testSearch() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Map<String, TMS> taskMap = new HashMap<>();
        Map<String, Criterion> criterionMap = new HashMap<>();

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

        taskMap.put("Task1", task1);
        taskMap.put("Task2", task2);

        Criterion criterion = new DefineBasicCriterion("Criterion1", "name", "contains", "Task1");
        criterionMap.put("Criterion1", criterion);

        DefineBasicCriterion defineBasicCriterion = new DefineBasicCriterion();

        defineBasicCriterion.search("search Criterion1", taskMap, criterionMap);

        String expectedOutput = "Tasks matching the given criterion:\nTask1\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLoad() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Map<String, Criterion> criterionMap = new HashMap<>();

        DefineBasicCriterion defineBasicCriterion = new DefineBasicCriterion();

        defineBasicCriterion.create("load Criterion1 name contains Task1", criterionMap);

        assertEquals("Criteria created: Criterion1\n", outContent.toString());
        assertEquals(1, criterionMap.size());
    }
}