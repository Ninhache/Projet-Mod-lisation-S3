package model;

import model.ObservableThings.Observable;
import view.components.CanvasModel;

import java.util.ArrayList;


/**
 * Define the Model, the model will be displayed in a canvas
 *
 * @author Néo ALMEIDA - Paul VANHEE - Simon LAGNEAU
 * @version %I%, %G%
 * @see CanvasModel
 */
public class Model extends Observable {

    private Matrix matrix;
    private final ArrayList<Vertex> vertices;
    private final ArrayList<Face> faces;
    private String nameOfFile, author, description;

    private double barycenterX;
    private double barycenterY;
    private double barycenterZ;

    /**
     * <b>Constructor of a Model</b>
     *
     * If you put together a list of points, and with these points you create faces, you get a model
     *
     * @param vertices list of points of the model
     * @param faces list of faces of the model
     * @param rotation direction's rotation
     * @param degree of the rotation
     */
    
    public Model(ArrayList<Vertex> vertices, ArrayList<Face> faces, Rotation rotation, int degree) {
    	this.vertices = vertices;
        this.faces = faces;
        
        matrix = new Matrix(new double[4][vertices.size()], rotation, degree);
        Vertex.resetAuto();
        for(Vertex v : vertices) {
        	int index = v.getId();
        	matrix.getValues()[0][index] = vertices.get(index).getX();
        	matrix.getValues()[1][index] = vertices.get(index).getY();
        	matrix.getValues()[2][index] = vertices.get(index).getZ();
        	matrix.getValues()[3][index] = 1;
        }
    }
    
    public Model(ArrayList<Vertex> vertices, ArrayList<Face> faces) {
    	this(vertices,faces,Rotation.X,0);
    }
    
    public Model(){
        this(new ArrayList<>(), new ArrayList<>());
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

    /**
     *
     * @return the smallest Z coordinate value in the model
     */
    public double getMinZ() {

        if(vertices == null)
            throw new NullPointerException();

        double res = vertices.get(0).getZ();
        for (Vertex v : vertices) {
            if(v.getZ()<res)
                res = v.getZ();
        }
        return res;
    }

    /**
     *
     * @return the biggest Z coordinate value in the model
     */
    public double getMaxZ() {

        if(vertices == null)
            throw new NullPointerException();

        double res = vertices.get(0).getZ();
        for (Vertex v : vertices) {
            if(v.getZ()>res)
                res = v.getZ();
        }
        return res;
    }

    public void rotate(Rotation r, double degree) {
        this.getMatrix().rotation(r, degree);
        System.out.println("MODEL: Rotate");
        updateFaces();
        this.notifyObservers();
    }

    public void xRotate(double degree) {
        this.getMatrix().xRotationMatrix(degree);
        System.out.println("MODEL: xRotate");
        updateFaces();
        this.notifyObservers();
    }

    public void yRotate(double degree) {
        this.getMatrix().yRotationMatrix(degree);
        System.out.println("MODEL: yRotate");
        updateFaces();
        this.notifyObservers();
    }

    public void zRotate(double degree) {
        this.getMatrix().zRotationMatrix(degree);
        System.out.println("MODEL: zRotate");
        updateFaces();
        this.notifyObservers();
    }

    public void translate(Vector v) {
        this.getMatrix().translation(v);
        System.out.println("MODEL: Homothety Vector");
        this.notifyObservers();
    }

    public void translate(double x, double y, double z) {
        this.getMatrix().translation(x,y,z);
        System.out.println("MODEL: Translate XYZ");
        this.notifyObservers();
    }

    public void homothety(double ratio) {
        this.getMatrix().homothety(ratio);
        System.out.println("MODEL: Homothety");
        this.notifyObservers();
    }

    public void resetToDefaultValues() {
        this.getMatrix().resetToDefaultValues();
        System.out.println("MODEL: resetDefault");
        this.notifyObservers();
    }

    public void updateFaces(){
        for (Face f:
                this.getFaces()) {
            for (Vertex v:
                    f.getVertices()) {
                v.setX(this.getMatrix().getValues()[0][v.getId()]);
                v.setY(this.getMatrix().getValues()[1][v.getId()]);
                v.setZ(this.getMatrix().getValues()[2][v.getId()]);
            }
        }
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

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public void setBarycenterX(double barycenterX) {
        this.barycenterX = barycenterX;
    }

    public void setBarycenterY(double barycenterY) {
        this.barycenterY = barycenterY;
    }

    public void setBarycenterZ(double barycenterZ) {
        this.barycenterZ = barycenterZ;
    }

    public double getBarycenterX() {
        return barycenterX;
    }

    public double getBarycenterY() {
        return barycenterY;
    }

    public double getBarycenterZ() {
        return barycenterZ;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }
    
    public Model clone() {
    	return new Model((ArrayList<Vertex>) this.vertices.clone(), (ArrayList<Face>) this.faces.clone());
    }
}
