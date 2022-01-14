package view.components.render;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.models.Model;
import view.stages.DraggableTab;


/**
 * The TabCanvas is just a Tab, handeling a canvas
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 * @see javafx.scene.control.Tab
 * @see CanvasModelTop
 */
public class TabCanvas extends DraggableTab {

    private CanvasModelTop canvasTop;
    private CanvasModelLeft canvasLeft;
    private CanvasModelRight canvasRight;


    private VBox root;

    private HBox hboxTop, hboxBot, hBoxLeft,hBoxRight;



    public TabCanvas(Model model, String title, double width, double height) {
        super(title);

        this.canvasTop = new CanvasModelTop(model,width,height);
        this.canvasLeft = new CanvasModelLeft(model,width,height);
        this.canvasRight = new CanvasModelRight(model,width,height);


        this.root = new VBox();
        this.hboxTop = new HBox();
        this.hboxBot = new HBox();
        this.hBoxLeft = new HBox();
        this.hBoxRight = new HBox();

        this.root.getChildren().addAll(hboxTop, hboxBot);
        this.hboxBot.getChildren().addAll(hBoxLeft, hBoxRight);

        this.hboxTop.getChildren().addAll(canvasTop);
        this.hBoxLeft.getChildren().addAll(canvasLeft);
        this.hBoxRight.getChildren().addAll(canvasRight);

        setContent(root);
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
        this.canvasTop.initDraw();
    }

    public void draw() {
        this.canvasTop.draw();
    }

    public CanvasModelTop getCanvasTop() {
        return canvasTop;
    }

    public void setVerticesColor(Color color) {
    	this.canvasTop.setVerticesColor(color);
    }

    public void setFacesColor(Color color) {
        this.canvasTop.setFacesColor(color);
    }

    public void setStrokesColor(Color color) {
        this.canvasTop.setStrokesColor(color);
    }

    public void setBackgroundColor(Color color) {
        this.canvasTop.setBackgroundColor(color);
    }

    public Color getVerticesColor() {
        return this.canvasTop.getVerticesColor();
    }

    public Color getFacesColor() {
        return this.canvasTop.getFacesColor();
    }

    public Color getStrokesColor() {
        return this.canvasTop.getStrokesColor();
    }

    public Color getBackgroundColor() {
        return this.canvasTop.getBackgroundColor();
    }

}
