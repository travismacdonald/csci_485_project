package BulletinBoardProj;


import java.util.ArrayList;
import java.util.List;

import BulletinBoardProj.Databases.Confirmed;
import BulletinBoardProj.Databases.Database485;
import BulletinBoardProj.ui.AdminPage;
import BulletinBoardProj.ui.BBPage;
import BulletinBoardProj.ui.EventScroll;
import BulletinBoardProj.ui.FilterBar;
import BulletinBoardProj.ui.HomePage;
import BulletinBoardProj.ui.LoginPage;
import BulletinBoardProj.ui.NavBar;
import BulletinBoardProj.ui.SignupPage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Todo: put scroll bar back to top when events get filtered

public class Main extends Application implements UIController {

	private enum Page {
		HOME,
		ADMIN,
		CREATE_EVENT,
		LOGIN,
		SIGNUP
	}
	
//	private HomePage homePage = null;
//	private LoginPage loginPage = null;
//	private AdminPage adminPage = null;
//	private SignupPage signupPage = null;

    private DBModel dbModel;
    private Stage primaryStage;
    private BorderPane borderPane;
    private Page curPage;
    
    private FilterBar filterBar;
    private EventScroll eventScroll;
    private NavBar navBar;
    
    private boolean filterBarIsVisible;
    
    final private int minWidth = 800;
    final private int minHeight = 600;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
    	dbModel = new DBModel();
    	filterBarIsVisible = false;
    	
    	setupView();
        navToApplicationStart();
    }
    
    private void setupView() {
    	primaryStage.setMinHeight(minHeight);
        primaryStage.setMinWidth(minWidth);
        primaryStage.initStyle(StageStyle.DECORATED);
    	primaryStage.setMaximized(true);
        primaryStage.setTitle("CSCI 485");
        
        borderPane = new BorderPane();
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }
    
    /* Takes care of initial UI setup */
    private void navToApplicationStart() {
    	filterBar = new FilterBar();
    	navBar = new NavBar();
    	eventScroll = new EventScroll();
    	// TODO: Login stuff
    	
    	/* Setup click listeners for navigation bar */
    	
    	borderPane.setTop(navBar.getPane());
    	navBar.getHomeLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	navToHomePage();
   	        }
   	    });
		navBar.getLoginLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	navToLoginPage();
   	        }
   	    });
		navBar.getSignupLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	navToSignupPage();
   	        }
   	    });
		navBar.getAdminLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	navToAdminPage();
   	        }
   	    });
		navBar.getCreateEventLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	        	navToCreateEventPage();
   	        }
   	    });
		
		/* Setup click listeners for filter bar */
		
        filterBar = new FilterBar();
        filterBar.getDateLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            onDateFilter();
   	        }
   	    });
        filterBar.getDeptLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            onDeptFilter();
   	        }
   	    });
        filterBar.getFeeLabel().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            onFeeFilter();
   	        }
   	    });
    	navToHomePage();
    }
    
    private void navToHomePage() {
    	if (!filterBarIsVisible) {
    	    borderPane.setLeft(filterBar.getPane());
    	    filterBarIsVisible = true;
    	}
    	curPage = Page.HOME;
    	onDateFilter();
    }
    
    private void navToAdminPage() {
    	curPage = Page.ADMIN;
    }
    
    private void navToCreateEventPage() {
    	curPage = Page.CREATE_EVENT;
    }
    
    private void navToSignupPage() {
    	curPage = Page.SIGNUP;
    }
    
    private void navToLoginPage() {
    	curPage = Page.LOGIN;
    }
    
//    private 
//    
//    private Scene getHomePage() {
//    	if (homePage == null) {
//    	    homePage = new HomePage();
//    	    homePage.showAllEvents(dbModel.getEventsByDate());
//    	    homePage.setController(this);
//    	    curPage = homePage;
//    	}
//    	return new Scene(homePage.getPane());
//    }
//    
//    private Scene getAdminPage() {
//    	if (adminPage == null) {
//    		adminPage = new AdminPage();
//    		// adminPage.showAllEvents(dbModel.getEventsByDate()) // todo: write method for requested events
//    		adminPage.setController(this);
//    		curPage = adminPage;
//    	}
//    	return new Scene(adminPage.getPane());
//    }
    
//    private Scene getLoginPage() {
//    	return null; // todo
//    }
//    
//    private Scene getSignupPage() {
//    	return null; // todo
//    }


    public static void main(String[] args) {
        launch(args);
    }
    

	@Override
	public void onDateFilter() {
		if (curPage == Page.HOME) {
//			homePage.showAllEvents(dbModel.getEventsByDate());
			// TODO: scroll.showall ...
		}
		else if (curPage == Page.ADMIN) {
			// TODO: scroll.showallrequested ...
		}
	}

	@Override
	public void onFeeFilter() {
		if (curPage == Page.HOME) {
//			homePage.showAllEvents(dbModel.getEventsByDate());
			// TODO: scroll.showall ...
		}
		else if (curPage == Page.ADMIN) {
			// TODO: scroll.showallrequested ...
		}
		
	}

	@Override
	public void onDeptFilter() {
		if (curPage == Page.HOME) {
//			homePage.showAllEvents(dbModel.getEventsByDate());
			final List<Confirmed> eventList = dbModel.getEventsByDate();
			
			// TODO: scroll.showall ...
		}
		else if (curPage == Page.ADMIN) {
			// TODO: scroll.showallrequested ...
		}
	}
}
