package view.components;

import javafx.scene.control.Accordion;
import view.components.tiltedPane.LibraryPane;
import view.components.tiltedPane.ModelePane;
import view.components.tiltedPane.SlidersModel;

public class ModelAccordion extends Accordion {

    private SlidersModel slidersModel;
    private LibraryPane libraryPane;


    public ModelAccordion() {
        super();
        slidersModel = new SlidersModel();
        libraryPane = new LibraryPane();

        getPanes().addAll(slidersModel,libraryPane);
    }

    public LibraryPane getLibraryPane() {
        return libraryPane;
    }

    public SlidersModel getSlidersModel() {
        return slidersModel;
    }
}
