package BulletinBoardProj.ui;

import BulletinBoardProj.Databases.Event;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class EventScroll {

    final String containerCss =
    	    "-fx-padding: 20;\n" +
    	    "-fx-background-color: transparent;\n";
    
    final String contentCss = 
    		"-fx-spacing: 20;\n";
    
    private ScrollPane container;
    private VBox content;
    
    public EventScroll() {
    	setupView();
    }
    
    public ScrollPane getPane() {
    	return container;
    }
    
    public void addEventItem(EventScrollItem item) {
    	content.getChildren().add(item.getPane());
    }
    
    public void clear() {
    	content.getChildren().clear();
    }
    
    private void setupView() {
        content = new VBox();
        content.setStyle(contentCss);

        container = new ScrollPane();
        container.setContent(content);
        container.setStyle(containerCss);
        container.setMinSize(500, 500);
    }
	
}
