package hk.edu.polyu.comp.comp2021.tms.utilities;
import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.io.Serializable;
import java.util.Map;

public class DefineBasicCriterion extends Criterion implements Serializable {
//    Map<String, Criterion> criterionMap = Application.criterionMap;
//    Map<String, TMS> taskMap = Application.taskMap;

    public DefineBasicCriterion(){
        super();
    }
    public DefineBasicCriterion(String name, String property, String op, double val) {
        super(name, property, op, val);
    }
    public DefineBasicCriterion(String name, String property, String op, String val) {
        super(name, property, op, val);
    }
    public DefineBasicCriterion(String name, String property, String op, String[] val) { super(name, property, op, val); }

    public void create(String instruction, Map<String, Criterion> criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1], property = tokens[2];
        if (tokens.length != 5) {
            System.out.println("Invalid DefineBasicCriterion command format.");
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

        DefineBasicCriterion criteria;
        String op = tokens[3], val = tokens[4];
        switch (property) {
            case "name":
            case "description":
                criteria = new DefineBasicCriterion(name, property, "contains", '"' + val + '"');
                break;
            case "duration":
                try {
                    if(!op.matches(">|<|>=|<=|==|!=")){
                        System.out.println("Invalid duration op: " + op);
                        return;
                    }
                    criteria = new DefineBasicCriterion(name, property, op, Double.parseDouble(val));
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Invalid duration value: " + val);
                    return;
                }
            default:
                String[] valList = tokens[2].split(",");
                criteria = new DefineBasicCriterion(name, property, "contains", valList);
                break;
        }
        criterionMap.put(name, criteria);
        System.out.println("Criteria created: " + name);
    }

    public void search(String instruction, Map<String, Criterion> criterionMap, Map <String, TMS>taskMap){
        // check the logic to see if it works
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        if (tokens.length != 2) {
            System.out.println("Invalid CreateSimpleTask command format.");
            return;
        }
        if (!criterionMap.containsKey(name)) {
            System.out.println("Criterion with the given name does not exist: " + name);
            return;
        }

        Criterion criterion = criterionMap.get(name);
        for(Map.Entry<String, TMS> entry : taskMap.entrySet()){
            TMS task = entry.getValue();
            if(task.getDescription() == criterion.getProperty()){
                if(task.getDuration() == criterion.getVal()){

                } else if(task.getPrerequisites().equals(criterion.getValList())){ // fix this part

                }
            }
        }
    }

    public void printBasicCriterion(String instruction, Map<String,Criterion>criterionMap){
        String[] tokens = instruction.split(" ");
        String name = tokens[1];
        if(criterionMap.containsKey(name)){
            Criterion criterion = (Criterion) criterionMap.get(name);
            System.out.println("Task Name: " + criterion.getName());
            System.out.println("Property: " + criterion.getProperty());
            System.out.println("Operator: " + criterion.getOp());
            if(criterion.getValStr() != null){
                System.out.println("Value: " + criterion.getValStr());
            } else if(criterion.getValList() != null){
                System.out.println("Value: " + criterion.getValList());
            } else{
                System.out.println("Value: " + criterion.getVal());
            }
        }
    }

}
