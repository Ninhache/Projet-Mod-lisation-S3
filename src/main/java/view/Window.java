package view;

import javafx.application.Application;
import javafx.stage.Stage;
import view.stages.MainStage;

public class Window extends Application {
	
	@Override
	public void start(Stage stage) {
		MainStage main = new MainStage();
		//main.initStyle(StageStyle.UTILITY); // => Permet de retirer les bordures de la fenêtre

		main.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
