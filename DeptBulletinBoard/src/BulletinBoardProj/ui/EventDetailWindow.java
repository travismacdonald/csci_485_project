package BulletinBoardProj.ui;

import BulletinBoardProj.Databases.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventDetailWindow {

    private final String vBoxCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-spacing: 20;\n" +
    		"-fx-padding: 10;\n";
    
    final String normalFont =
    		"-fx-font: 22 calibri;\n";
    
    final String headingFont =
    		"-fx-font: 34 calibri;\n" + 
    		"-fx-font-weight: bold;\n";
    
    private Stage stage;
    private VBox vBox;
    private Button acceptButton;
    private Button rejectButton;
    private Button notifyButton;
    
    
    public EventDetailWindow(Event event, boolean isRequestedEvent) {
    	setupView(event);
    	if (isRequestedEvent) {
    		setupAcceptRejectButtons();
    	}
    	else {
    		setupNotifyButton();
    	}
    	setupStage();
    }
    
    public Stage getStage() {
    	return stage;
    }
    
    public Button getNotifyButton() {
    	return notifyButton;
    }
    
    public Button getAcceptButton() {
    	return acceptButton;
    }
    
    public Button getRejectButton() {
    	return rejectButton;
    }
	
    private void setupView(Event event) {
    	vBox = new VBox();
    	vBox.setUserData(event);
    	vBox.setMinWidth(400);  // TODO: check if this can go in CSS string
    	
    	// Event content
    	final Label title = new Label(event.getTitle());
    	final Label details = new Label("Details: " + event.getDescription());
    	final Label date = new Label("When: " + event.getDate().toString());
    	final Label location = new Label("Where: " + event.getLocation() + ' ' + Integer.toString(event.getRoom()));
    	final Label department = new Label("Dept: " + event.getDepartment());
    	// Todo: make fee rounded to 2 decimal places.
    	final Label fee = new Label("Fee: $" + Double.toString(event.getFee()));
    	
    	// Event style
    	vBox.setStyle(vBoxCss);
    	vBox.getChildren().addAll(title, details, date, location, department, fee);
      	vBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 6; i++) {
    		vBox.getChildren().get(i).setStyle(normalFont);
    	}
    	
    }
    
    private void setupStage() {
    	stage = new Stage();
    	Scene scene = new Scene(vBox, 500, 500);
    	stage.setScene(scene);
    	stage.initModality(Modality.APPLICATION_MODAL);
    }
    
    private void setupNotifyButton() {
    	// TODO:
    }
    
    private void setupAcceptRejectButtons() {
    	GridPane buttonPane = new GridPane();
    	acceptButton = new Button("Accept");
    	rejectButton = new Button("Reject");
    	buttonPane.add(acceptButton, 0, 0);
    	buttonPane.add(rejectButton, 1, 0);
    	buttonPane.setPadding(new Insets(5));
    	vBox.getChildren().add(buttonPane);
    }
    
}
