package BulletinBoardProj.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CreateEventVBox {

	private GridPane grid;
	
	public CreateEventVBox() {
		setupView();
	}
	
	public GridPane getPane() {
		return grid;
	}
	
	public String getUserName() {
		return null;
	}
	
	public String getPassword() {
		return null;
	}
	
	private void setupView() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Create Event");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Label title = new Label("Title:");
		grid.add(title, 0, 1);
		TextField titleTextField = new TextField();
		grid.add(titleTextField, 1, 1);
		
		Label description = new Label("Description:");
		grid.add(description, 0, 2);
		TextField descriptionTextField = new TextField();
		grid.add(descriptionTextField, 1, 2);

		// TODO: fix regex entry
		Label date = new Label("Date:");
		grid.add(date, 0, 3);
		DatePicker datePicker = new DatePicker();
		grid.add(datePicker, 1, 3);
		
		Label building = new Label("Building:");
		grid.add(building, 0, 4);
		TextField buildingTextField = new TextField();
		grid.add(buildingTextField, 1, 4);
		
		// TODO: fix entry regex
		Label roomNumber = new Label("Room number:");
		grid.add(roomNumber, 0, 5);
		TextField roomNumberTextField = new TextField();
		grid.add(roomNumberTextField, 1, 5);
		acceptOnlyNumericInput(roomNumberTextField);
		
		Label department = new Label("Department:");
		grid.add(department, 0, 6);
		TextField departmentTextField = new TextField();
		grid.add(departmentTextField, 1, 6);
		
		// TODO: fix entry regex
		Label fee = new Label("Entry Fee:");
		grid.add(fee, 0, 7);
		TextField feeTextField = new TextField();
		grid.add(feeTextField, 1, 7);
//		acceptOnlyDecimalNumericInput(feeTextField);
		
		Button btn = new Button("Submit Event");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 8);
		
	}
	
	/* Force the text field to accept numerals only */
	private void acceptOnlyNumericInput(TextField textField) {
		
		textField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\[0-9]d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	/* Force the text field to accept decimal numerals only */
	private void acceptOnlyDecimalNumericInput(TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*\\.\\d\\d")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
}
