package TrackPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Record {
    private File record;
    private ArrayList<Exercise> tempRecord;

    public Record(){
        record = new File("record.txt");
        tempRecord = new ArrayList<Exercise>();
    }
    public void load() throws FileNotFoundException {
        //moves File record to ArrayList tempRecord
        Scanner readFile = new Scanner(record);
        String line;
        String[] lineArray;
        int month, day, year;
        String exercise;
        double weight;
        int reps, sets;
        double totalLifted;
        while (readFile.hasNextLine()){
            line = readFile.nextLine();
            lineArray = line.split(",");
            month = Integer.parseInt(lineArray[0]);
            day = Integer.parseInt(lineArray[1]);
            year = Integer.parseInt(lineArray[2]);
            exercise = lineArray[3];
            weight = Double.parseDouble(lineArray[4]);
            reps = Integer.parseInt(lineArray[5]);
            sets = Integer.parseInt(lineArray[6]);
            totalLifted = Double.parseDouble(lineArray[7]);
            tempRecord.add(new Exercise(month, day, year, exercise, weight, reps, sets, totalLifted));
        }
        readFile.close();
    }
    public void save() throws FileNotFoundException{
        //moves ArrayList tempRecord to File record
        sortTempRecord();
        PrintWriter outFile = new PrintWriter(record);
        for(int i = 0; i < tempRecord.size(); i++){
            outFile.println((tempRecord.get(i)).toString());
        }
        outFile.close();
    }
    public ArrayList<Exercise> getRecord(){
        return tempRecord;
    }
    public void setRecord(ArrayList<Exercise> newRecord){
        tempRecord = newRecord;
    }
    public void add(Exercise entry){
        tempRecord.add(entry);
        sortTempRecord();
    }
    private void sortTempRecord() {
        ArrayList<Exercise> sorted = new ArrayList<>();
        if (tempRecord.size() != 0) {
            sorted.add(0, tempRecord.get(0));
        }
        int check;
        for (int sortPoint = 1; sortPoint < tempRecord.size(); sortPoint++){
            check = sortPoint-1;
            while(check >= 0 && tempRecord.get(sortPoint).getTimeIdea() < sorted.get(check).getTimeIdea()){
                check--;
            }
            sorted.add(check+1, tempRecord.get(sortPoint));
        }
        tempRecord = sorted;
    }
}
