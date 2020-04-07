package BulletinBoardProj;


import java.util.List;

import BulletinBoardProj.Databases.Event;
import BulletinBoardProj.Databases.User;
import BulletinBoardProj.ui.CreateEventVBox;
import BulletinBoardProj.ui.EventDetailWindow;
import BulletinBoardProj.ui.EventScroll;
import BulletinBoardProj.ui.EventScrollItem;
import BulletinBoardProj.ui.FilterBar;
import BulletinBoardProj.ui.UserFormVBox;
import BulletinBoardProj.ui.NavBar;
import BulletinBoardProj.ui.SignupVBox;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// Todo: put scroll bar back to top when events get filtered

public class Main extends Application {

	private enum Page {
		HOME,
		ADMIN,
		CREATE_EVENT,
		LOGIN,
		SIGNUP
	}

    private DBModel dbModel;
    private Stage primaryStage;
    private BorderPane borderPane;
    
    private FilterBar filterBar;
    private EventScroll eventScroll;
    private NavBar navBar;
    private UserFormVBox userFormVBox;
    private SignupVBox signupVBox;
    private CreateEventVBox createEventVBox;
    
    private boolean filterBarIsVisible;
    private Page curPage;
    
    final private int minWidth = 800;
    final private int minHeight = 600;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	dbModel = new DBModel();
    	this.primaryStage = primaryStage;
    	
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
    	filterBarIsVisible = false;
    	
    	navBar = new NavBar();
    	filterBar = new FilterBar();
    	userFormVBox = new UserFormVBox();
    	eventScroll = new EventScroll();
    	
    	/* Setup click listeners for navigation bar */
    	
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
        
        /* User Signup and Login Event handler */
        
        userFormVBox.getLoginButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            onValidateAttempt();
   	        }
   	    });


        borderPane.setTop(navBar.getPane());
    	// TODO: delete this later; just testing for now
    	navBar.showAdminLabel();
    	navToHomePage();
    }
    
    private void navToHomePage() {
    	if (!filterBarIsVisible) {
    	    borderPane.setLeft(filterBar.getPane());
    	    filterBarIsVisible = true;
    	}
    	curPage = Page.HOME;
    	onDateFilter();
    	borderPane.setCenter(eventScroll.getPane());
    }
    
    private void navToAdminPage() {
    	if (!filterBarIsVisible) {
    	    borderPane.setLeft(filterBar.getPane());
    	    filterBarIsVisible = true;
    	}
    	curPage = Page.ADMIN;
    	onDateFilter();
    	borderPane.setCenter(eventScroll.getPane());
    }
    
    private void navToCreateEventPage() {
    	if (filterBarIsVisible) {
    		borderPane.setLeft(null);
    		filterBarIsVisible = false;
    	}
    	curPage = Page.CREATE_EVENT;
    	createEventVBox = new CreateEventVBox();
    	borderPane.setCenter(createEventVBox.getPane());
    }
    
    private void navToSignupPage() {
    	if (filterBarIsVisible) {
    		borderPane.setLeft(null);
    		filterBarIsVisible = false;
    	}
    	curPage = Page.SIGNUP;
    	userFormVBox.clear();
    	userFormVBox.setTypeAsSignup();
    	borderPane.setCenter(userFormVBox.getPane());
    }
    
    private void navToLoginPage() {
    	if (filterBarIsVisible) {
    		borderPane.setLeft(null);
    		filterBarIsVisible = false;
    	}
    	curPage = Page.LOGIN;
    	userFormVBox.clear();
    	userFormVBox.setTypeAsLogin();
    	borderPane.setCenter(userFormVBox.getPane());
    }    

	public void onDateFilter() {
		if (curPage == Page.HOME) {
			showAllEvents(dbModel.getConfirmedEventsByDate());
		}
		else if (curPage == Page.ADMIN) {
			showAllEvents(dbModel.getRequestedEventsByDate());
		}
	}

	public void onFeeFilter() {
		if (curPage == Page.HOME) {
			showAllEvents(dbModel.getConfirmedEventsByFee());
		}
		else if (curPage == Page.ADMIN) {
			showAllEvents(dbModel.getRequestedEventsByFee());
		}
		
	}

	public void onDeptFilter() {
		if (curPage == Page.HOME) {
			showAllEvents(dbModel.getConfirmedEventsByDept());
		}
		else if (curPage == Page.ADMIN) {
			showAllEvents(dbModel.getRequestedEventsByDept());
		}
	}
	
	private void showAllEvents(List<Event> eventList) {
		eventScroll.clear();
		for (Event event : eventList) {
			final EventScrollItem item = new EventScrollItem(event);
			item.getPane().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	   	        @Override
	   	        public void handle(MouseEvent mouseEvent) {
	   	            if (curPage == Page.HOME) {
	   	            	final EventDetailWindow window = new EventDetailWindow(event, false);
	   	            	setupNormalWindow(window);
	   	            	window.getStage().show();
	   	            }
	   	            else if (curPage == Page.ADMIN) {
	   	            	final EventDetailWindow window = new EventDetailWindow(event, true);
	   	            	setupAdminWindow(window);
	   	            	window.getStage().show();
	   	            }
	   	        }
	   	    });
			eventScroll.addEventItem(item);
		}
	}
	
	private void setupAdminWindow(EventDetailWindow window) {
		window.getAcceptButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            dbModel.approveEvent(window.getEvent());
   	            window.getStage().close();
   	            onDateFilter(); // TODO: just remove the event from the list instead
   	        }
   	    });
		window.getRejectButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
   	        @Override
   	        public void handle(MouseEvent mouseEvent) {
   	            dbModel.rejectEvent(window.getEvent());
   	            window.getStage().close();
   	            onDateFilter(); // TODO: just remove the event from the list instead
   	        }
   	    });
	}
	
	private void setupNormalWindow(EventDetailWindow window) {
		// TODO
	}
	
	private void onValidateAttempt() {
		final User user = dbModel.getCurUser();
		user.setName(userFormVBox.getUserName());
		user.setPass(userFormVBox.getPassword());
		
		/* LOGIN FORM */
		if (curPage == Page.LOGIN) {
			if (dbModel.loginUser(user)) {
				navToHomePage();
			}
			else {
				// TODO: show failed login attempt
			}
		}
		
		/* SIGNUP FORM*/
		else if (curPage == Page.SIGNUP) {
			if (dbModel.registerUser(user)) {
				navToHomePage();
			}
			else {
				// TODO: show failed signup attempt
			}
		}
		
	}
}


