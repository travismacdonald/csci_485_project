package BulletinBoardProj.ui;

import java.util.List;

import BulletinBoardProj.UIController;
import BulletinBoardProj.Databases.Confirmed;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomePage implements BBPage {

    private BorderPane borderPane;
    private GridPane topBar;
//    private VBox filterBar;
    private FilterBar filterBar;
    private ScrollPane scrollPane;
    private VBox scrollContent;
    private List<VBox> eventVBoxList;
    
    private UIController controller;

	
    final String borderPaneCss =
    		"";
    
    final String topBarCss =
    		"-fx-padding: 30;\n" +
    		"-fx-spacing: 20;\n" +
    		"-fx-background-color: grey;\n";
    
    final String scrollPaneCss =
    	    "-fx-padding: 20;\n" +
    	    "-fx-background-color: transparent;\n";
    
    final String scrollContentCss = 
    		"";
    
    final String eventVBoxCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" + 
    		"-fx-padding: 10;\n" +
    		"-fx-cursor: hand;\n";
    
    final String eventDetailsVBoxCss = 
    		"-fx-border-color: black;\n" +
            "-fx-border-width: 3;\n" + 
    		"-fx-padding: 10;\n";
    
    final String headingFont =
    		"-fx-font: 34 calibri;\n" + 
    		"-fx-font-weight: bold;\n";
    
    final String normalFont =
    		"-fx-font: 22 calibri;\n";
    
    final String filterHeadingFont =
    		"-fx-font: 34 calibri;\n" +
            "-fx-text-fill: white;\n" +
    	    "-fx-font-weight: bold;\n";
    
    final String filterFontNormal =
    		"-fx-font: 28 calibri;\n" +
    		"-fx-text-fill: white;\n" +
    		"-fx-cursor: hand;\n";
	
	public HomePage() {
		setupPane();
	}
	
	public Pane getPane() {
		return borderPane;
	}
	
	@Override
	public void setController(UIController controller) {
		this.controller = controller;
	}
	
    public void showAllEvents(List<Confirmed> eventList) {
    	scrollContent.getChildren().clear();
    	for (Confirmed event : eventList) {
    		showEvent(event);
    	}
    }
    
    private void showEvent(Confirmed event) {
    	final VBox eventVBox = makeEventVBox(event);
    	scrollContent.getChildren().add(eventVBox);
    }
    
    /* Formats event data into VBox */
    private VBox makeEventVBox(Confirmed event) {
    	final VBox eventVBox = new VBox();
    	eventVBox.setUserData(event);
    	eventVBox.setSpacing(20);
    	eventVBox.setMinWidth(400);
    	eventVBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    	     @Override
    	     public void handle(MouseEvent mouseEvent) {
    	         System.out.println("Event " + event.getTitle() + " pressed");
    	         showEventDetails(event);
    	     }
    	});
    	
    	
    	// Event content
    	Label title = new Label(event.getTitle());
    	Label date = new Label("When: " + event.getDate().toString());
    	Label location = new Label("Where: " + event.getLocation());
    	Label department = new Label("Dept: " + event.getDepartment());
    	// Todo: make fee rounded to 2 decimal places.
    	Label fee = new Label("Fee: $" + Double.toString(event.getFee()));
    	
    	// Event style
    	eventVBox.setStyle(eventVBoxCss);
    	eventVBox.getChildren().addAll(title, date, location, department, fee);
    	eventVBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 5; i++) {
    		eventVBox.getChildren().get(i).setStyle(normalFont);
    	}
    	return eventVBox;
    }
    
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
    	eventVBox.getChildren().get(0).setStyle(headingFont);
    	for (int i = 1; i < 6; i++) {
    		eventVBox.getChildren().get(i).setStyle(normalFont);
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
	
	private void setupPane() {
		/* SETUP TOP BAR */
    	topBar = new GridPane();
        topBar.setStyle(topBarCss);
        topBar.setMinHeight(100);
        topBar.setHgap(80);
        
        Label homeLabel = new Label("HOME");
        homeLabel.setStyle(filterFontNormal);
        homeLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// Todo
   	        }
   	    });
        
        Label createEventLabel = new Label("CREATE EVENT");
        createEventLabel.setStyle(filterFontNormal);
        createEventLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// Todo
   	        }
   	    });
        
        Label loginLabel = new Label("LOGIN");
        loginLabel.setStyle(filterFontNormal);
        loginLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// Todo
   	        }
   	    });
        
        Label signupLabel = new Label("SIGNUP");
        signupLabel.setStyle(filterFontNormal);
        signupLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	// Todo
   	        }
   	    });
        
        Label adminLabel = new Label("ADMIN");
        // TODO: only show label if user is admin { if (dbModel.userIsAdmin()) }
        if (true) { 
	        adminLabel.setStyle(filterFontNormal);
	        signupLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	   	        @Override
	   	        public void handle(MouseEvent mouseEvent) {
	   	        	// Todo
	   	        }
	   	    });
        }
        
        topBar.add(homeLabel, 0, 0);
        topBar.add(createEventLabel, 1, 0);
        topBar.add(loginLabel, 2, 0);
        topBar.add(signupLabel, 3, 0);
        topBar.add(adminLabel, 4, 0);
    	
        
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
        scrollContent = new VBox();
        scrollContent.setSpacing(30);
        scrollContent.setStyle(scrollContentCss);

        scrollPane = new ScrollPane();
        scrollPane.setContent(scrollContent);
        scrollPane.setStyle(scrollPaneCss);
        scrollPane.setMinSize(500, 500);
        
        borderPane = new BorderPane();
        borderPane.setTop(topBar);
//        topBar.prefWidthProperty().bind(borderPane.widthProperty());
        borderPane.setLeft(filterBar.getVBox());
        borderPane.setCenter(scrollPane);
	}
	
}
