package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.Application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * Abstract Class used to create Primitive and Composite Tasks.
 * The class also implements the Serializable interface which is used
 * for saving and loading its contents to a file.
 */
public abstract class TMS implements Serializable {
    private String name;
    private String description;
    private double duration;
    private List<String> prerequisites;

    /**
     * Default constructor for the TMS class. <p>
     * This no-argument constructor that's used for the initialization of its children.
     */
    public TMS (){
        // Empty constructors for children initialization
    }

    /** Constructors for TMS initializing primitive task
     *@param name contains the name of the Primitive Task
     * @param description contains the description of the task
     * @param duration contains the time needed to complete the task*/
    public TMS (String name, String description, double duration){
        // Constructor used for Primitive Tasks
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    /** Constructors for TMS for initializing composite task
     *@param name contains the name of the Primitive Task
     * @param description contains the description of the task
     * @param subtaskNames contains the time needed to complete the task*/
    public TMS (String name, String description, String[] subtaskNames){
        // Constructor used for Composite Tasks
        this.name = name;
        this.description = description;
        this.prerequisites = Arrays.asList (subtaskNames);
    }

    /** Placeholder function in Application
     * They aid with the initialization of Application.
     * Defined Function: made to add a new element to the HashMap
     *
     *@param task contains the name of the Primitive Task
     * @param taskMap contains the description of the task
     */
    public void addTask(TMS task, Map<String, TMS> taskMap) {
        taskMap.put(task.getName(), task);
    }

    /** Placeholder function in Application
     * They aid with the initialization of Application.
     * Defined Function: made to add a new element to the HashMap. Slightly different function header
     *
     *@param task contains the name of the Primitive Task
     * @param taskMap contains the description of the task
     */
    public void addTask(PrimitiveTask task, Map<String, TMS> taskMap) {
        taskMap.put(task.getName(), task);
    }

    /** Placeholder function in Application
     * They aid with the initialization of Application.
     * Defined Function: checks whether the taskMap contains a particular task
     *
     *@param taskName contains the name of the Primitive Task
     * @param taskMap contains the description of the task
     * @return boolean evaluates to true if the given task has already been defined by the user.
     */
    public boolean containsTask(String taskName, Map<String, TMS> taskMap) {
        return taskMap.containsKey(taskName);
    }

    /** Placeholder function in Application
     * They aid with the initialization of Application.
     * Defined Function: removes an element from within the task map
     *
     *@param taskName contains the name of the Primitive Task
     * @param taskMap contains the description of the task
     */
    public void deleteTask(String taskName, Map<String, TMS> taskMap) {
        taskMap.remove(taskName);
    }

    /** Placeholder function in Application
     * They aid with the initialization of Application.
     * Defined Function: returns the time duration value of the function
     *
     * @return double number that indicates the time taken to finish the task
     */
    public double getEarliestFinishTime() {
        return getDuration();
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of the TMS object that calls it
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return String the name of the task
     */
    public String getName() {
        return name;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of the TMS object that calls it
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return String the name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the description of the TMS object that calls it.
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return String the description of the task
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the time taken to complete the TMS object that calls it.
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return double the duration of the task
     */
    public double getDuration() {
        return duration;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of the TMS object that calls it
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return String the name of the task
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns the name of all the pre-requisites required to complete the
     *                      task that calls it.
     *                      It includes both Primtive and Composite Tasks.
     *
     * @return List<String></> a list containing the names of all the pre-requisites
     */
    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if the current task is Primitive
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return boolean if the task is a primitive task
     */
    public boolean isPrimitive (String name, Map<String, TMS> taskMap){
        //String [] tokens = instruction.split (" ");
        if (taskMap.containsKey(name)){
            if (taskMap.get(name) instanceof PrimitiveTask) return true;
        }
        return false;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if the name arguments satisfying the naming
     *                      conditions
     *                      It includes both Primtive and Composite Tasks.
     *
     * @return boolean True if and only if the taskname follows the naming requirements
     *  mentioned in the question.
     */
    public boolean isName(String name){
        if(name.length() > 8 || (name.charAt(0) >= '0' && name.charAt(0) <= '9') || !name.matches("[a-zA-Z0-9]+")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** Common function - For all types of tasks
     * Defined Function: returns true if and only if the given String is a description style entry
     *                      It checks whether the format of the desccription satisfyies the naming
     *                      criteria
     *                     It includes both Primtive and Composite Tasks.
     *
     * @return boolean True if and only if the function evaluates it to true.
     */
    public boolean isDescription(String description) {
        if(!description.matches("^[a-zA-Z0-9-]+$")){
            System.out.println("Incorrect input. Try again!");
            return false;
        }
        return true;
    }

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Creates a task object of either primitive or composite
     *                      It implementation varies...
     *
     */
    public abstract void create(String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Deletes a particular task from the system
     *                      It implementation varies...
     *
     * @return String value that contains the name of the deleted task
     */
    public abstract String delete (String instruction,Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Changes the features of an already defined task
     *                      It implementation varies based on the instance
     *                      of the defined class
     *
     */
    public abstract void changeTask (String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: Prints all the tasks defined in the system
     *                      As it musts work for both Primitive and Composite
     *                      tasks, it's implementation differs slightly from
     *                      each other
     */
    public abstract void printTask (String instruction, Map<String,TMS>taskMap);

    /** ABSTRACT function - For all types of tasks
     * Defined Function: States the time taken to complete a particular task
     *                      It implementation varies...
     *
     * @return double value that consists of the time to complete a particular task x.
     */
    public abstract double reportDuration (String instruction, Map<String,TMS>taskMap);

}
