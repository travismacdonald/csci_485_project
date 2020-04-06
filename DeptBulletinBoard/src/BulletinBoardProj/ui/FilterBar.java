package BulletinBoardProj.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Class responsible for building the filter bar for querying database events.
 *
 */
public class FilterBar {

    final String filterHeadingFont =
    		"-fx-font: 34 calibri;\n" +
            "-fx-text-fill: white;\n" +
    	    "-fx-font-weight: bold;\n";
    
    final String filterFontNormal =
    		"-fx-font: 28 calibri;\n" +
    		"-fx-text-fill: white;\n" +
    		"-fx-cursor: hand;\n";
    
    final String filterBarCss =
    		"-fx-padding: 10;\n" +
    	    "-fx-spacing: 15;\n" +
            "-fx-background-color: black;\n";
    
    private VBox vBox;
    private Label orderByLabel;
    private Label dateLabel;
    private Label feeLabel;
    private Label deptLabel;
	
	
	public FilterBar() {
		setupView();
	}
	
	public Pane getPane() {
		return vBox;
	}
	
	public Label getDateLabel() {
		return dateLabel;
	}
	
	public Label getFeeLabel() {
		return feeLabel;
	}
	
	public Label getDeptLabel() {
		return deptLabel;
	}
	
	private void setupView() {
		/* SETUP LEFT BAR */
    	vBox = new VBox();
        vBox.setStyle(filterBarCss);
        vBox.setMinWidth(180); // TODO: move to CSS
        
        orderByLabel = new Label("ORDER BY:");
        orderByLabel.setStyle(filterHeadingFont);
        
        dateLabel = new Label("Date");
        dateLabel.setStyle(filterFontNormal);
        deptLabel = new Label("Dept");
        deptLabel.setStyle(filterFontNormal);
        feeLabel = new Label("Fee");
        feeLabel.setStyle(filterFontNormal);
        
        vBox.getChildren().addAll(orderByLabel, dateLabel, deptLabel, feeLabel);

	}
}
