package TrackPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGraphRoutine implements Initializable {
    @FXML
    MenuBar menuBar;
    @FXML
    LineChart<Number, Number> timeline;
    @FXML
    ChoiceBox<String> routineChoice;

    Record record;
    RoutineRecord routineRecord;
    ArrayList<Exercise> tempRec;
    ArrayList<Routine> tempRoutineRecord;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        record = new Record();
        routineRecord = new RoutineRecord();
        try {
            record.load();
            routineRecord.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tempRec = record.getRecord();
        tempRoutineRecord = routineRecord.getRecord();

        for (int i = 0; i < tempRoutineRecord.size(); i++){
            routineChoice.getItems().add(tempRoutineRecord.get(i).getWorkout());
        }

        timeline.getYAxis().setLabel("Total Lifted");
        timeline.getXAxis().setLabel("Day");
    }

    //events
    public void addBClicked(){
        String routine = routineChoice.getValue();
        int a = 0;
        while(!(routine.equals(tempRoutineRecord.get(a).getWorkout()))){
            a++;
        }
        String[] e = tempRoutineRecord.get(a).getExercises();
        List exercises = Arrays.asList(e);

        XYChart.Series series = new XYChart.Series();
        series.setName(routine);

        String prevDate = tempRec.get(0).getDate();
        double dTotal = 0;
        int pos = 0;
        int prevI = 0;
        while(!(exercises.contains(tempRec.get(prevI).getExercise()))){
            prevI++;
        }
        for (int i = prevI+1; i < tempRec.size(); i++){
            if (exercises.contains(tempRec.get(i).getExercise())) {
                if (prevDate.equals(tempRec.get(i).getDate())) {
                    dTotal += tempRec.get(prevI).getTotalLifted();
                } else {
                    series.getData().add(new XYChart.Data(pos, dTotal + tempRec.get(prevI).getTotalLifted()));
                    dTotal = 0;
                    pos++;
                }
                prevDate = tempRec.get(i).getDate();
                prevI = i;
            }
        }
        if (prevI != 0){
            series.getData().add(new XYChart.Data(pos,dTotal + tempRec.get(prevI).getTotalLifted()));
            timeline.getData().add(series);
        }
    }
    public void clearBClicked(){
        timeline.getData().clear();
    }

    //change scenes with menu
    public void setSceneViewRecord() throws IOException {
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: View Record");
        Parent viewRecordRoot = FXMLLoader.load(getClass().getResource("viewRecord.fxml"));
        Scene viewRecord = new Scene(viewRecordRoot, 600, 400);
        mainStage.setScene(viewRecord);
    }
    public void setSceneViewRoutines() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: View Routines");
        Parent viewWorkoutsRoot = FXMLLoader.load(getClass().getResource("viewRoutines.fxml"));
        Scene viewWorkouts = new Scene(viewWorkoutsRoot, 600, 400);
        mainStage.setScene(viewWorkouts);
    }
    public void setSceneEditAddEntry() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Add Entry");
        Parent addEntryRoot = FXMLLoader.load(getClass().getResource("addEntry.fxml"));
        Scene addEntry = new Scene(addEntryRoot, 600, 400);
        mainStage.setScene(addEntry);
    }
    public void setSceneEditRoutineNames() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Edit Routine Names");
        Parent editWorkoutsRoot = FXMLLoader.load(getClass().getResource("editRoutineNames.fxml"));
        Scene editWorkouts = new Scene(editWorkoutsRoot, 600, 400);
        mainStage.setScene(editWorkouts);
    }
    public void setSceneEditRoutines() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Edit Routines");
        Parent editExercisesRoot = FXMLLoader.load(getClass().getResource("editRoutines.fxml"));
        Scene editExercises = new Scene(editExercisesRoot, 600, 400);
        mainStage.setScene(editExercises);
    }
    public void setSceneEditRecord() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Edit Record");
        Parent editRecordRoot = FXMLLoader.load(getClass().getResource("editRecord.fxml"));
        Scene addEntry = new Scene(editRecordRoot, 600, 400);
        mainStage.setScene(addEntry);
    }
    public void setSceneGraphTimeline() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Graph Total Timeline");
        Parent graphTimelineRoot = FXMLLoader.load(getClass().getResource("graphTimeline.fxml"));
        Scene graphTimeline = new Scene(graphTimelineRoot, 600, 400);
        mainStage.setScene(graphTimeline);
    }
    public void setSceneGraphRoutine() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Graph Routine vs Time");
        Parent graphRoutineRoot = FXMLLoader.load(getClass().getResource("graphRoutine.fxml"));
        Scene graphRoutine = new Scene(graphRoutineRoot, 600, 400);
        mainStage.setScene(graphRoutine);
    }
    public void setSceneGraphExercise() throws IOException{
        Stage mainStage = (Stage) menuBar.getScene().getWindow();
        mainStage.setTitle("LiftTracker: Graph Exercise vs Time");
        Parent graphExerciseRoot = FXMLLoader.load(getClass().getResource("graphExercise.fxml"));
        Scene graphRoutine = new Scene(graphExerciseRoot, 600, 400);
        mainStage.setScene(graphRoutine);
    }
}
