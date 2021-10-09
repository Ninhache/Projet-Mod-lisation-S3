package model;

import javafx.geometry.Point3D;

/**
 * A vertex is a point from a model
 *
 * @author Paul VANHEE
 * @version %I%, %G%
 */
public class Vertex extends Point3D {

    private static int idAuto = 0;
    private final int id;

    public Vertex(double x, double y, double z) {
        super(x, y, z);
        this.id = idAuto;

        idAuto ++;
    }
}
