package view.components.tabpane;

import model.Model;
import view.components.CanvasModel;

/**
 * The TabCanvas is just a Tab, handeling a canvas
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 * @see javafx.scene.control.Tab
 * @see CanvasModel
 */
public class TabCanvas extends DraggableTab {

    private CanvasModel canvas;

    public TabCanvas(Model model, String title, double width, double height) {
        super(title);

        this.canvas = new CanvasModel(model,width,height);

        //setText(title);
        setContent(canvas);

    }

    public TabCanvas(Model model, double width, double height) {
        this(model, model == null ? "" : model.getNameOfFile(), width,height);
    }

    public TabCanvas(Model model) {
        this(model, model == null ? "" : model.getNameOfFile(), 500,500);
    }

    public TabCanvas() {
        super("null");
    }
    
    public void initDraw() {
        this.canvas.initDraw();
    }

    public void draw() {
        this.canvas.draw();
    }

    public CanvasModel getCanvas() {
        return canvas;
    }
}
