import hk.edu.polyu.comp.comp2021.tms.utilities.*;
import org.junit.Test;
import hk.edu.polyu.comp.comp2021.tms.model.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CriterionTest {

    private static class TestCriterion extends Criterion {
        @Override
        public boolean containsCriterion(Criterion criterion, TMS task) {
            return super.containsCriterion(criterion, task);
        }

        // Implement other abstract methods if needed
        @Override
        public void create(String instruction, Map<String, Criterion> criterionMap) {
            // Implement if needed
        }

        @Override
        public void search(String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) {
            // Implement if needed
        }
    }

    @Test
    public void testContainsCriterion() {
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
        List<String> prerequisites = Arrays.asList("Task2", "Task3");
        task.setPrerequisites(prerequisites);

        TestCriterion criterion1 = new TestCriterion();
        criterion1.setValStr("Task1");
        criterion1.setProperty("name");

        TestCriterion criterion2 = new TestCriterion();
        criterion2.setValStr("Description1");
        criterion2.setProperty("description");

        TestCriterion criterion3 = new TestCriterion();
        criterion3.setValList(Arrays.asList("Task2", "Task3"));
        criterion3.setProperty("prerequisites");

        assertTrue(criterion1.containsCriterion(criterion1, task));
        assertTrue(criterion2.containsCriterion(criterion2, task));
        assertTrue(criterion3.containsCriterion(criterion3, task));
    }
}