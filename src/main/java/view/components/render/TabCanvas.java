package view.components.render;

import javafx.scene.paint.Color;
import model.models.Model;
import view.components.render.CanvasModel;
import view.stages.DraggableTab;


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

    public void setVerticesColor(Color color) {
    	this.canvas.setVerticesColor(color);
    }

    public void setFacesColor(Color color) {
        this.canvas.setFacesColor(color);
    }

    public void setStrokesColor(Color color) {
        this.canvas.setStrokesColor(color);
    }

    public void setBackgroundColor(Color color) {
        this.canvas.setBackgroundColor(color);
    }

    public Color getVerticesColor() {
        return this.canvas.getVerticesColor();
    }

    public Color getFacesColor() {
        return this.canvas.getFacesColor();
    }

    public Color getStrokesColor() {
        return this.canvas.getStrokesColor();
    }

    public Color getBackgroundColor() {
        return this.canvas.getBackgroundColor();
    }

}
