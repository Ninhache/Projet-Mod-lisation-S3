package view;

/**
 * Handle Canvas Rendering Options
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class CanvasDrawHandler {

	private boolean drawVertices = true;
	private boolean drawFaces = true;
	private boolean drawStrokes = true;
	private boolean drawLight = false;

	public CanvasDrawHandler() {
	}

	public void setDefaultValues() {
		this.drawVertices = false;
		this.drawFaces = true;
		this.drawStrokes = true;
		this.drawLight = false;
	}

	public boolean isDrawVertices() {
		return drawVertices;
	}

	public void setDrawVertices(boolean drawVertices) {
		this.drawVertices = drawVertices;
	}

	public boolean isDrawFaces() {
		return drawFaces;
	}

	public void setDrawFaces(boolean drawFaces) {
		this.drawFaces = drawFaces;
	}

	public boolean isDrawStrokes() {
		return drawStrokes;
	}

	public void setDrawStrokes(boolean drawStrokes) {
		this.drawStrokes = drawStrokes;
	}

	public boolean isDrawLight() {
		return drawLight;
	}

	public void setDrawLight(boolean drawLight) {
		this.drawLight = drawLight;
	}
}
