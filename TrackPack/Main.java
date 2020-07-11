package TrackPack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent startRoot = FXMLLoader.load(getClass().getResource("addEntry.fxml"));
        Scene startScene = new Scene(startRoot, 600, 400);

        primaryStage.setScene(startScene);
        primaryStage.setTitle("LiftTracker");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
