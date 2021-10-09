package model;

import java.util.ArrayList;


/**
 * Define the Model, the model will be displayed in a canvas
 *
 * @author Néo ALMEIDA - Paul VANHEE
 * @version %I%, %G%
 * @see view.ModelCanvas
 */
public class Model {

    private Matrix matrix;
    private ArrayList<Vertex> points;
    private ArrayList<Face> faces;

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
