package TrackPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGraphTimeline implements Initializable {
    @FXML
    MenuBar menuBar;
    @FXML LineChart<Number, Number> timeline;

    Record record;
    ArrayList<Exercise> tempRec;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        record = new Record();
        try {
            record.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tempRec = record.getRecord();

        timeline.getYAxis().setLabel("Total Lifted");
        timeline.getXAxis().setLabel(tempRec.get(0).getDate() + " to " + tempRec.get(tempRec.size()-1).getDate());

        XYChart.Series series = new XYChart.Series();
        series.setName("Lift Record");

        String prevDate = tempRec.get(0).getDate();
        double dTotal = 0;
        int pos = 0;
        for (int i = 1; i < tempRec.size(); i++){
            if (prevDate.equals(tempRec.get(i).getDate())){
                dTotal += tempRec.get(i-1).getTotalLifted();
            } else {
                series.getData().add(new XYChart.Data(pos, dTotal+tempRec.get(i-1).getTotalLifted()));
                dTotal = 0;
                pos++;
            }
            prevDate = tempRec.get(i).getDate();
        }
        series.getData().add(new XYChart.Data(pos, dTotal+tempRec.get(tempRec.size()-1).getTotalLifted()));
        timeline.getData().add(series);
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
