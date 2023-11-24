package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.util.Map;
import java.io.*;
import java.util.*;

// import static hk.edu.polyu.comp.comp2021.tms.Application.taskMap;

public class UserControl {
    public static void saveMap(String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) throws IOException {
        String[] inputArray = instruction.split(" ");
        if (inputArray.length == 2) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inputArray[1]))) {
                oos.writeObject(taskMap);
                oos.writeObject(criterionMap);
                System.out.println("Files were updated successfully");
            } catch (FileNotFoundException e) {
                System.out.println("The file path was not found. Try again");
            } catch (NotSerializableException e) {
                System.out.println("There is an implementation error.");
            }
        } else {
            System.out.println("Invalid format for Store function");
        }
    }

    public static void loadMap(String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) throws IOException, ClassNotFoundException {
        String[] inputArray = instruction.split(" ");
        if (inputArray.length == 2) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputArray[1]))) {
                taskMap.clear();
                criterionMap.clear();
                taskMap.putAll((Map<String, TMS>) ois.readObject());
                criterionMap.putAll((Map<String, Criterion>) ois.readObject());
                System.out.println("All lines within file have been read.");
            } catch (FileNotFoundException e) {
                System.out.println("Specified directory is not found or cannot be accessed.");
            } catch (EOFException e) {
                System.out.println("End of file reached.");
            }
        } else {
            System.out.println("Invalid Syntax for Load");
        }
    }
}


