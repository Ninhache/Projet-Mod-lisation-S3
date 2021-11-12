package view.nodes;

import javafx.scene.layout.VBox;
import view.components.CanvasBox;
import view.components.ModelAccordion;

public class RightMenu extends VBox {

    private CanvasBox canvasBox;
    private ModelAccordion modelAccordion;


    public RightMenu() {
        super();

        canvasBox = new CanvasBox();
        modelAccordion = new ModelAccordion();

        getChildren().addAll(canvasBox, modelAccordion);

    }

    public ModelAccordion getModelAccordion() {
        return modelAccordion;
    }
    
    public CanvasBox getCanvasBox() {
		return canvasBox;
	}
}
