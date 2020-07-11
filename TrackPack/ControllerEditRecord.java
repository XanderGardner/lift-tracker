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

public class ControllerEditRecord implements Initializable {

    @FXML MenuBar menuBar;
    @FXML TableView<Exercise> recordEditView;
    @FXML TableColumn<Exercise, String> dateCER, exerciseCER;
    @FXML TableColumn<Exercise, Double> weightCER, totalLiftedCER;
    @FXML TableColumn<Exercise, Integer> repsCER, setsCER;
    @FXML TextField dateTER, exerciseTER, weightTER, repsTER, setsTER;
    @FXML
    Label saveText;

    Record record;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateCER.setCellValueFactory(new PropertyValueFactory<>("date"));
        exerciseCER.setCellValueFactory(new PropertyValueFactory<>("exercise"));
        weightCER.setCellValueFactory(new PropertyValueFactory<>("weight"));
        repsCER.setCellValueFactory(new PropertyValueFactory<>("reps"));
        setsCER.setCellValueFactory(new PropertyValueFactory<>("sets"));
        totalLiftedCER.setCellValueFactory(new PropertyValueFactory<>("totalLifted"));

        record = new Record();
        try {
            record.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Exercise> recAry = record.getRecord();
        for (Exercise e : recAry) {
            recordEditView.getItems().add(e);
        }
    }

    public void deleteBERClicked(){
        ObservableList<Exercise> all, selected;
        all = recordEditView.getItems();
        selected = recordEditView.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
    }
    public void extractBERClicked(){
        ObservableList<Exercise> all, selected;
        selected = recordEditView.getSelectionModel().getSelectedItems();
        dateTER.setText(selected.get(0).getDate());
        exerciseTER.setText(selected.get(0).getExercise());
        weightTER.setText(Double.toString(selected.get(0).getWeight()));
        repsTER.setText(Integer.toString(selected.get(0).getReps()));
        setsTER.setText(Integer.toString(selected.get(0).getSets()));

        all = recordEditView.getItems();
        selected.forEach(all::remove);
    }
    public void insertBERClicked(){
        if (dateTER.getText().isEmpty() || exerciseTER.getText().isEmpty() || weightTER.getText().isEmpty() || repsTER.getText().isEmpty() || setsTER.getText().isEmpty()){
            saveText.setText("Insert Failed: Complete All Fields");
        } else {
            try {
                String[] dAry = dateTER.getText().split("/");
                int month = Integer.parseInt(dAry[0]);
                int day = Integer.parseInt(dAry[1]);
                int year = Integer.parseInt(dAry[2]);
                double weight = Double.parseDouble(weightTER.getText());
                int reps = Integer.parseInt(repsTER.getText());
                int sets = Integer.parseInt(setsTER.getText());
                Exercise eAdded = new Exercise(month, day, year, exerciseTER.getText(), weight, reps, sets);
                recordEditView.getItems().add(eAdded);
                dateTER.clear();
                exerciseTER.clear();
                weightTER.clear();
                repsTER.clear();
                setsTER.clear();
            } catch (Exception e){
                saveText.setText("Insert Failed: Enter Date as XX/XX/XXXX");
            }
        }
    }
    public void saveRecBERClicked(){
        ArrayList<Exercise> newRec = new ArrayList<>();
        recordEditView.getItems().forEach(exercise -> {
            newRec.add(exercise);
        });
        record.setRecord(newRec);
        try {
            record.save();
            saveText.setText("Saved at " + StringTime.getClockTime() + "!");
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

