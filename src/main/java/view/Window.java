package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Window extends Application {
	
	BorderPane root=new BorderPane();
	
//	public static BorderPane createBP() {
//		BorderPane bp=new BorderPane();
//		Label top=new Label("Toolbar");
//		bp.setTop(top);
//		return bp;
//		
//	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Woah c'est bo");
		Scene scene=new Scene(root,1080,720);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
