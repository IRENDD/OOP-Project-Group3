package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.util.Map;

public abstract class Criterion {
    private String name;
    private String name2;
    private String name3;

    private String property;
    private String op;
    private double val;
    private String valStr;
    private String[] valList;
    private String logicOp;

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
        this.valList = val;
    }
    public Criterion(String name, String name2){
        this.name = name;
        this.name2 = name2;
    }
//    public Criterion(String name, String name2, String logicOp, String name3){
//        this.name = name;
//        this.name2 = name2;
//        this.logicOp = logicOp;
//        this.name3 = name3;
//    }

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

    public String[] getValList() { return valList; }
    public void setValList(String[] valList) { this.valList = valList; }

    public String getName2() { return name2; }
    public void setName2(String name2) { this.name2 = name2; }

    public String getName3() { return name3; }
    public void setName3(String name3) { this.name3 = name3; }

    public String getLogicOp() { return logicOp; }

    public void setLogicOp(String logicOp) { this.logicOp = logicOp; }

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

    public abstract void create(String instruction, Map<String, Criterion> criterionMap);
}
