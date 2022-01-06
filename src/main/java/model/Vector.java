package model;

/**
 * A vector is a movement, matrix handle them to translate model
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class Vector {

    private double x;
    private double y;
    private double z;

    /**
     * <b>Constructor of a Vector</b>
     *
     * A vector is like a point but it's not a point ...
     *
     * @param anX X coords of the vector
     * @param anY Y coords of the vector
     * @param anZ Z coords of the vector
     */
    public Vector(double anX, double anY, double anZ) {
        this.x = anX;
        this.y = anY;
        this.z = anZ;
    }

    public Vector(Vertex vertex) {
        this(vertex.getX(), vertex.getY(), vertex.getZ());
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

    public Vector produitVectoriel(Vector v2) {
        double newX = this.y * v2.getZ() - this.z * v2.getY();
        double newY = this.z * v2.getX() - this.x * v2.getZ();
        double newZ = this.x * v2.getY() - this.y * v2.getX();
        return new Vector(newX, newY, newZ);
    }

    public double produitScalaire(Vector v2) {
        return this.x * v2.x + this.y * v2.y + this.z * v2.z;
    }

    private double norme() {
        return (Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z));
    }

    public Vector normalisation() {
        double norme = this.norme();
        this.x = this.x / norme;
        this.y = this.y / norme;
        this.z = this.z / norme;
        return this;
    }

    public Vector substract(Vector other) {
        return new Vector(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
    }

    public Vector substract(Vertex other) {
        return this.substract(new Vector(other));
    }

    public double dot(Vector other) {
        return this.getX() * other.getX() + this.getY() * other.getY() + this.getZ() * other.getZ();
    }

    public double dot(Vertex other) {
        return this.dot(new Vector(other));
    }
}


