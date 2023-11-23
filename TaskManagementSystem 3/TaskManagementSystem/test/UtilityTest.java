import hk.edu.polyu.comp.comp2021.tms.utilities.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {

    @Test
    public void testCreate_ExistingCriterion() {
        DefineNegatedCriterion criterion = new DefineNegatedCriterion();
        Map<String, Criterion> criterionMap = new HashMap<>();
        criterionMap.put("criterion1", new DefineBasicCriterion("criterion1", "name", "contains", "task1"));
        criterion.create("define_negated_criterion criterion1 criterion2", criterionMap);

// Assert that the existing criterion is detected and no criterion is created
        assertEquals(1, criterionMap.size());
        assertFalse(criterionMap.containsKey("criterion2"));
    }

    @Test
    public void testCreate_InvalidNameFormat() {
        DefineNegatedCriterion criterion = new DefineNegatedCriterion();
        Map<String, Criterion> criterionMap = new HashMap<>();
        criterion.create("define_negated_criterion 123 name2", criterionMap);

// Assert that the invalid name format is detected and no criterion is created
        assertTrue(criterionMap.isEmpty());}
    @Test
    public void testCreate_InvalidCommandFormat() {
        DefineBinaryCriterion criterion = new DefineBinaryCriterion();
        Map<String, Criterion> criterionMap = new HashMap<>();


        criterion.create("define_binary_criterion name name2", criterionMap);

// Assert that the invalid command format is detected and no criterion is created
        assertTrue(criterionMap.isEmpty());
    }

    @Test
    public void testIsName_ValidName() {
        assertTrue(Criterion.isName("criterion1"));
    }

    @Test
    public void testIsName_InvalidName() {
        assertFalse(Criterion.isName("123"));
    }

    @Test
    public void testIsDescription_ValidDescription() {
        assertTrue(Criterion.isDescription("description1"));
    }

    @Test
    public void testIsDescription_InvalidDescription() {
        assertFalse(Criterion.isDescription("description@1"));
    }






}