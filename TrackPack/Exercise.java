package TrackPack;

import java.lang.StringBuilder;

public class Exercise {

    private int month;
    private int day;
    private int year;
    private String date;
    private String exercise;
    private double weight;
    private int reps;
    private int sets;
    private double totalLifted;

    public Exercise(String exercise){
        this.exercise = exercise;
    }
    public Exercise(int month, int day, int year, String exercise, double weight, int reps, int sets) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.totalLifted = weight * reps * sets;
    }
    //overloaded constructor
    public Exercise(int month, int day, int year, String exercise, double weight, int reps, int sets, double totalLifted){
        this.month = month;
        this.day = day;
        this.year = year;
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.totalLifted = totalLifted;
    }

    public void setMonth(int newMonth){
        month = newMonth;
    }
    public void setDay(int newDay){
        day = newDay;
    }
    public void setYear(int newYear){
        year = newYear;
    }
    public void setExercise(String newExercise){
        exercise = newExercise;
    }
    public void setWeight(double newWeight){
        weight = newWeight;
        setTotalLifted();
    }
    public void setReps(int newReps){
        reps = newReps;
        setTotalLifted();
    }
    public void setSets(int newSets){
        sets = newSets;
        setTotalLifted();
    }
    private void setTotalLifted(){
        totalLifted = weight * reps * sets;
    }

    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getYear(){
        return year;
    }
    public String getDate() {
        StringBuilder date = new StringBuilder();
        date.append(month);
        date.append("/");
        date.append(day);
        date.append("/");
        date.append(year);
        return date.toString();
    }
    public String getExercise(){
        return exercise;
    }
    public double getWeight(){
        return weight;
    }
    public int getReps(){
        return reps;
    }
    public int getSets(){
        return sets;
    }
    public double getTotalLifted(){
        return totalLifted;
    }
    public int getTimeIdea(){
        return 370*year + 35*month + day;
    }

    @Override
    public String toString(){
        StringBuilder data =  new StringBuilder();
        data.append(month);
        data.append(',');
        data.append(day);
        data.append(',');
        data.append(year);
        data.append(',');
        data.append(exercise);
        data.append(',');
        data.append(weight);
        data.append(',');
        data.append(reps);
        data.append(',');
        data.append(sets);
        data.append(',');
        data.append(totalLifted);
        return data.toString();
    }
}

