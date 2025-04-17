
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameGUIAnisa extends Application {
    public void start(Stage primaryStage) {
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

        Rectangle room = new Rectangle(200, 350);
        room.setFill(Color.LIGHTGRAY);
        room.setStroke(Color.BLACK);
        Label roomLabel = new Label("Room");

        VBox visualsBox = new VBox(roomLabel, room);
        visualsBox.setAlignment(Pos.TOP_CENTER);

        gameVisuals.getChildren().addAll(visualsLabel, visualsBox);

        //center panel (description) 
        VBox eventDescription = new VBox();
        eventDescription.setPadding(new Insets(10));
        eventDescription.setAlignment(Pos.TOP_CENTER);
        Label descLabel = new Label("Description of Events");
        descLabel.setFont(Font.font(16));
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefSize(200,300);
        descriptionArea.setWrapText(true);
        eventDescription.getChildren().addAll(descLabel, descriptionArea);

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
        ProgressBar hpBar = new ProgressBar(0.95);
        hpBar.setPrefWidth(150);
        Label hpValue = new Label("285 / 300");
        statsBox.getChildren().addAll(statsLabel, hpLabel, hpBar, hpValue);

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}

