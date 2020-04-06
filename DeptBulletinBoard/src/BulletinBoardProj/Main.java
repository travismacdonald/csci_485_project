package BulletinBoardProj;


import java.util.List;

import BulletinBoardProj.Databases.Confirmed;
import BulletinBoardProj.ui.AdminPage;
import BulletinBoardProj.ui.BBPage;
import BulletinBoardProj.ui.HomePage;
import BulletinBoardProj.ui.LoginPage;
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

	private HomePage homePage = null;
	private LoginPage loginPage = null;
	private AdminPage adminPage = null;
	private SignupPage signupPage = null;
	private BBPage curPage = null;
    private DBModel dbModel;
    private Stage primaryStage;
    
    final private int minWidth = 800;
    final private int minHeight = 600;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
    	dbModel = new DBModel();
    	
    	setupPrimaryStage(primaryStage);
        this.primaryStage.setScene(getHomePage());
        this.primaryStage.show();
    }
    
    private void setupPrimaryStage(Stage stage) {
    	primaryStage = stage; 
    	primaryStage.setMinHeight(minHeight);
        primaryStage.setMinWidth(minWidth);
        primaryStage.initStyle(StageStyle.DECORATED);
    	primaryStage.setMaximized(true);
        primaryStage.setTitle("CSCI 485");
        primaryStage.initStyle(StageStyle.DECORATED);
    }
    
    private Scene getHomePage() {
    	if (homePage == null) {
    	    homePage = new HomePage();
    	    homePage.showAllEvents(dbModel.getEventsByDate());
    	    homePage.setController(this);
    	    curPage = homePage;
    	}
    	return new Scene(homePage.getPane());
    }
    
    private Scene getAdminPage() {
    	if (adminPage == null) {
    		adminPage = new AdminPage();
    		// adminPage.showAllEvents(dbModel.getEventsByDate()) // todo: write method for requested events
    		adminPage.setController(this);
    		curPage = adminPage;
    	}
    	return new Scene(adminPage.getPane());
    }
    
    private Scene getLoginPage() {
    	return null; // todo
    }
    
    private Scene getSignupPage() {
    	return null; // todo
    }


    public static void main(String[] args) {
        launch(args);
    }
    

	@Override
	public void onDateFilter() {
		if (curPage instanceof HomePage) {
			homePage.showAllEvents(dbModel.getEventsByDate());
			
		}
		else if (curPage instanceof AdminPage) {
			// TODO
		}
	}

	@Override
	public void onFeeFilter() {
		if (curPage instanceof HomePage) {
			homePage.showAllEvents(dbModel.getEventsByFee());
		}
		else if (curPage instanceof AdminPage) {
			// TODO
		}
		
	}

	@Override
	public void onDeptFilter() {
		if (curPage instanceof HomePage) {
			homePage.showAllEvents(dbModel.getEventsByDept());
		}
		else if (curPage instanceof AdminPage) {
			// TODO
		}
		
	}
}
