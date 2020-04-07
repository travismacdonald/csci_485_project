package BulletinBoardProj.ui;

import java.sql.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CreateEventVBox {

	private GridPane grid;
	private TextField titleTf;
	private TextField descriptionTf;
	private DatePicker datePicker;
	private TextField buildingTf;
	private TextField roomNoTf;
	private TextField deptTf;
	private TextField feeTf;
	Button btn;
	
	public CreateEventVBox() {
		setupView();
	}
	
	public GridPane getPane() {
		return grid;
	}
	
	public String getTitle() {
		return titleTf.getText();
	}
	
	public String getDescription() {
		return descriptionTf.getText();
	}
	
	public Date getDate() {
		return Date.valueOf(datePicker.valueProperty().get());
	}
	
	public String getBuilding() {
		return buildingTf.getText();
	}
	
	public String getDept() {
		return deptTf.getText();
	}
	
	public Double getFee() {
		return Double.valueOf(feeTf.getText());
	}
	
	public int getRoomNo() {
		return Integer.valueOf(roomNoTf.getText());
	}
	
	public Button getCreateButton() {
		return btn;
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
		titleTf = new TextField();
		grid.add(titleTf, 1, 1);
		
		Label description = new Label("Description:");
		grid.add(description, 0, 2);
		descriptionTf = new TextField();
		grid.add(descriptionTf, 1, 2);

		// TODO: fix regex entry
		Label date = new Label("Date:");
		grid.add(date, 0, 3);
		datePicker = new DatePicker();
		grid.add(datePicker, 1, 3);
		
		Label building = new Label("Building:");
		grid.add(building, 0, 4);
		buildingTf = new TextField();
		grid.add(buildingTf, 1, 4);
		
		// TODO: fix entry regex
		Label roomNumber = new Label("Room number:");
		grid.add(roomNumber, 0, 5);
		roomNoTf = new TextField();
		grid.add(roomNoTf, 1, 5);
		acceptOnlyNumericInput(roomNoTf);
		
		Label department = new Label("Department:");
		grid.add(department, 0, 6);
		deptTf = new TextField();
		grid.add(deptTf, 1, 6);
		
		// TODO: fix entry regex
		Label fee = new Label("Entry Fee:");
		grid.add(fee, 0, 7);
		feeTf = new TextField();
		grid.add(feeTf, 1, 7);
//		acceptOnlyDecimalNumericInput(feeTextField);
		
		btn = new Button("Submit Event");
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
