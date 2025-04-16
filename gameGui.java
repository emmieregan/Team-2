import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import java.awt.*;

import java.io.IOException;
import java.io.File; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.layout.HBox;
import java.util.ArrayList;

import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Write a description of class gameGui here.
 *
 * @author Emmie Regan
 * @version April 3, 2025
 */
public class gameGui extends Application
{
    // instance variables - replace the example below with your own
    public static void main(String[] args){
        launch();
    }
    @Override
    public void start(Stage stage) {
        Label title = new Label("Topia Kingdom!");
        
        Image image = new Image("zeldaDungeon.jpg", 200, 200, false, false);
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        
        ListView listView = new ListView();
        
        //SplitPane splitPane = new SplitPane();
        HBox header = new HBox(new Label ("This is Topia Kingdom"));
        HBox footer = new HBox(new Label ("Controls"));
        VBox map = new VBox(new Label ("Map"));
        VBox gameScreen = new VBox(new Label ("Game"),iv1);
        VBox inventory = new VBox(new Label ("Inventory"));
        VBox stats = new VBox(new Label ("HP"),footer);
        
        map.setPadding(new Insets (30,30,30,30));
        inventory.setPadding(new Insets (30,30,30,30));
        gameScreen.setAlignment(Pos.CENTER);
        header.setAlignment(Pos.CENTER);
        footer.setAlignment(Pos.CENTER);
        //map.setAlignment(Pos.CENTER);
        stats.setAlignment(Pos.BOTTOM_RIGHT);
        stats.setPadding(new Insets (30,30,30,30));
        footer.setPadding(new Insets(10,10,10,10));
        
        
        //map.setPadding()
        BorderPane root = new BorderPane();
        root.setCenter(gameScreen);
        root.setLeft(map);
        root.setRight(inventory);
        root.setBottom(footer);
        root.setTop(header);
        //root.setRight(stats);
        Scene scene = new Scene(root, 550, 300);
        stage.setScene(scene);
        stage.setTitle("This is Topia Kingdom");
        stage.show();
    }
    
    
}
