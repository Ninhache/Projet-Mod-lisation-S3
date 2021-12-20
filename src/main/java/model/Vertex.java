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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static void resetAuto() {
    	Vertex.idAuto = 0;
    }

    @Override
    public String toString() {
        return String.format("ID:%s[X:%s; Y:%s;Z:%s]",this.id,this.x, this.y, this.z);
    }
}
