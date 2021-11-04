package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

	private ArrayList<Vertex> vertices;
	private int[] color;

	/**
	 * <b>Constructor of a Face</b>
	 *
	 * A face is build with 3 points (or more), and a color, LAVENDER by default
	 *
	 * @param vertices list of vertices
	 * @param color color of faces
	 */
	public Face(ArrayList<Vertex> vertices, int[] color) {
		this.vertices = vertices;
		this.color = color;
	}

	public Face(ArrayList<Vertex> vertices){
		this(vertices, new int[]{230,230,250});
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

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public int[] getColor() {
		return color;
	}




	@Override
	public int compareTo(Face other) {

		double thisMoyZ = this.getVertices().stream().mapToDouble(Vertex::getZ).sum() / this.getVertices().size();
		double otherMoyZ = other.getVertices().stream().mapToDouble(Vertex::getZ).sum() / this.getVertices().size();

		if(thisMoyZ < otherMoyZ)
			return -1;
		else if(thisMoyZ > otherMoyZ)
			return 1;
		else
			return 0;
	}



	@Override
	public String toString() {
		String res = "";

		for (Vertex v : this.getVertices()) {
			res += v.toString() ;
		}
		res+="\n";
		return res;
	}
}
