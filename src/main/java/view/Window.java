package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.stages.MainStage;

public class Window extends Application {
	
//	public static BorderPane createBP() {
//		BorderPane bp=new BorderPane();
//		Label top=new Label("Toolbar");
//		bp.setTop(top);
//		return bp;
//		
//	}
	
	@Override
	public void start(Stage stage) {

		MainStage main = new MainStage();
		main.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
