package BulletinBoardProj.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UserFormVBox {

	private GridPane grid;
	
	private TextField userTextField;
	private PasswordField passwordField;
	private Text formTitle;
	private Button validateButton;
	
	private final String loginStr = "Login";
	private final String signupStr = "Signup";
	
	public UserFormVBox() {
		setupView();
	}
	
	public GridPane getPane() {
		return grid;
	}
	
	public String getUserName() {
		return userTextField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	public Button getLoginButton() {
		return validateButton;
	}
	
	public void clear() {
		userTextField.clear();
		passwordField.clear();
	}
	
	public void setTypeAsLogin() {
		formTitle.setText(loginStr);
		validateButton.setText(loginStr);
	}
	
	public void setTypeAsSignup() {
		formTitle.setText(signupStr);
		validateButton.setText(signupStr);
	}
	
	private void setupView() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		formTitle = new Text();
		formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(formTitle, 0, 0, 2, 1);
		
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

	    userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		passwordField = new PasswordField();
		grid.add(passwordField, 1, 2);
		
		validateButton = new Button();
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(validateButton);
		grid.add(hbBtn, 1, 4);
	}
	
	
	
}
