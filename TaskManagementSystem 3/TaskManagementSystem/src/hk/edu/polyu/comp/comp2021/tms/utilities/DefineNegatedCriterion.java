package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.io.Serializable;
import java.util.Map;

public class DefineNegatedCriterion extends Criterion implements Serializable {
//    Map<String, Criterion> criterionMap = Application.criterionMap;
//    Map<String, TMS> taskMap = Application.taskMap;

    public DefineNegatedCriterion(){ super(); }
    public DefineNegatedCriterion(String name, String name2){ super(name, name2); }

    public void create(String instruction, Map<String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1], name2 = tokens[2];
        if (tokens.length != 3) {
            System.out.println("Invalid DefineNegatedCriterion command format.");
            return;
        }
        if (criterionMap.containsKey(name)){
            System.out.println("Task with the same name already exists: " + name);
            return;
        }
        if(!isName(name)){
            System.out.println("Invalid CreateSimpleTask name format.");
            return;
        }
        if (!criterionMap.containsKey(name2)) {
            System.out.println("Key" + name2 + "not found in criterionMap");
            return;
        }

        Criterion criterion = criterionMap.get(name2);
        String property = criterion.getProperty(), op = criterion.getOp();

        DefineBasicCriterion negCriterion = null;
        switch (property) {
            case "name":
            case "description":
                if(op == "contains"){
                    negCriterion = new DefineBasicCriterion(criterion.getName(), property, "notContains", '"' + criterion.getValStr() + '"');
                } else{
                    negCriterion = new DefineBasicCriterion(criterion.getName(), property, "contains", '"' + criterion.getValStr() + '"');
                }
                break;
            case "duration":
                try {
                    if (!op.matches(">|<|>=|<=|==|!=")) {
                        System.out.println("Invalid duration op: " + op);
                        return;
                    }
                    String[] operators = { ">", "<=", "<", ">=", "==", "!=" };
                    for(int i = 0; i < operators.length; i++){
                        if(op.equals(operators[i]) && i % 2 == 0){
                            negCriterion = new DefineBasicCriterion(name, property, operators[i + 1], criterion.getVal());
                            break;
                        } else if(op.equals(operators[i]) && i % 2 == 1){
                            negCriterion = new DefineBasicCriterion(name, property, operators[i - 1], criterion.getVal());
                            break;
                        }
                    }
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Invalid duration value: " + criterion.getVal());
                    return;
                }
            default:
                if(op == "contains"){
                    negCriterion = new DefineBasicCriterion(name, property, "notContains", criterion.getValList());
                } else{
                    negCriterion = new DefineBasicCriterion(name, property, "contains", criterion.getValList());
                }
                break;
        }
        criterionMap.put(name, negCriterion);
        System.out.println("Negative criteria of" + name2 + "created: " + name);
    }
    public void search(String instruction, Map<String, Criterion> criterionMap, Map <String, TMS>taskMap){
        // define the search function
    }
}
