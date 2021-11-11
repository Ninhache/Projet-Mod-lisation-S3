package view.components.tiltedPane;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.components.CanvasModel;

public class ModelePane extends TitledPane {

    public ModelePane() {
        VBox rootPane = new VBox();

        // need les 3 vues mdr

        Canvas canvas = new Canvas(getWidth(), getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        gc.fillRect(0,0,getWidth(), getHeight());
        gc.fill();

        rootPane.getChildren().add(canvas);

        expandedProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(rootPane.getWidth() + " " + rootPane.getHeight());

            gc.setFill(Color.RED);
            gc.fillRect(0,0,getWidth(), getHeight());
            gc.fill();
        });

        setContent(rootPane);
    }

}
