package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.io.Serializable;
import java.util.Map;

public class DefineBinaryCriterion extends Criterion implements Serializable {
//    Map<String, Criterion> criterionMap = Application.criterionMap;
//    Map<String, TMS> taskMap = Application.taskMap;

    public DefineBinaryCriterion(){ super(); }
    public DefineBinaryCriterion(String name, String name2, String logicOp, String name3){ super(name, name2, logicOp, name3); }

    public void create(String instruction, Map<String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1], name2 = tokens[2], logicOp = tokens[3], name3 = tokens[4];
        if (tokens.length != 5) {
            System.out.println("Invalid DefineBinaryCriterion command format.");
            return;
        }
        if (criterionMap.containsKey(name)){
            System.out.println("Criteria with the same name already exists: " + name);
            return;
        }
        if(!isName(name)){
            System.out.println("Invalid DefineBinaryCriterion name format.");
            return;
        }
        if (!criterionMap.containsKey(name2)) {
            System.out.println("Key" + name2 + "not found in criterionMap");
            return;
        }
        if (!criterionMap.containsKey(name3)) {
            System.out.println("Key" + name3 + "not found in criterionMap");
            return;
        }

        Criterion criterion = criterionMap.get(name2);
        Criterion criterion2 = criterionMap.get(name3);
    }
    public void search(String instruction, Map<String, Criterion> criterionMap, Map <String, TMS>taskMap){
        // define search function
    }
}
