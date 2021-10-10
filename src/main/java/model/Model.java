package model;

import java.util.ArrayList;


/**
 * Define the Model, the model will be displayed in a canvas
 *
 * @author Néo ALMEIDA - Paul VANHEE
 * @version %I%, %G%
 * @see view.CanvasModel
 */
public class Model {

    private Matrix matrix;
    private final ArrayList<Vertex> points;
    private final ArrayList<Face> faces;
    private String nameOfFile;

    public Model(ArrayList<Vertex> points, ArrayList<Face> faces) {
        this.points = points;
        this.faces = faces;
    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
    
    public Matrix getMatrix() {
		return matrix;
	}
}
