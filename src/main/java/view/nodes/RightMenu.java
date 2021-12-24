package view.nodes;

import javafx.scene.layout.VBox;
import view.components.ModelAccordion;

public class RightMenu extends VBox {

    private ModelAccordion modelAccordion;


    public RightMenu() {
        super();

        modelAccordion = new ModelAccordion();

        getChildren().add( modelAccordion);

    }
    
    public ModelAccordion getModelAccordion() {
        return modelAccordion;
    }

}
