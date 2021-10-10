package model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 *	A model contains faces
 * A face is build with 3 points, or more !
 *
 * @author Paul VANHEE
 * @version %I%, %G%
 * @see Model
 * @see Vertex
 */
public class Face {

	private ArrayList<Vertex> vertexs;
	private Color color;

	/**
	 * <b>Constructor of a Face</b>
	 *
	 * A face is build with 3 points (or more), and a color, LAVENDER by default
	 *
	 * @param vertexs list of vertexs
	 * @param color color of faces
	 */
	public Face(ArrayList<Vertex> vertexs, Color color) {
		this.vertexs = vertexs;
		this.color = color;
	}

	public Face(ArrayList<Vertex> vertexs){
		this(vertexs, Color.LAVENDER);
	}


}
