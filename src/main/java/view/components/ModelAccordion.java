package view.components;

import javafx.scene.control.Accordion;
import view.components.tiltedPane.LibraryPane;
import view.components.tiltedPane.SlidersModelPane;

public class ModelAccordion extends Accordion {

    private SlidersModelPane slidersModel;
    private LibraryPane libraryPane;


    public ModelAccordion() {
        super();
        slidersModel = new SlidersModelPane();
        libraryPane = new LibraryPane();

        getPanes().addAll(slidersModel,libraryPane);
    }

    public LibraryPane getLibraryPane() {
        return libraryPane;
    }

    public SlidersModelPane getSlidersModel() {
        return slidersModel;
    }
}
