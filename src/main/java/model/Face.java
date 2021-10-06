package model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Face {


	private ArrayList<Vertex> vertexs;
	private Color color;


	public Face(ArrayList<Vertex> vertexs, Color color) {
		this.vertexs = vertexs;
		this.color = color;
	}

	public Face(ArrayList<Vertex> vertexs){
		this(vertexs, Color.LAVENDER);
	}


}
