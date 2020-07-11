package TrackPack;

import java.lang.StringBuilder;

public class Routine {
    private String workout;
    private String[] exercises;
    private String exercisesView;
    private String exercisesFullView;

    public Routine(String workout){
        this.workout = workout;
    }
    public Routine(String workout, String[] exercises){
        this.workout = workout;
        this.exercises = exercises;
        setExercisesView(exercises);
        setExercisesFullView(exercises);
    }

    public String getWorkout(){
        return workout;
    }
    public String[] getExercises(){
        return exercises;
    }
    public String getExercisesView(){
        return exercisesView;
    }
    public String getExercisesFullView() { return exercisesFullView; };
    public void setWorkout(String workout){
        this.workout = workout;
    }
    public void setExercises(String[] exercises){
        this.exercises = exercises;
        setExercisesView(exercises);
    }
    public void setExercisesView(String[] exercises){
        StringBuilder eView = new StringBuilder();
        if (exercises != null && exercises.length != 0) {
            eView.append(exercises[0]);
            for (int i = 1; i < exercises.length && i < 3; i++) {
                if (i == 2) {
                    eView.append("...");
                    break;
                }
                eView.append(", ");
                eView.append(exercises[i]);
            }
            exercisesView = eView.toString();
        } else {
            exercisesView = "";
        }
    }
    public void setExercisesFullView(String[] exercises){
        StringBuilder eView = new StringBuilder();
        if (exercises != null && exercises.length != 0) {
            eView.append(exercises[0]);
            for (int i = 1; i < exercises.length; i++) {
                eView.append(", ");
                eView.append(exercises[i]);
            }
            exercisesFullView = eView.toString();
        } else {
            exercisesFullView = "";
        }
    }

    @Override
    public String toString(){
        StringBuilder data = new StringBuilder();
        data.append(workout);
        if (exercises != null) {
            for (String exercise : exercises) {
                data.append(",");
                data.append(exercise);
            }
        }
        return data.toString();
    }
}
