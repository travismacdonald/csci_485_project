package BulletinBoardProj.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavBar {

    final String navBarCss =
    		"-fx-padding: 30;\n" +
    		"-fx-spacing: 30;\n" +
    		"-fx-background-color: grey;\n";
    
    final String normalFont =
    		"-fx-font: 28 calibri;\n" +
    		"-fx-text-fill: white;\n" +
    		"-fx-cursor: hand;\n";
	
	private HBox hBox;
	private Label homeLabel;
	private Label loginLabel;
	private Label signupLabel;
	private Label adminLabel;
	private Label createEventLabel;
	private Label signOutLabel;
	
	private boolean adminLabelIsVisible;
	private boolean signOutLabelIsVisible;
	private boolean loginLabelIsVisible;
	private boolean signupLabelIsVisible;
	
	public NavBar() {
		adminLabelIsVisible = false;
		signOutLabelIsVisible = false;
		loginLabelIsVisible = false;
		signupLabelIsVisible = false;
		setupView();
	}
	
	public Pane getPane() {
		return hBox;
	}
	
	public Label getHomeLabel() {
		return homeLabel;
	}
	
	public Label getLoginLabel() {
		return loginLabel;
	}
	
	public Label getSignupLabel() {
		return signupLabel;
	}
	
	public Label getAdminLabel() {
		return adminLabel;
	}
	
	public Label getCreateEventLabel() {
		return createEventLabel;
	}
	
	public Label getSignOutLabel() {
		return signOutLabel;
	}
	
	public void showAdminLabel() {
		hBox.getChildren().add(adminLabel);
		adminLabelIsVisible = true;
	}
	
	public void hideAdminLabel() {
		hBox.getChildren().remove(adminLabel);
		adminLabelIsVisible = false;
	}
	
	public void showLoginLabel() {
		hBox.getChildren().add(loginLabel);
		loginLabelIsVisible = true;
	}
	
	public void hideLoginLabel() {
		hBox.getChildren().remove(loginLabel);
		loginLabelIsVisible = false;
	}
	
	public void showSignOutLabel() {
		hBox.getChildren().add(signOutLabel);
		signOutLabelIsVisible = true;
	}
	
	public void hideSignOutLabel() {
		hBox.getChildren().remove(signOutLabel);
		signOutLabelIsVisible = false;
	}
	
	public void showSignupLabel() {
		hBox.getChildren().add(signupLabel);
		signupLabelIsVisible = true;
	}
	
	public void hideSignupLabel() {
		hBox.getChildren().remove(signupLabel);
		signupLabelIsVisible = false;
	}
	
	public boolean adminLabelIsVisible() {
		return adminLabelIsVisible;
	}
	
	public boolean loginLabelIsVisible() {
		return loginLabelIsVisible;
	}
	
	public boolean signupLabelIsVisible() {
		return signupLabelIsVisible;
	}
	
	public boolean signOutLabelIsVisible() {
		return signOutLabelIsVisible;
	}
	
	private void setupView() {
    	hBox = new HBox();
        hBox.setStyle(navBarCss);
        hBox.setMinHeight(100);
        
        homeLabel = new Label("HOME");
        homeLabel.setStyle(normalFont);
        createEventLabel = new Label("CREATE EVENT");
        createEventLabel.setStyle(normalFont);
        loginLabel = new Label("LOGIN");
        loginLabel.setStyle(normalFont);
        signupLabel = new Label("SIGNUP");
        signupLabel.setStyle(normalFont);
        
        adminLabel = new Label("ADMIN"); 
	    adminLabel.setStyle(normalFont);
	    
	    signOutLabel = new Label("SIGN OUT"); 
	    signOutLabel.setStyle(normalFont);
        
        hBox.getChildren().add(homeLabel);
        hBox.getChildren().add(createEventLabel);
        
        /* doesn't add login, signup, logout, or adminLabel by default - must be explicitly added */
	}
	
}
