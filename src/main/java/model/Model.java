package model;

import java.util.ArrayList;


/**
 * Define the Model, the model will be displayed in a canvas
 *
 * @author Néo ALMEIDA - Paul VANHEE - Simon LAGNEAU
 * @version %I%, %G%
 * @see view.CanvasModel
 */
public class Model {

    private Matrix matrix;
    private final ArrayList<Vertex> vertices;
    private final ArrayList<Face> faces;
    private String nameOfFile;

    /**
     * <b>Constructor of a Model</b>
     *
     * If you put together a list of points, and with these points you create faces, you get a model
     *
     * @param vertices list of points of the model
     * @param faces list of faces of the model
     */
    
    public Model(ArrayList<Vertex> vertices, ArrayList<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
        
        matrix = new Matrix(new double[4][vertices.size()]);
        Vertex.resetAuto();
        for(Vertex v : vertices) {
        	int index = v.getId();
        	matrix.getValues()[0][index] = vertices.get(index).getX();
        	matrix.getValues()[1][index] = vertices.get(index).getY();
        	matrix.getValues()[2][index] = vertices.get(index).getZ();
        	matrix.getValues()[3][index] = 1;
        }
    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
    
    /**
     * 
     * @return the smallest Y coordinate value in the model
     */
    public double getMinY() {

       if(vertices == null)
               throw new NullPointerException();

       double res = vertices.get(0).getX();
       for (Vertex v : vertices) {
                       if(v.getX()<res)
                               res = v.getX();
               }
       return res;
    }

    /**
     * 
     * @return the biggest Y coordinate value in the model
     */
   public double getMaxY() {

       if(vertices == null)
             throw new NullPointerException();

       double res = vertices.get(0).getY();
       for (Vertex v : vertices) {
                       if(v.getY()>res)
                               res = v.getY();
               }
       return res;
    }

   /**
    * 
    * @return the smallest X coordinate value in the model
    */
   public double getMinX() {

       if(vertices == null)
               throw new NullPointerException();

       double res = vertices.get(0).getX();
       for (Vertex v : vertices) {
                       if(v.getX()<res)
                               res = v.getX();
               }
       return res;
    }
	
   /**
    * 
    * @return the biggest X coordinate value in the model
    */
   public double getMaxX() {

       if(vertices == null)
               throw new NullPointerException();

       double res = vertices.get(0).getX();
       for (Vertex v : vertices) {
                       if(v.getX()>res)
                               res = v.getX();
               }
       return res;
    }

    public Matrix getMatrix() {
		return matrix;
	}

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public ArrayList<Vertex> getPoints() {
        return vertices;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
}
