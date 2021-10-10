package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Model;

public class CanvasModel extends Canvas {

    private Model model;

    public CanvasModel(Model model, double width, double height) {
        super(width, height);
        this.model = model;


        widthProperty().addListener(e -> {
            this.draw();
        });

        heightProperty().addListener(e -> {
            this.draw();
        });
    }

    public CanvasModel(Model model) {
        this(model, 0,0);
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0,this.getWidth(), this.getHeight());
        gc.fill();
    }

}
