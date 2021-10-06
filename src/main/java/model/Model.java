package model;

import java.util.ArrayList;

public class Model {

    private Matrix matrix;
    private ArrayList<Vertex> points;
    private ArrayList<Face> faces;

    public Model(ArrayList<Vertex> points, ArrayList<Face> faces) {
        this.points = points;
        this.faces = faces;
    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
}
