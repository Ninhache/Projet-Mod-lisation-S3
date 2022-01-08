package view.control;

import javafx.scene.control.Accordion;
import view.components.render.TabCanvas;

/**
 * The accordion is the principal part of the right menu, it's an accordion which has multiple part, in our case, there's the slider part, and the library part :
 *      - The slider part contains 3 {@link javafx.scene.control.Slider}, the part is disabled if no {@link TabCanvas} is openned
 *      - The library part is used to loads files
 *
 * @version %I%, %G%
 * @see view.stages.OnlineLibraryStage
 * @see LibraryPane
 */
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
