package storage;

import exceptions.NoFileException;
import gui.theme.Themes;
import module.TaskLists.ShoppingList;
import module.TaskLists.TaskList;
import ui.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private static int counter;
    private static boolean condition = true;

    public static void loadData(List<TaskList> list, Account ac) throws IOException{
        String AccountName = ac.getAccountName();
        String FileName = AccountName + ".txt";
        List<String> lines = Files.readAllLines(Paths.get(FileName));
        if(lines.isEmpty())throw new NoFileException();
        list.set(0,new TaskList(lines.get(0), ac));
        lines.remove(0);
        counter = 0;
        for(String stuff : lines){
            if(stuff.isEmpty()) {
                if(condition) {
                    counter++;
                    condition = false;
                    continue;
                }
                else break;
            }
            if(!stuff.substring(0,1).equals("[")){
                if(stuff.endsWith("@")){
                    list.add( new ShoppingList(stuff.replace(" @",""),ac));
                    condition = true;
                    continue;
                }
                else if(stuff.endsWith("**")){
                    Themes theme =Themes.valueOf(stuff.replace("**",""));
                    ac.changeTheme(theme);
                    continue;
                }
                else{
                    list.add( new TaskList(stuff,ac));
                    condition = true;
                    continue;
                }

            }
            stuff = stuff.replace("[", "");
            stuff = stuff.replace("]", "");
            ArrayList<String> slist = splitOnComma(stuff);
            slist.replaceAll(String::trim);
            if(slist.get(4).equals("Personal")) list.get(counter).addPersonalTask(slist.get(0),slist.get(1),slist.get(2),slist.get(3));
            else if (slist.get(4).equals("URGENT")) list.get(counter).addPersonalTask(slist.get(0),slist.get(1),slist.get(2),slist.get(3));
            else list.get(counter).addTask(slist.get(0),slist.get(1),slist.get(2),slist.get(3));
        }


    }

    public static ArrayList<String> splitOnComma(String line){
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

//    public static void trimList(ArrayList<String> list){
//        for(String s: list){
//            s;
//        }
//    }
}
