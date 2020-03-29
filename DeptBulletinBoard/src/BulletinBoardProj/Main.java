package BulletinBoardProj;


import java.util.List;

import BulletinBoardProj.Databases.Confirmed;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private BorderPane borderPane;
    private HBox topBar;
    private VBox leftBar;
    private ScrollPane scrollPane;
    private VBox scrollContent;
    private List<VBox> eventVBoxList;

    final String topBarCss =
            "-fx-border-color: blue;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: dashed;\n";
    
    final String leftBarCss =
            "-fx-border-color: green;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: dashed;\n";
    
    final String scrollPaneCss =
    		"-fx-border-color: red;\n" +
    	    "-fx-border-insets: 5;\n" +
    	    "-fx-border-width: 3;\n" +
    	    "-fx-border-style: dashed;\n" +
    	    "-fx-background-color: transparent";
    
    final String eventCss = "";
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
    	DBModel dbModel = new DBModel();
        setUpView(primaryStage);
        showAllEvents(dbModel.getEventsByDate());
    }

    private void setUpView(Stage primaryStage) {
    	topBar = new HBox();
        topBar.setStyle(topBarCss);
        topBar.setMinHeight(100);
    	
    	leftBar = new VBox();
        leftBar.setStyle(leftBarCss);
        leftBar.setMinWidth(100);
        
        // Bulletin Scroll
        scrollContent = new VBox();
        for (int i = 0; i < 20; i++) {
            Label label = new Label("bitch");
            scrollContent.getChildren().add(label);
        }
        scrollContent.setSpacing(10);
        
        scrollPane = new ScrollPane();
        scrollPane.setContent(scrollContent);
        scrollPane.setStyle(scrollPaneCss);
        scrollPane.setMinSize(500, 500);
        

        borderPane = new BorderPane();
        borderPane.setTop(topBar);
        borderPane.setLeft(leftBar);
        borderPane.setCenter(scrollPane);

        Scene scene = new Scene(borderPane, 500, 500);
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
    
    private void showAllEvents(List<Confirmed> eventList) {
    	for (Confirmed event : eventList) {
//    		scrollContent.getChildren().add(new Label(event.getTitle()));
    		showEvent(event);
    	}
    }
    
    private void showEvent(Confirmed event) {
    	final VBox eventVBox = new VBox();
    	Label title = new Label(event.getTitle());
    	Label date = new Label(event.getDate().toString());
    	Label description = new Label(event.getDescription());
    	Label location = new Label(event.getLocation());
    	Label department = new Label(event.getDepartment());
    	Label fee = new Label(Double.toString(event.getFee()));
    	
    	eventVBox.getChildren().addAll(title, date, description, location, department, fee);
    	scrollContent.getChildren().add(eventVBox);
    }
}
