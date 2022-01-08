package view.components.render;

import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.observers.Observable;
import model.observers.Observer;
import model.maths.Rotation;
import model.maths.Vector;
import model.models.Face;
import model.models.Model;
import model.models.Vertex;
import view.components.items.CustomCheckBox;
import view.utils.CanvasDrawHandler;
import view.stages.MainStage;

/**
 * The CanvasModel handles a model
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class CanvasModel extends Canvas implements Observer {

    @Override
    public void update(Observable o) {
        this.draw();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.draw();
    }

    private Model model;
    private GraphicsContext gc;

    private CanvasDrawHandler canvasDrawHandler;

    private Color facesColor, strokesColor, verticesColor, backgroundColor;

    private final static Vertex CAMERA = new Vertex(0,0,1);

    public CanvasModel(Model model, double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
        this.model = model;

        this.facesColor = null;
        this.strokesColor = Color.BLACK;
        this.verticesColor = Color.RED;
        this.backgroundColor = Color.LIGHTGRAY;

        this.canvasDrawHandler = new CanvasDrawHandler();
        this.canvasDrawHandler.setDefaultValues();

        if(model != null) this.model.addObserver(this);

        widthProperty().addListener(e -> this.draw());

        heightProperty().addListener(e -> this.draw());

        addEventHandler(MouseEvent.ANY, mouseDraggedEvent());

    }


    public EventHandler<MouseEvent> mouseDraggedEvent() {
        return new EventHandler<>() {

            double dX, dY, rotationX, rotationY;
            boolean isDragged;


            @Override
            public void handle(MouseEvent mouseDragged) {

                if (!isDragged && mouseDragged.isDragDetect()) {
                    isDragged = true;
                    dX = mouseDragged.getSceneX();
                    dY = mouseDragged.getSceneY();
                }
                if (isDragged && !mouseDragged.isDragDetect()) {
                    isDragged = false;
                }

                rotationX = (mouseDragged.getSceneX() - dX);
                rotationY = (mouseDragged.getSceneY() - dY);


                // Click gauche
                if (mouseDragged.isPrimaryButtonDown() && !mouseDragged.isSecondaryButtonDown()) {
                    model.translate(-getWidth() / 2, -getHeight() / 2, 0);
                    model.rotate(Rotation.X, rotationY);
                    model.rotate(Rotation.Y, rotationX);
                    model.translate(getWidth() / 2, getHeight() / 2, 0);
                }
                // click droit
                if (!mouseDragged.isPrimaryButtonDown() && mouseDragged.isSecondaryButtonDown()) {
                    model.translate(-getWidth() / 2, -getHeight() / 2, 0);
                    model.rotate(Rotation.Z, rotationX);
                    model.translate(getWidth() / 2, getHeight() / 2, 0);
                }
                // les deux clicks
                else if (mouseDragged.isPrimaryButtonDown() && mouseDragged.isSecondaryButtonDown()) {
                    model.translate(mouseDragged.getSceneX() - dX, mouseDragged.getSceneY() - dY, 0);
                }
                dX = mouseDragged.getSceneX();
                dY = mouseDragged.getSceneY();
            }
        };
    }

    public CanvasModel(Model model) {
        this(model, 0,0);
    }
    
    public CanvasModel() {
    	this(null);
    }

    /**
     * Draws for the first time your model, in that project, we also use it to reset our model
     */
    public void initDraw() {

        this.model.resetToDefaultValues();

        double ratio;

        double distanceX = this.model.getMaxX() - this.model.getMinX();
        double distanceY = this.model.getMaxY() - this.model.getMinY();

        if( distanceX > distanceY ) {
            ratio = (getWidth() / distanceX) ;
        } else {
            ratio = (getHeight() / distanceY) ;
        }

        this.model.homothety(ratio);
        this.model.translate(this.getWidth()/2 - (this.model.getBarycenterX() * ratio), this.getHeight()/2 - (this.model.getBarycenterY() * ratio), 0);

        initListeners();
        draw();
    }

    /**
     * Draws the model, the method is dependent on local variables.
     * The method is not drawing the hidden faces, and then doesn't work if the faces are interior faces
     */
    public void draw() {
        setupDrawStuff();
        for(Face face : this.model.getFaces() ) {
            if(!this.canvasDrawHandler.isDrawFaces() || face.vecteurFace().dot(CAMERA) > 0) {
                drawFace(face);
            }
        }
    }

    public Color getFaceColor(Face face) {
    	if(this.facesColor == null) {
            int[] rgbArray = face.getColor();


            if(rgbArray.length == 3)
                if(face.getAlpha()>=0)
                    return Color.rgb(rgbArray[0], rgbArray[1],rgbArray[2],face.getAlpha());
                else
                    return Color.rgb(rgbArray[0], rgbArray[1],rgbArray[2]);
            else
                return Color.LAVENDER;
        } else {
            return this.facesColor;
        }
    }

    /**
     *
     * @param face is the face to draw
     */
    public void drawFace(Face face) {
        List<Vertex> points = face.getVertices();

        double[] xArray = points.stream().mapToDouble(Vertex::getX).toArray();
        double[] yArray = points.stream().mapToDouble(Vertex::getY).toArray();
        double[] zArray = points.stream().mapToDouble(Vertex::getZ).toArray();

        if(canvasDrawHandler.isDrawLight()) {
            Vector vectorLumos = new Vector(0,0,-1);
            Vector vecteurFace1 = new Vector(xArray[1] - xArray[0],yArray[1] - yArray[0],zArray[1] - zArray[0]);
            Vector vecteurFace2 = new Vector(xArray[xArray.length-1]-xArray[0], yArray[yArray.length-1]-yArray[0],zArray[zArray.length-1]-zArray[0]);
            Vector vectorNorm = vecteurFace1.produitVectoriel(vecteurFace2);

            double coeffLumos = (Math.cos((vectorLumos.normalisation()).produitScalaire(vectorNorm.normalisation())));

            Color colorLumos = null;

            if(this.facesColor == null) {
                colorLumos = Color.rgb(face.getColor()[0], face.getColor()[1], face.getColor()[2]);
            } else {
                colorLumos = this.facesColor;
            }
            gc.setFill(Color.rgb((int)((colorLumos.getRed()*255)*coeffLumos), (int)((colorLumos.getGreen()*255)*coeffLumos), (int)((colorLumos.getBlue()*255)*coeffLumos)));
        } else {
            gc.setFill(getFaceColor(face));
        }

        if(canvasDrawHandler.isDrawVertices()) {
            gc.setFill(this.verticesColor);
            for(Vertex vertex : points) {
                gc.fillOval(vertex.getX(), vertex.getY(), 3, 3);
            }
            gc.setFill(this.facesColor == null ? getFaceColor(face) : this.facesColor);
        }

        if(canvasDrawHandler.isDrawStrokes()) {
            gc.strokePolygon(xArray,yArray, points.size());
        }

        if(canvasDrawHandler.isDrawFaces()) {
            gc.fillPolygon(xArray,yArray, points.size());
        }
    }

    /**
     * Clears the canvas, setups the right color to draw strokes, setups the right color to fill canvas.. It's just a function tool
      */
    private void setupDrawStuff() {
        gc.setFill(this.backgroundColor);
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.beginPath();
        gc.setLineWidth(1);
        gc.setStroke(this.strokesColor);
    }

    /**
     * Initializes listeners
     */
    public void initListeners() {
        MainStage tmp = (MainStage) getParent().getScene().getWindow();
        CustomCheckBox cpAfficherSommet = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(0));
        CustomCheckBox cpAfficherLigne = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(1));
        CustomCheckBox cpAfficherFace = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(2));
        CustomCheckBox cpAfficherLumiere = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(3));

        cpAfficherSommet.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(canvasDrawHandler.isDrawVertices()));
        cpAfficherSommet.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            canvasDrawHandler.setDrawVertices(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherFace.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(canvasDrawHandler.isDrawFaces()));
        cpAfficherFace.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            canvasDrawHandler.setDrawFaces(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherLigne.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(canvasDrawHandler.isDrawStrokes()));
        cpAfficherLigne.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            canvasDrawHandler.setDrawStrokes(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherLumiere.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(canvasDrawHandler.isDrawLight()));
        cpAfficherLumiere.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            canvasDrawHandler.setDrawLight(newValue);
            if(oldValue != newValue) draw();
        });
    }

    public Model getModel() { return model; }
    public void setModel(Model model) { this.model=model; }
    public void setFacesColor(Color facesColor) { this.facesColor = facesColor; }
    public void setStrokesColor(Color strokesColor) { this.strokesColor = strokesColor; }
    public void setVerticesColor(Color verticesColor) { this.verticesColor = verticesColor; }
    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }
    public Color getBackgroundColor() { return backgroundColor; }
    public Color getFacesColor() { return facesColor == null ? getFaceColor(model.getFaces().get(0)) : this.facesColor; }
    public Color getStrokesColor() { return strokesColor; }
    public Color getVerticesColor() { return verticesColor; }
}
