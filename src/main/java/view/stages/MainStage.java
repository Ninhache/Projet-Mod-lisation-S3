package view.stages;

import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import view.dialogs.SuperToolBar;

public class MainStage extends ExtendedStage {

    public MainStage() {
        super();

        BorderPane root = new BorderPane();
        SuperToolBar sp = new SuperToolBar();

        root.setTop(sp);

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("test");
        scene.getStylesheets().add(getClass().getResource("/css/javafxTestCSS.css").toExternalForm());
        System.out.println(getClass().getResource("/css/javafxTestCSS.css").toExternalForm());
    }
}
