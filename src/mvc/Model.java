package mvc;

//q1
//64050060

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> todos;
    private File csvFile;

    public Model() {
        todos = new ArrayList<>();
        csvFile = new File("todos.csv");
        //clear old data
        csvFile.delete();
        csvFile = new File("todos.csv");
    }
    
    // read csv
    public List<String> loadTodosFromCSV() {      
        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                todos.add(record.get(0)+" is "+record.get(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todos;
    }

    // save or append data to csv file
    public void saveTodosToCSV(String type, String input) {
        // check if csv file already created.
        if (!csvFile.exists()) {
             try {
                 if (csvFile.createNewFile()) {
                     System.out.println("File created: " + csvFile.getAbsolutePath());
                 } else {
                     System.out.println("File already exists.");
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
        // append data to csv file
        try (Writer writer = new FileWriter(csvFile, true);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
             csvPrinter.printRecord(input, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
