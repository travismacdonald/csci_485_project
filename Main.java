package BulletinBoardProj;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    final Group group = new Group();
    final ScrollPane scrollPane = new ScrollPane();

    final String topBorderCss =
            "-fx-border-color: blue;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: dashed;\n";

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BBModel bbModel = new BBModel(); // here is the model

        setUpView(primaryStage);

//        BorderPane borderPane = new BorderPane();
//        HBox topBar = new HBox();
//        topBar.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
//        topBar.setMinHeight(100);
//        borderPane.setLeft();

//        borderPane.setTop(topBar);

/*
        VBox vBox = new VBox();
        Label tl = new Label("Filters/Sortings go here.");
        vBox.getChildren().add(tl);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        vBox.prefHeightProperty().bind(primaryStage.heightProperty().multiply(1));

        HBox hBox = new HBox();
        Label hBoxLabel = new Label("Other features go here");
        hBox.getChildren().add(hBoxLabel);
        hBox.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
//        hBox.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.9));
        hBox.setMinWidth(500);
        hBox.setLayoutX(140);
        hBox.setMinHeight(80);

        group.getChildren().add(vBox);
        group.getChildren().add(hBox);
        Scene scene = new Scene(group, 500, 500);
*/
//        Scene scene = new Scene(borderPane, 500, 500);
//        // Setup stage
//        primaryStage.setMinHeight(400);
//        primaryStage.setMinWidth(500);
//        primaryStage.initStyle(StageStyle.DECORATED);
//        primaryStage.setTitle("CSCI 485");
//        primaryStage.setScene(scene);
//        primaryStage.show();


    }

    private void setUpView(Stage primaryStage) {
        // Setup Border Pane
        BorderPane borderPane = new BorderPane();
        HBox topBar = new HBox();
        topBar.setStyle(topBorderCss);
//        topBar.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
        topBar.setMinHeight(100);


        borderPane.setTop(topBar);

        Scene scene = new Scene(borderPane, 500, 500);
        // Setup stage
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("CSCI 485");
        primaryStage.setScene(scene);

        primaryStage.setMaximized(true);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
