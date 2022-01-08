package view.components;

import javafx.scene.layout.VBox;
import view.components.ModelAccordion;

/**
 * It's the right menu of the main windows
 * @author Néo ALMEIDA
 * @version %I%, %G%
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
