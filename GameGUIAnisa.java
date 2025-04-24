
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.io.OutputStream;
import java.io.PrintStream;
//import javafx.scene.control.ProgressBar;
/**
 * This is the GUI for our adventure game adopted from "The Tower"
 *
 * @author Emmie Regan Ange Li Anisa Rodriguez
 * @version Spring 2025 
 */
public class GameGUIAnisa extends Application {
        private TextArea descriptionArea;
        private TextField inputField;
        private Game game;
        private ProgressBar guiHpBar;
        private Label hpValue;
        private ImageView roomImageView;
        
    public void start(Stage primaryStage) {
        game = new Game(this);
        this.game = game;
        //center panel (description) 
        VBox eventDescription = new VBox();
        eventDescription.setPadding(new Insets(10));
        eventDescription.setAlignment(Pos.TOP_CENTER);
        Label descLabel = new Label("Description of Events");
        descLabel.setFont(Font.font(16));
        
        descriptionArea = new TextArea();
        descriptionArea.setPrefSize(200,300);
        descriptionArea.setWrapText(true);
        eventDescription.getChildren().addAll(descLabel, descriptionArea);
        descriptionArea.setText("Welcome to the Tower,treasure seeker. Can you make it to the treasure chest on the top floor?");
        descriptionArea.appendText("You can enter 'go North' to move up a floor and 'go South' to move down a floor.");
        descriptionArea.appendText("To take a treasure, enter 'take' followed by 'name of the item'");
        descriptionArea.appendText("To quit the game, enter 'q'.");
        

        // Top Menu
        Label menuLabel = new Label("Menu");
        menuLabel.setFont(Font.font(20));
        HBox topMenu = new HBox(menuLabel);
        topMenu.setAlignment(Pos.CENTER_LEFT);
        topMenu.setPadding(new Insets(10));
        topMenu.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0;");

        //left panel (game visual) 
        VBox gameVisuals = new VBox(10);
        gameVisuals.setPadding(new Insets(10));
        gameVisuals.setAlignment(Pos.TOP_CENTER);
        Label visualsLabel = new Label("Game Visuals");
        //room images
        Image image0 = new Image("room0.jpg", 200, 350, false, false);
        Image image1 = new Image("room1.jpg", 200, 350, false, false);
        Image image2 = new Image("room2.jpg", 200, 350, false, false);
        Image image3 = new Image("room3.jpg", 200, 350, false, false);

        roomImageView = new ImageView(); 
        roomImageView.setFitWidth(200); 
        roomImageView.setFitHeight(350); 
        roomImageView.setPreserveRatio(true);
        
        
        Label roomLabel = new Label("Room");
        VBox roomVisuals = new VBox(roomLabel, roomImageView);

        VBox visualsBox = new VBox(roomLabel, roomImageView);
        visualsBox.setAlignment(Pos.TOP_CENTER);

        gameVisuals.getChildren().addAll(visualsLabel, visualsBox);
        

        //inventory (right) 
        VBox inventoryBox = new VBox(10);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setAlignment(Pos.TOP_CENTER);
        Label inventoryLabel = new Label("Inventory");
        ListView<String> inventoryList = new ListView<>();
        inventoryList.getItems().addAll("🗡", "🛡");
        inventoryList.setPrefSize(80, 350);
        inventoryBox.getChildren().addAll(inventoryLabel, inventoryList);

        //creates bottom section (input field & health) 
        HBox bottomSection = new HBox(20);
        bottomSection.setPadding(new Insets(10));
        bottomSection.setAlignment(Pos.CENTER);
        
        //player input field 
        VBox inputBox = new VBox(5);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        Label inputLabel = new Label("Player Action Input:");
        TextField inputField = new TextField();
        inputField.setPromptText("Type your action here (e.g., move up, fight)");
        inputField.setPrefWidth(200);
        inputBox.getChildren().addAll(inputLabel, inputField);

        //stats Box-right of input
        VBox statsBox = new VBox(5);
        statsBox.setAlignment(Pos.CENTER_RIGHT);
        Label statsLabel = new Label("Stats");
        Label hpLabel = new Label("HP:");
        guiHpBar = new ProgressBar(1.0);
        guiHpBar.setPrefWidth(150);
        this.hpValue = new Label(game.getPlayer().getHealth() + " / " + game.getPlayer().getMaxHealth());
        statsBox.getChildren().addAll(statsLabel, hpLabel, guiHpBar, hpValue);
        
        bottomSection.getChildren().addAll(inputBox, statsBox);
        
        BorderPane root = new BorderPane();
        root.setTop(topMenu);
        root.setLeft(gameVisuals);
        root.setCenter(eventDescription);
        root.setRight(inventoryBox);
        root.setBottom(bottomSection);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Adventure Game UI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //allows input for user
        inputField.setOnAction(event -> {
            String command = inputField.getText();
            inputField.clear();//deletes the text after inputting
            game.parseCommand(command); // The game's output will now go to the TextArea
        });
        
        System.out.println("You are starting on " + game.getPlayer().getLocation());
        updateRoomVisual(game.getPlayer().getLocation().getId());
    }
    public void appendToDescription(String text) {
        Platform.runLater(() -> descriptionArea.appendText(text));
    }
    public void updateHealthBar(){
        //System.out.println("updateHealthBar() called");
        double currentHealth = game.getPlayer().getHealth();
        double maxHealth = game.getPlayer().getMaxHealth();
        double progress = currentHealth/maxHealth;
        guiHpBar.setProgress(progress);
        hpValue.setText((int)currentHealth + " / " + (int)maxHealth);
    }
    public void updateRoomVisual(int roomId) {
     try {
        String imagePath = "room" + roomId + ".jpg"; //changes string for file name
        Image newImage = new Image(getClass().getResource(imagePath).toURI().toString());
        roomImageView.setImage(newImage); // sets the new room image
    } catch (Exception e) {
        System.out.println("Error loading image for room");
        
    }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

