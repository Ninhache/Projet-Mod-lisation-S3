package model;

import javafx.beans.property.SimpleBooleanProperty;

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

    /**
     * <b>Constructor of a Model</b>
     *
     * If you put together a list of points, and in these points you create faces, you get a model
     *
     * @param points list of points of the model
     * @param faces list of faces of the model
     */
    public Model(ArrayList<Vertex> points, ArrayList<Face> faces) {
        this.points = points;
        this.faces = faces;
        
        matrix = new Matrix(new double[points.size()][4]);

        for(Vertex v : points) {
        	int index = v.getId();
        	matrix.getValues()[index][0] = points.get(index).getX();
        	matrix.getValues()[index][1] = points.get(index).getY();
        	matrix.getValues()[index][2] = points.get(index).getZ();
        	matrix.getValues()[index][3] = 1;
        }

    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
    
    public Matrix getMatrix() {
		return matrix;
	}

}
