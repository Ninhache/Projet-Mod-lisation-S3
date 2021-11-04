package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import model.Model;

/**
 * The TabCanvas is just a Tab, handeling a canvas
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 * @see javafx.scene.control.Tab
 * @see view.CanvasModel
 */
public class TabCanvas extends Tab {

    private CanvasModel canvas;

    public TabCanvas(Model model, String title, double width, double height) {
        super(title);

        this.canvas = new CanvasModel(model,width,height);

        setText(title);
        setContent(canvas);

    }

    public TabCanvas(Model model, double width, double height) {
        this(model, model == null ? "" : model.getNameOfFile(), width,height);
    }

    public TabCanvas(Model model) {
        this(model, model == null ? "" : model.getNameOfFile(), 500,500);
    }

    public void updateDraw() {
        this.canvas.initDraw();
    }

    public CanvasModel getCanvas() {
        return canvas;
    }
}
