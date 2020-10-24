package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static int height = 1000;
    public static int width = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED); //  remove windows decoration

        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(login, this.height, this.width);
        scene.getStylesheets().add(getClass().getResource("../res/styles.css").toExternalForm());   //  linking stylesheet
        primaryStage.setTitle("JEE Management System");

        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(scene);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds(); //  getting displayInfo

        //  setting position of window at center
        primaryStage.setX((screenBounds.getMaxX()-this.height)/2);
        primaryStage.setY((screenBounds.getMaxY()-this.width)/2);

        primaryStage.setResizable(false);   //  setting resizable to false
        primaryStage.show();
    }

    public static void main(String[] args) {
        databaseConnection.connect();
        launch(args);
    }
}
