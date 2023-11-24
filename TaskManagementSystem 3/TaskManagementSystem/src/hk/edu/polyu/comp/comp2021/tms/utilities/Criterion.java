package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Criterion implements Serializable {
    private String name;
    private String property;
    private String op;
    private double val;
    private String valStr;
    private List<String> valList;
    private Criterion criterion;
    private Criterion criterion2;

    public Criterion(){

    }
    public Criterion(String name, String property, String op, double val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.val = val;
    }
    public Criterion(String name, String property, String op, String val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.valStr = val;
    }
    public Criterion(String name, String property, String op, String[] val){
        this.name = name;
        this.property = property;
        this.op = op;
        this.valList = Arrays.asList(val);
    }
    public Criterion(String name, Criterion criterion, String op, Criterion criterion2){
        this.name = name;
        this.criterion = criterion;
        this.op = op;
        this.criterion2 = criterion2;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProperty() { return property; }
    public void setProperty(String property) { this.property = property; }

    public String getOp() { return op; }
    public void setOp(String op) { this.op = op; }

    public double getVal() { return val; }
    public void setVal(double val) { this.val = val; }

    public String getValStr() { return valStr; }
    public void setValStr(String valStr) { this.valStr = valStr; }

    public List<String> getValList() { return valList; }
    public void setValList(List<String> valList) { this.valList = valList; }

    public Criterion getCriterion() { return criterion; }
    public void setCriterion(Criterion criterion) { this.criterion = criterion; }

    public Criterion getCriterion2() { return criterion2; }
    public void setCriterion2(Criterion criterion2) { this.criterion2 = criterion2; }

    public static boolean isName(String name){
        if(name.length() > 8 || (name.charAt(0) >= '0' && name.charAt(0) <= '9') || !name.matches("[a-zA-Z0-9]+")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }
    public static boolean isDescription(String description) {
        if(!description.matches("^[a-zA-Z0-9-]+$")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }
    protected boolean containsCriterion(Criterion criterion, TMS task) {
        switch (criterion.getProperty()) {
            case "name":
                return criterion.getValStr().equals(task.getName());
            case "description":
                return criterion.getValStr().equals(task.getDescription());
            case "prerequisites":
                List<String> valList = criterion.getValList();
                List<String> prerequisites = task.getPrerequisites();
                boolean allPrerequisites = true;

                if(prerequisites == null) return false;
                for (String val : valList) {
                    if (!prerequisites.contains(val)) {
                        allPrerequisites = false;
                        break;
                    }
                }
                return allPrerequisites;
            default:
                return false;
        }
    }

    public boolean checkDurationCriterion(Criterion criterion, TMS task) {
        switch (criterion.getOp()) {
            case ">":
                return task.getDuration() > criterion.getVal();
            case "<":
                return task.getDuration() < criterion.getVal();
            case ">=":
                return task.getDuration() >= criterion.getVal();
            case "<=":
                return task.getDuration() <= criterion.getVal();
            case "==":
                return task.getDuration() == criterion.getVal();
            case "!=":
                return task.getDuration() != criterion.getVal();
            default:
                return false;
        }
    }
    public abstract void create(String instruction, Map <String, Criterion> criterionMap);
    public abstract void search(String instruction, Map<String, TMS> taskMap, Map <String, Criterion> criterionMap);
}
