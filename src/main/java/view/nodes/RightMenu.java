package view.nodes;

import javafx.scene.layout.VBox;
import view.components.CanvasBox;
import view.components.ModelAccordion;

public class RightMenu extends VBox {

    private ModelAccordion modelAccordion;
    private CanvasBox canvasBox;


    public RightMenu() {
        super();

        modelAccordion = new ModelAccordion();
        canvasBox = new CanvasBox();

        getChildren().addAll(canvasBox, modelAccordion);

    }

    public ModelAccordion getModelAccordion() {
        return modelAccordion;
    }
}
