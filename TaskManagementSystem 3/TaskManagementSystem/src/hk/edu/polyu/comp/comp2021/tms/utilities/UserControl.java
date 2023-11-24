package hk.edu.polyu.comp.comp2021.tms.utilities;

import hk.edu.polyu.comp.comp2021.tms.Application;
import hk.edu.polyu.comp.comp2021.tms.model.TMS;

import java.util.Map;
import java.io.*;
import java.util.*;

// import static hk.edu.polyu.comp.comp2021.tms.Application.taskMap;

public class UserControl {
    // this is for saving, loading, undo and redo.
    //static Map<String, TMS> taskMap = Application.taskMap;
    //static Map<String, Criterion> criterionMap = Application.criterionMap;
    // Java program to write HashMap to a file


//    final static String outputFilePath
//            = "F:/Serialisation/write.txt";

    public static void saveTask(String instruction, HashMap <String, TMS>taskMap, HashMap<String, Criterion> criterionMap) {
        String[] tokens = instruction.split(" ");
        if (tokens.length == 2) {
            String filePath = tokens[1];
            // new file object
            File file = new File(filePath);
            BufferedWriter bf = null;
            try {
                // create new BufferedWriter for the output file
                bf = new BufferedWriter(new FileWriter(file));
                // iterate map entries
                for (Map.Entry<String, TMS> entry :
                        taskMap.entrySet()) {
                    // put key and value separated by a colon
                    bf.write(entry.getKey() + ":"
                            + entry.getValue());
                    // new line
                    bf.newLine();
                }
                for (Map.Entry<String, Criterion> entry :
                        criterionMap.entrySet()) {
                    // put key and value separated by a colon
                    bf.write(entry.getKey() + ":"
                            + entry.getValue());
                    // new line
                    bf.newLine();
                }
                bf.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // always close the writer
                    bf.close();
                } catch (Exception e) {
                }
            }
        }
        else {System.out.println ("File Path declaration incorrect. Please do not add space characters in the path.");}
    }
    public static void saveMap (String instruction, Map<String, TMS> taskMap, Map <String, Criterion> criterionMap) throws IOException {
        String[] inputArray = instruction.split(" ");
        if (inputArray.length == 2) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(inputArray[1]))) {
                // writing the TMS objects
                for (TMS task : taskMap.values()){
                    oos.writeObject (task);
                }
                //oos.writeObject(taskMap);
                // writing the Criterion objects
                for (Criterion criteria : criterionMap.values()){
                    oos.writeObject (criteria);
                }
                //oos.writeObject(criterionMap);
                System.out.println ("Files were updated successfully");
            }
            catch (FileNotFoundException e) {System.out.println ("The file path was not found. Try again");}
            catch (NotSerializableException e) {System.out.println ("There is an implementation error.");}
        }
        else {System.out.println ("Invalid format for Store function");}
    }

    public static void loadMap (String instruction, Map<String, TMS> taskMap, Map<String, Criterion> criterionMap) throws IOException, ClassNotFoundException {
        String[] inputArray = instruction.split (" ");
        Map<String, Object> rawMap = new HashMap<>();
        if (inputArray.length ==2){
            try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream(inputArray[1]))) {
                while (true) {
                    Object objectRead = ois.readObject();
                    rawMap.put(objectRead.toString(), objectRead);
                }
            }
            catch (FileNotFoundException e) {System.out.println ("Specified directory is not found or cannot be accessed.");}
            catch (EOFException e) {
                System.out.println ("All lines within file have been read.");
                for (Object object : rawMap.values()){
                    if (object instanceof TMS) {
                        TMS task = (TMS) object;
                        taskMap.put (task.getName(), task);
                    }
                    else if (object instanceof Criterion){
                        Criterion crt = (Criterion) object;
                        criterionMap.put (crt.getName(), crt);
                    }
                }
            }
        }
        else {System.out.println ("Invalid Syntax for Load");}
    }
}


