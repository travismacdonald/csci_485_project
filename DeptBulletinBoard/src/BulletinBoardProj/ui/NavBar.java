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
	private boolean adminLabelIsVisible;
	
	public NavBar() {
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
	
	public void showAdminLabel() {
		if (!adminLabelIsVisible()) {
			hBox.getChildren().add(adminLabel);
		}
	}
	
	public void hideAdminLabel() {
		if (adminLabelIsVisible()) {
			hBox.getChildren().remove(adminLabel);
		}
	}
	
	public boolean adminLabelIsVisible() {
		return adminLabelIsVisible;
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
        
        hBox.getChildren().add(homeLabel);
        hBox.getChildren().add(createEventLabel);
        hBox.getChildren().add(loginLabel);
        hBox.getChildren().add(signupLabel);
        /* doesn't add the admin label by default - must be explicitly added */
	}
	
}
