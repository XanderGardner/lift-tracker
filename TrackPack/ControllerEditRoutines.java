package TrackPack;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEditRoutines implements Initializable {

    @FXML
    MenuBar menuBar;
    @FXML
    ComboBox<String> workoutDrop;
    @FXML
    TableView<Exercise> exerciseView;
    @FXML
    TableColumn<Exercise, String> exerciseColumn;
    @FXML
    TextField exerciseInput;
    @FXML Label saveText;

    RoutineRecord record;
    ArrayList<Routine> temp;
    int editPos;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exercise"));

        record = new RoutineRecord();
        try{
            record.load();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ArrayList<Routine> recArray = record.getRecord();
        for (Routine r : recArray){
            workoutDrop.getItems().add(r.getWorkout());
        }

    }
    public void deleteBClicked(){
        ObservableList<Exercise> all, selected;
        all = exerciseView.getItems();
        selected = exerciseView.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
    }
    public void dropSelected(){

        //take all the exercises from the selected workout and create several exercise objects
        //clear table view then move those exercise objects into the tableview

        temp = record.getRecord();
        editPos = 0;
        while (!(temp.get(editPos).getWorkout()).equals(workoutDrop.getValue())){
            editPos++;
        }
        String[] exc = temp.get(editPos).getExercises();
        exerciseView.getItems().clear();
        if (exc != null && exc.length != 0){
            for (int j = 0; j < exc.length; j++) {
                exerciseView.getItems().add(new Exercise(exc[j]));
            }
        }
    }
    public void addBClicked(){
        if (exerciseInput.getText().isEmpty()){
            saveText.setText("Entry Failed: Add Exercise Name");
        } else {
            exerciseView.getItems().add(new Exercise(exerciseInput.getText()));
            exerciseInput.clear();
        }
    }
    public void saveBClicked(){
        String[] eList = new String[exerciseView.getItems().size()];
        for (int i = 0; i < eList.length; i++){
            eList[i] = exerciseView.getItems().get(i).getExercise();
        }
        temp.get(editPos).setExercises(eList);
        record.setRecord(temp);
        try {
            record.save();
            saveText.setText("Saved at " + StringTime.getClockTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

