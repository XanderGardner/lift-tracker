package TrackPack;

import javafx.collections.ObservableList;
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

public class ControllerAddEntry implements Initializable {


    @FXML MenuBar menuBar;
    @FXML TextField dateE, weightE, repsE, setsE;
    @FXML ComboBox<String> workoutDrop, exerciseDrop;
    @FXML TableView<Exercise> entryTable;
    @FXML TableColumn<Exercise, String> exerciseColumn;
    @FXML TableColumn<Exercise, Double> weightColumn;
    @FXML TableColumn<Exercise, Integer> repsColumn, setsColumn;
    @FXML Label saveData;
    Record record;
    RoutineRecord routineRecord;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        exerciseColumn.setCellValueFactory(new PropertyValueFactory<>("exercise"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        repsColumn.setCellValueFactory(new PropertyValueFactory<>("reps"));
        setsColumn.setCellValueFactory(new PropertyValueFactory<>("sets"));
        dateE.setText(StringTime.getDate());

        record = new Record();
        try {
            record.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        routineRecord = new RoutineRecord();
        try{
            routineRecord.load();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ArrayList<Routine> recArray = routineRecord.getRecord();
        for (Routine r : recArray){
            workoutDrop.getItems().add(r.getWorkout());
        }
    }

    //events
    public void workoutSelected(){
        exerciseDrop.getItems().clear();

        ArrayList<Routine> temp = routineRecord.getRecord();
        int editPos = 0;
        while (!(temp.get(editPos).getWorkout()).equals(workoutDrop.getValue())){
            editPos++;
        }

        //load the exercise drop
        String[] ex = temp.get(editPos).getExercises();
        for (int i = 0; i < ex.length; i++){
            exerciseDrop.getItems().add(ex[i]);
        }
    }
    public void createBClicked() {
        entryTable.getItems().forEach(exercise -> {
            record.add(exercise);
        });
        try {
            record.save();
            saveData.setText("Created at " + StringTime.getClockTime() + "!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entryTable.getItems().clear();
    }
    public void addBClicked(){
        if (dateE.getText().isEmpty() || exerciseDrop.getValue() == null || weightE.getText().isEmpty() || repsE.getText().isEmpty() || setsE.getText().isEmpty()){
            saveData.setText("Entry Failed: Complete All Fields");
        } else {
            String[] dAry = dateE.getText().split("/");
            int month;
            int day;
            int year;
            String exercise = exerciseDrop.getValue();
            double weight = Double.parseDouble(weightE.getText());
            int reps = Integer.parseInt(repsE.getText());
            int sets = Integer.parseInt(setsE.getText());
            try {
                month = Integer.parseInt(dAry[0]);
                day = Integer.parseInt(dAry[1]);
                year = Integer.parseInt(dAry[2]);
                Exercise eAdded = new Exercise(month, day, year, exercise, weight, reps, sets);
                entryTable.getItems().add(eAdded);
                exerciseDrop.getSelectionModel().clearSelection();
                weightE.clear();
                repsE.clear();
                setsE.clear();
            } catch (Exception e){
                saveData.setText("Entry Failed: Enter Date as XX/XX/XXXX");
            }
        }
    }
    public void deleteBClicked(){
        ObservableList<Exercise> all, selected;
        all = entryTable.getItems();
        selected = entryTable.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
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

