package model;

/* FUNCTION TODO:
*   produitvectoriel(Vecteur)
*   produitScalaire(Vecteur)
*   produitVectoriel(Vecteur)
*   norme() => return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
*   normalisation()
* */

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
     * @param anX
     * @param anY
     * @param anZ
     */
    public Vector(double anX, double anY, double anZ) {
        this.x = anX;
        this.y = anY;
        this.z = anZ;
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

    private double Norme() {
        return (Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z));
    }

    public Vector Normalisation() {
        double norme = Norme();
        this.x = this.x / norme;
        this.y = this.y / norme;
        this.z = this.z / norme;
        return this;
    }
}


