package view;

import javafx.scene.layout.VBox;

/**
 * It's the right menu of the main windows
 *
 * @see ModelAccordion
 */
public class RightMenu extends VBox {

    private ModelAccordion modelAccordion;

    public RightMenu() {
        super();

        setMinWidth(500);
        modelAccordion = new ModelAccordion();

        getChildren().add( modelAccordion);

    }
    
    public ModelAccordion getModelAccordion() {
        return modelAccordion;
    }
}
