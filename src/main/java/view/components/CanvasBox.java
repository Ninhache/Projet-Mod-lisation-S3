package view.components;

import javafx.scene.layout.VBox;

public class CanvasBox extends VBox {

    public CanvasBox() {

        getChildren().addAll(new CanvasSingle(), new CanvasSingle());
    }

}
