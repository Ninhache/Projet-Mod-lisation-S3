package model;

import java.util.ArrayList;

/**
 * A model contains faces
 * A face is build with 3 points, or more !
 *
 * @author Paul VANHEE
 * @version %I%, %G%
 * @see Model
 * @see Vertex
 */
public class Face implements Comparable<Face> {

	private final ArrayList<Vertex> vertices;
	private int[] color;
	private double alpha = -1.0;

	/**
	 * <b>Constructor of a Face</b>
	 *
	 * A face is build with 3 points (or more), and a color, LAVENDER by default
	 *
	 * @param vertices list of vertices
	 */
	public Face(ArrayList<Vertex> vertices){
		this.vertices = vertices;
		this.color = new int[]{230,230,250};
	}
	
	public Face(ArrayList<Vertex> vertices, int[] color) {
		this(vertices);
		this.color = color;
	}

	public Face(ArrayList<Vertex> vertices, int[] color, double alpha) {
		this(vertices,color);
		this.alpha = alpha;
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public int compareTo(Face other) {

		double thisMoy = this.getAverage();
		double otherMoy = other.getAverage();

		if(thisMoy>otherMoy) return 1;
		else if(thisMoy<otherMoy) return -1;

		return 0;
	}

	public int[] getColor() {
		return color;
	}

	public double getAverage(){
		double res = 0;
		for (Vertex v:
			 getVertices()) {
			res+= v.getZ();
		}
		return res/this.getVertices().size();
	}

	/**
	 * Normal vector of a face
	 * @return a vector
	 */
	public Vector vecteurFace() {
		Vertex vOrigin = this.vertices.get(0);
		Vertex pointA =  this.vertices.get(1);
		Vertex pointB = this.vertices.get(2);

		Vector pointOA = new Vector(pointA).substract(vOrigin);
		Vector pointOB = new Vector(pointB).substract(vOrigin);

		return pointOA.produitVectoriel(pointOB.normalisation()).normalisation();
	}
	
	public int getColorR() {
		return color[0];
	}
	
	public int getColorG() {
		return color[1];
	}
	
	public int getColorB() {
		return color[2];
	}

	public double getAlpha() {
		return alpha;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		for (Vertex v : this.getVertices()) {
			res.append(v.toString());
		}
		res.append("\n");
		return res.toString();
	}
}