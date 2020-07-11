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

public class ControllerEditRoutineNames implements Initializable {

    @FXML MenuBar menuBar;
    @FXML TableView<Routine> routineView;
    @FXML TableColumn<Routine, String> routineNameCERN;
    @FXML TableColumn<Routine, String> exercisesCERN;
    @FXML TextField renameField;
    @FXML TextField renameExercises;
    @FXML TextField addField;
    @FXML Label saveText;

    RoutineRecord record;
    String[] holdExercises;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        routineNameCERN.setCellValueFactory(new PropertyValueFactory<>("workout"));
        exercisesCERN.setCellValueFactory(new PropertyValueFactory<>("exercisesView"));

        record = new RoutineRecord();
        try{
            record.load();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ArrayList<Routine> recAry = record.getRecord();
        for(Routine e : recAry){
            routineView.getItems().add(e);
        }
    }

    public void deleteBClicked(){
        ObservableList<Routine> all, selected;
        all = routineView.getItems();
        selected = routineView.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
    }
    public void extractBClicked(){
        ObservableList<Routine> all, selected;
        selected = routineView.getSelectionModel().getSelectedItems();
        renameField.setText(selected.get(0).getWorkout());
        renameExercises.setText(selected.get(0).getExercisesView());
        holdExercises = selected.get(0).getExercises();

        all = routineView.getItems();
        selected.forEach(all::remove);
    }
    public void renameBClicked(){
        if (renameField.getText().isEmpty()){
            saveText.setText("Rename Failed: Enter a Name");
        } else {
            Routine rAdded;
            if (holdExercises == null){
                rAdded = new Routine(renameField.getText());
            } else {
                rAdded = new Routine(renameField.getText(), holdExercises);
            }
            routineView.getItems().add(rAdded);
            renameField.clear();
            renameExercises.clear();
        }
    }
    public void addButtonClicked(){
        if (addField.getText().isEmpty()){
            saveText.setText("Entry Failed: Enter a Name");
        } else {
            routineView.getItems().add(new Routine(addField.getText()));
            addField.clear();
        }
    }
    public void saveButtonClicked(){
        ArrayList<Routine> newRec = new ArrayList<>();
        routineView.getItems().forEach(routine -> {
            newRec.add(routine);
        });
        record.setRecord(newRec);
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

