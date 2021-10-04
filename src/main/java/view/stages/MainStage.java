package view.stages;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import view.dialogs.SuperToolBar;

public class MainStage extends ExtendedStage {

    public MainStage() {
        super();

        BorderPane root = new BorderPane();
        SuperToolBar sp = new SuperToolBar();
        Canvas canvas = new Canvas(500,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0,0,canvas.getHeight(), canvas.getWidth());
        gc.fill();

        root.setTop(sp);
        root.setCenter(canvas);

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("test");
        scene.getStylesheets().add(getClass().getResource("/css/javafxTestCSSWHITE.css").toExternalForm());
    }
}
