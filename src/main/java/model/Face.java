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
public class Face {

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
		this(vertices, new int[]{125,125,125});
	}


}
