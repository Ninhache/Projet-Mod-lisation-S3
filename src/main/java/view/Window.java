package view;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import view.stages.MainStage;

public class Window extends Application {

	private static HostServices services;
	
	@Override
	public void start(Stage stage) {
		services = getHostServices();
		MainStage main = new MainStage();
		
		main.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static HostServices getServices() {
		return services;
	}
}
