package view.components;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CanvasBox extends VBox {

    public CanvasBox() {

        getChildren().addAll(new Button("2 mins svp"), new Button("ça charge"));
    }

}
