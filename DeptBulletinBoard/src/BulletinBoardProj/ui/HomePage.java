package BulletinBoardProj.ui;

import java.util.List;

import BulletinBoardProj.UIController;
import BulletinBoardProj.Databases.Confirmed;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class that hosts and organizes a collection of individual components.
 */
public class HomePage implements BBPage {

    private BorderPane borderPane;
    private NavBar navBar;
    private FilterBar filterBar;
    private EventScroll eventScroll;
    private VBox scrollContent;
    private UIController controller;

	
    final String borderPaneCss =
    		"";
    
    final String scrollContentCss = 
    		"";
    
    final String eventDetailsVBoxCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" + 
    		"-fx-padding: 10;\n";
   
    
	
	public HomePage() {
		setupView();
	}
	
	public Pane getPane() {
		return borderPane;
	}
	
	@Override
	public void setController(UIController controller) {
		this.controller = controller;
	}
	
//    public void showAllEvents(List<Confirmed> eventList) {
//    	scrollContent.getChildren().clear();
//    	for (Confirmed event : eventList) {
//    		showEvent(event);
//    	}
//    }
    

    
    /* Formats event data into VBox */
//    private VBox makeEventVBox(Confirmed event) {
//    	
//    }
    
    private VBox makeEventDetailVBox(Confirmed event) {
    	final VBox eventVBox = new VBox();
    	eventVBox.setUserData(event);
    	eventVBox.setSpacing(20);
    	eventVBox.setMinWidth(400);
    	
    	// Event content
    	Label title = new Label(event.getTitle());
    	Label details = new Label("Details: " + event.getDescription());
    	Label date = new Label("When: " + event.getDate().toString());
    	Label location = new Label("Where: " + event.getLocation());
    	Label department = new Label("Dept: " + event.getDepartment());
    	// Todo: make fee rounded to 2 decimal places.
    	Label fee = new Label("Fee: $" + Double.toString(event.getFee()));
    	
    	// Event style
    	eventVBox.setStyle(eventDetailsVBoxCss); // todo: update style
    	eventVBox.getChildren().addAll(title, details, date, location, department, fee);
//    	eventVBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 6; i++) {
//    		eventVBox.getChildren().get(i).setStyle(normalFont);
    	}
    	return eventVBox;
    }
    
    private void showEventDetails(Confirmed event) {
    	Stage eventDetailWindow = new Stage();
    	VBox eventDetailVBox = makeEventDetailVBox(event);
    	Scene scene = new Scene(eventDetailVBox, 500, 500);
    	eventDetailWindow.setScene(scene);
    	eventDetailWindow.initModality(Modality.APPLICATION_MODAL);
    	eventDetailWindow.show();
    }
	
	private void setupView() {

		/* Setup click listeners for navigation bar */
		
		navBar = new NavBar();
		navBar.getHomeLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// TODO
   	        }
   	    });
		navBar.getLoginLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// TODO
   	        }
   	    });
		navBar.getSignupLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// TODO
   	        }
   	    });
		navBar.getAdminLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// TODO
   	        }
   	    });
		navBar.getCreateEventLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// TODO
   	        }
   	    });
		
    	
        /* Setup click listeners for filter bar */
		
        filterBar = new FilterBar();
        filterBar.getDateLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            controller.onDateFilter();
   	        }
   	    });
        filterBar.getDeptLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            controller.onDeptFilter();
   	        }
   	    });
        filterBar.getFeeLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            controller.onFeeFilter();
   	        }
   	    });
        
        /* SETUP BULLETIN SCROLL */
        
        
        borderPane.setTop(navBar.getPane());
        borderPane.setLeft(filterBar.getPane());
        borderPane.setCenter(eventScroll.getPane());
	}
	
}
