package view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Matrix;
import model.Model;
import model.Vector;
import model.Vertex;

/**
 * The CanvasModel handle a model
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
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
    	//double ratio = getHeight()/getWidth();
    	Vector v = new Vector(getWidth()/2, getHeight()/2, 0);
    	this.model.getMatrix().translation(v);
    	GraphicsContext gc = getGraphicsContext2D();
    	Matrix m = this.model.getMatrix();
    	for (int i = 0; i < m.getColumnCount(); i++) {
			gc.fillOval(m.getValues()[i][0], m.getValues()[i][1], m.getValues()[i][2], 1);
			gc.fill();
		}
    	
    }

}
