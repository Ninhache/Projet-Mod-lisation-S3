package view.components;

import javafx.scene.control.Accordion;
import view.components.tiltedPane.LibraryPane;
import view.components.tiltedPane.ModelePane;
import view.components.tiltedPane.SlidersModel;

public class ModelAccordion extends Accordion {

    public ModelAccordion() {
        super();

        getPanes().addAll(new ModelePane(),new SlidersModel(),new LibraryPane());

    }

}
