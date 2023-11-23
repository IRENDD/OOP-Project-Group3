package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


public abstract class TMS {
    private String name;
    private String description;
    private double duration;
    private List<String> prerequisites;
    /* Constructors for the abstract class */
    public TMS (){
        // Empty constructors for children initialization
    }
    public TMS (String name, String description, double duration){
        // Constructor used for Primitive Tasks
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
    public TMS (String name, String description, String[] subtaskNames){
        // Constructor used for Composite Tasks
        this.name = name;
        this.description = description;
        this.prerequisites = Arrays.asList (subtaskNames);
    }
    //Map<String, TMS> taskMap = Application.taskMap;

    /* Functions to return values from object fields */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public boolean isPrimitive (String name, Map<String, TMS> taskMap){
        //String [] tokens = instruction.split (" ");
        if (taskMap.containsKey(name)){
            if (taskMap.get(name) instanceof PrimitiveTask) return true;
        }
        return false;
    }
    public boolean isName(String name){
        if(name.length() > 8 || (name.charAt(0) >= '0' && name.charAt(0) <= '9') || !name.matches("[a-zA-Z0-9]+")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }
    public boolean isDescription(String description) {
        if(!description.matches("^[a-zA-Z0-9-]+$")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /* Abstract Functions to be included in the implementation of children classes */
    public abstract void create(String instruction, Map<String,TMS>taskMap);
    public abstract String delete (String instruction,Map<String,TMS>taskMap);
    public abstract void changeTask (String instruction, Map<String,TMS>taskMap);
    public abstract void printTask (String instruction, Map<String,TMS>taskMap);
    public abstract double reportDuration (String instruction, Map<String,TMS>taskMap);
    public double getEarliestFinishTime() {
        return getDuration();
    }
}
