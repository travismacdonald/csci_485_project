package BulletinBoardProj.ui;

import BulletinBoardProj.UIController;
import javafx.scene.layout.Pane;

public interface BBPage {
	
	void setController(UIController controller);
	
	Pane getPane();
	
}
