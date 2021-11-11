package view.nodes;

import javafx.scene.layout.VBox;
import view.components.CanvasBox;
import view.components.ModelAccordion;

public class RightMenu extends VBox {

    public RightMenu() {
        super();



        getChildren().addAll(new CanvasBox(), new ModelAccordion());

    }

}
