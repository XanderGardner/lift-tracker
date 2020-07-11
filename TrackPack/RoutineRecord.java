package TrackPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RoutineRecord {
    private File record;
    private ArrayList<Routine> tempRecord;

    public RoutineRecord(){
        record = new File("routine.txt");
        tempRecord = new ArrayList<Routine>();
    }

    public void load() throws FileNotFoundException {
        Scanner readFile = new Scanner(record);
        String line;
        String[] lineArray;
        String workout;
        String[] exercises;
        while (readFile.hasNextLine()){
            line = readFile.nextLine();
            lineArray = line.split(",");
            exercises = new String[lineArray.length-1];
            for (int i = 1; i < lineArray.length; i ++){
                exercises[i-1] = lineArray[i];
            }
            workout = lineArray[0];
            tempRecord.add(new Routine(workout, exercises));
        }
        readFile.close();
    }
    public void save() throws FileNotFoundException{
        //moves ArrayList tempRecord to File record
        PrintWriter outFile = new PrintWriter(record);
        for(int i = 0; i < tempRecord.size(); i++){
            outFile.println((tempRecord.get(i)).toString());
        }
        outFile.close();
    }
    public ArrayList<Routine> getRecord(){
        return tempRecord;
    }
    public void setRecord(ArrayList<Routine> newRecord){
        tempRecord = newRecord;
    }
    public void add(Routine entry){
        tempRecord.add(entry);
    }
}
