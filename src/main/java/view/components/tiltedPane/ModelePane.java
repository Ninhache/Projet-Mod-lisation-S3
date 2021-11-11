package view.components.tiltedPane;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.components.CanvasModel;

public class ModelePane extends TitledPane {

    public ModelePane() {
        VBox rootPane = new VBox();

        // need les 3 vues mdr

        setContent(rootPane);
    }

}
