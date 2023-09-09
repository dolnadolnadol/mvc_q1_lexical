package mvc;

//q1
//64050060

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private ArrayList<String> line;
    private String[] lexeme;
    private Model storage;
    private List<String> str;

    public Controller(String txt,int selected) {
        line = new ArrayList<String>();
        storage = new Model();
        splitText(txt);
        if(selected == 1){
            lexical();
        }else{
            lexical2();
        }
        
    }

    //split line
    private void splitText(String txt){
        String[] textsplit = txt.split("\n");
        line.addAll(Arrays.asList(textsplit));
    }

    //split lexeme model 1
    private void lexical(){
        for(String i : line){
            // ignore comment
            if(i.matches("^\\/\\/.*")){
                continue;
            }
            lexeme = i.split(" ");
            for(String j : lexeme){
                if(j.equals("declare")){
                    storage.saveTodosToCSV("Keyword",j);
                }
                else if(j.equals("=")||j.equals("+")){
                    storage.saveTodosToCSV("Symbol",j);
                }
                else if(isNumeric(j)){
                    storage.saveTodosToCSV("Literal",j);
                }
                else if(j.matches("[A-Za-z]+([A-Za-z0-9])?")) {
                    storage.saveTodosToCSV("Variable",j);
                }else{
                    // else is error
                    storage.saveTodosToCSV("Type error", j);
                }
            }
        }
    }
    
    //split lexeme model 2
    private void lexical2(){
        for(String i : line){
            // ignore comment
            if(i.matches("^\\/\\/.*")){
                continue;
            }
            lexeme = i.split(" ");
            for(String j : lexeme){
                if(j.equals("declare")||j.equals("+")){
                    storage.saveTodosToCSV("Keyword and Sign",j);
                }
                else if(j.equals("=")){
                    storage.saveTodosToCSV("Assignment",j);
                }
                else if(isNumeric(j)){
                    storage.saveTodosToCSV("Integer",j);
                }
                else if(j.matches("[A-Za-z]+([A-Za-z0-9])?")) {
                    storage.saveTodosToCSV("Variable",j);
                }else{
                    // else is error
                    storage.saveTodosToCSV("Type error", j);
                }
            }
        }
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); 
    }
    public String show(){
        //make text and return string to display
        String text = "";
        str = storage.loadTodosFromCSV();
        for(String line : str){
            //connect string
            text = text + line +" \n";
        }
        
        return text;
    }
    
}
