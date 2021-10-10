package model;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;


/**
 * Define the Model, the model will be displayed in a canvas
 *
 * @author Néo ALMEIDA - Paul VANHEE
 * @version %I%, %G%
 * @see view.CanvasModel
 */
public class Model {

    private Matrix matrix;
    private final ArrayList<Vertex> points;
    private final ArrayList<Face> faces;
    private String nameOfFile;

    private SimpleBooleanProperty needToDraw;

    /**
     * <b>Constructor of a Model</b>
     *
     * If you put together a list of points, and in these points you create faces, you get a model
     *
     * @param points list of points of the model
     * @param faces list of faces of the model
     */
    public Model(ArrayList<Vertex> points, ArrayList<Face> faces) {
        this.points = points;
        this.faces = faces;

        //needToDraw.bind();

        this.needToDraw = new SimpleBooleanProperty();
        this.needToDraw.set(true);
    }

    public Model(){
        this(new ArrayList<Vertex>(), new ArrayList<Face>());
    }
    
    public Matrix getMatrix() {
		return matrix;
	}

    /**
     * The draw property notify the canvas for update the drawing everytime
     * <p>
     * Longer description. If there were any, it would be
     * here.
     * <p>
     * And even more explanations to follow in consecutive
     * paragraphs separated by HTML paragraph breaks.
     *
     * @param variable Description text text text.
     * @return Description text text text.
     */
    public SimpleBooleanProperty getDrawProperty() {
        return this.needToDraw;
    }
}
