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

    public TabCanvas(Model model, String title) {
        super(title);

        this.canvas = new CanvasModel(model, 500,500);

        canvas.setOnMouseClicked((e)->{
        	System.out.println("MOUSE | x :" + e.getX() + " y: " + e.getY() + " z:" + e.getZ());
        });

        setText(title);
        setContent(canvas);

    }

    public TabCanvas(Model model) {
        this(model, model == null ? "" : model.getNameOfFile());
    }

    public void updateDraw() {
        this.canvas.initDraw();
    }
}
