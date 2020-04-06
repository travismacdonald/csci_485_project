package BulletinBoardProj.ui;

import BulletinBoardProj.Databases.Confirmed;
import BulletinBoardProj.Databases.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class EventScrollItem {

    final String itemCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" + 
    		"-fx-padding: 10;\n" +
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
		final VBox eventVBox = new VBox();
    	eventVBox.setUserData(event);
    	eventVBox.setSpacing(20);
    	eventVBox.setMinWidth(400);
    	
    	// click listener moved to controller class
//    	eventVBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//    	     @Override
//    	     public void handle(MouseEvent mouseEvent) {
//    	         System.out.println("Event " + event.getTitle() + " pressed");
//    	         showEventDetails(event);
//    	     }
//    	});
    	
    	
    	// Event content
    	Label title = new Label(event.getTitle());
    	Label date = new Label("When: " + event.getDate().toString());
    	Label location = new Label("Where: " + event.getLocation());
    	Label department = new Label("Dept: " + event.getDepartment());
    	// Todo: make fee rounded to 2 decimal places.
    	Label fee = new Label("Fee: $" + Double.toString(event.getFee()));
    	
    	// Event style
    	eventVBox.setStyle(itemCss);
    	eventVBox.getChildren().addAll(title, date, location, department, fee);
    	eventVBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 5; i++) {
    		eventVBox.getChildren().get(i).setStyle(normalFont);
    	}
	}
    
	
}
