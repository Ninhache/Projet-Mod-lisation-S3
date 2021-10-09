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

    public Vector(double anX, double anY, double aZ) {
        this.x = anX;
        this.y = anY;
        this.z = aZ;
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
}
