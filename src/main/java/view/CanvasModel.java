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
        GraphicsContext gc = getGraphicsContext2D();
        Matrix m = this.model.getMatrix();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

    	//double ratio = getHeight()/getWidth();
    	Vector v = new Vector(getWidth()/2, getHeight()/2, 0);
        m = m.translation(v);
        //m = m.homothety(0.5);

        System.out.println(m);
    	for (int i = 0; i < m.getColumnCount(); i++) {
			gc.fillOval(m.getValues()[0][i], m.getValues()[1][i], 5, 5);
			//gc.fill();
		}
    	
    	
    }

}
