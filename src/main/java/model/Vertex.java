package model;

/**
 * A vertex is a point from a model
 *
 * @author Paul VANHEE
 * @version %I%, %G%
 */
public class Vertex {

    private double x,y,z;
    private static int idAuto = 0;
    private final int id;

    /**
     * <b>Constructor of a Vertex</b>
     *
     * @param x part x of the point
     * @param y part y of the point
     * @param z part z of the point
     */
    public Vertex(double x, double y, double z) {
        this.id = idAuto;
        this.x = x;
        this.y = y;
        this.z = z;

        idAuto ++;
    }
    
    public double getX() {
		return x;
	}
    
    public double getY() {
		return y;
	}
    
    public double getZ() {
		return z;
	}
    
    public int getId() {
		return id;
	}

    public static void resetAuto() {
    	Vertex.idAuto = 0;
    }

}
