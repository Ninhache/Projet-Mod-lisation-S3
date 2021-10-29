package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.Matrix;
import model.Model;
import model.Vector;

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
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

    	double ratio = (canvasWidth*canvasWidth)/(canvasWidth/canvasHeight);
    	Vector v = new Vector(canvasWidth/2, canvasHeight/2, 0);
    	m = m.homothety(50);
        m = m.translation(v);
        //m = m.translation(new Vector(-(canvasWidth/2), 1, 0));
        //m = m.rotation(Rotation.Z, 45);
        System.out.println(ratio);

        //System.out.println(m);
    	for (int i = 0; i < m.getColumnCount(); i++) {
			gc.fillOval(m.getValues()[0][i], m.getValues()[1][i], 5, 5);
			//gc.fill();
		}
    }
}
