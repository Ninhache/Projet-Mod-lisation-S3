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
        
        matrix = new Matrix(new double[4][points.size()]);
        Vertex.resetAuto();
        for(Vertex v : points) {
        	int index = v.getId();
        	matrix.getValues()[0][index] = points.get(index).getX();
        	matrix.getValues()[1][index] = points.get(index).getY();
        	matrix.getValues()[2][index] = points.get(index).getZ();
        	matrix.getValues()[3][index] = 1;
        }
    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
    
    public Matrix getMatrix() {
		return matrix;
	}

    //TODO Change these functions when the Matrix list will be sorted
    public double getMinY() {
    	
    	if(points == null)
    		throw new NullPointerException();
    	
    	double res = points.get(0).getY();
    	for (Vertex v : points) {
			if(v.getY()<res)
				res = v.getY();
		}
    	return res;
    }
<<<<<<< HEAD

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public ArrayList<Vertex> getPoints() {
        return points;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
=======
    
    public double getMinX() {
    	
    	if(points == null)
    		throw new NullPointerException();
    	
    	double res = points.get(0).getX();
    	for (Vertex v : points) {
			if(v.getX()<res)
				res = v.getX();
		}
    	return res;
    }
    
    public double getMaxY() {
    	
    	if(points == null)
    		throw new NullPointerException();
    	
    	double res = points.get(0).getY();
    	for (Vertex v : points) {
			if(v.getY()>res)
				res = v.getY();
		}
    	return res;
    }
    
    public double getMaxX() {
    	
    	if(points == null)
    		throw new NullPointerException();
    	
    	double res = points.get(0).getX();
    	for (Vertex v : points) {
			if(v.getX()>res)
				res = v.getX();
		}
    	return res;
    }
    
>>>>>>> 8d84748 (Added max/min X and Y coordinates getters, change them later when lists will be sorted)
}
