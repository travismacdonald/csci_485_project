package BulletinBoardProj.ui;

import BulletinBoardProj.Databases.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EventScrollItem {

    final String itemCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" + 
    		"-fx-padding: 10;\n" +
    		"-fx-background-color: white;\n" +
    		"-fx-cursor: hand;\n";
    
    final String normalFont =
    		"-fx-font: 22 calibri;\n";
    
    final String headingFont =
    		"-fx-font: 34 calibri;\n" + 
    		"-fx-font-weight: bold;\n";
    
    private VBox vBox;
			
	
	public EventScrollItem(Event event) {
		setupView(event);
	}
	
	public VBox getPane() {
		return vBox;
	}
	
	private void setupView(Event event) {
		vBox = new VBox();
    	vBox.setUserData(event);
    	vBox.setSpacing(20);
    	vBox.setMinWidth(400);
    	
    	
    	// Event content
    	final Label title = new Label(event.getTitle());
    	final Label date = new Label("When: " + event.getDate().toString());
    	final Label location = new Label("Where: " + event.getLocation() + ' ' + Integer.toString(event.getRoom()));
    	final Label department = new Label("Dept: " + event.getDepartment());
    	// Todo: make fee rounded to 2 decimal places.
    	final Label fee = new Label("Fee: $" + Double.toString(event.getFee()));
    	
    	// Event style
    	vBox.setStyle(itemCss);
    	vBox.getChildren().addAll(title, date, location, department, fee);
    	vBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 5; i++) {
    		vBox.getChildren().get(i).setStyle(normalFont);
    	}
	}
    
	
}
