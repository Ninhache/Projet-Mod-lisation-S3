package view;

import javafx.scene.canvas.Canvas;
import model.Model;

/**
 * This canvas take a model in parameters
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class ModelCanvas extends Canvas {

    private final Model model;

    public ModelCanvas(Model model) {
        super();
        this.model = model;
    }
}
