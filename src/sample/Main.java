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

import java.sql.*;

public class Main extends Application {

    public static int height = 1000;
    public static int width = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED); //  remove windows decoration

        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(login, this.height, this.width);
        scene.getStylesheets().add(getClass().getResource("../res/main.css").toExternalForm());   //  linking stylesheet
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

    public static void main(String[] args) throws SQLException {
        Connection conn = databaseConnection.connect();

        //checking if table exists
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null,null,"students",null);

        //  if students table doesn't exist, create table
        if(!tables.next()){
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE students (rollnumber INTEGER PRIMARY KEY AUTOINCREMENT, password VARCHAR(25) NOT NULL , studentName TEXT NOT NULL, fName TEXT NOT NULL, mName TEXT NOT NULL, sex TEXT NOT NULL, category TEXT NOT NULL, dobD INTEGER NOT NULL, dobM TEXT NOT NULL, dobY INTEGER NOT NULL);";
            stmt.execute(sql);
        }

        tables = dbm.getTables(null,null,"marks",null);

        //  if students table doesn't exist, create table
        if(!tables.next()){
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE marks (rollnumber INTEGER NOT NULL, maths INTEGER DEFAULT 0 CHECK ( maths<=360 ), physics INTEGER DEFAULT 0 CHECK ( physics<=360 ), chemistry INTEGER DEFAULT 0 CHECK ( chemistry<=360 ),FOREIGN KEY (rollnumber) REFERENCES students(rollnumber));";
            stmt.execute(sql);
        }

        launch(args);
    }
}
