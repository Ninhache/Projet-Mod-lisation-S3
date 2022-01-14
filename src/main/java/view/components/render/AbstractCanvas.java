package view.components.render;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import model.maths.Rotation;
import model.models.Face;
import model.models.Model;
import model.models.Vertex;
import model.observers.Observer;
import view.components.items.CustomCheckBox;
import view.stages.MainStage;
import view.utils.CanvasDrawHandler;

public abstract class AbstractCanvas extends Canvas implements Observer {

    public Model model;
    public GraphicsContext gc;

    public CanvasDrawHandler canvasDrawHandler;

    public Color facesColor, strokesColor, verticesColor, backgroundColor;

    public final static Vertex CAMERA = new Vertex(0,0,1);

    public AbstractCanvas(Model model, double width, double height) {
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
        addEventHandler(ScrollEvent.ANY, scrollEvent());
    }

    public AbstractCanvas(Model model) {
        this(model, 0,0);
    }

    public AbstractCanvas() {
        this(null);
    }

    /**
     * Clears the canvas, setups the right color to draw strokes, setups the right color to fill canvas.. It's just a function tool
     */
    public void setupDrawStuff() {
        this.gc.setFill(this.backgroundColor);
        this.gc.fillRect(0, 0, getWidth(), getHeight());
        this.gc.beginPath();
        this.gc.setLineWidth(1);
        this.gc.setStroke(this.strokesColor);
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

        cpAfficherSommet.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.canvasDrawHandler.isDrawVertices()));
        cpAfficherSommet.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.canvasDrawHandler.setDrawVertices(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherFace.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.canvasDrawHandler.isDrawFaces()));
        cpAfficherFace.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.canvasDrawHandler.setDrawFaces(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherLigne.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.canvasDrawHandler.isDrawStrokes()));
        cpAfficherLigne.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.canvasDrawHandler.setDrawStrokes(newValue);
            if(oldValue != newValue) draw();
        });

        cpAfficherLumiere.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.canvasDrawHandler.isDrawLight()));
        cpAfficherLumiere.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.canvasDrawHandler.setDrawLight(newValue);
            if(oldValue != newValue) draw();
        });
    }

    public abstract void drawFace(Face face);

    public abstract void initDraw();

    public abstract void draw();

    private EventHandler<ScrollEvent> scrollEvent() {
        return event -> {
            if (event.getDeltaY() >= 0) {
                model.translate(-getWidth() / 2, -getHeight() / 2, 0);
                model.homothety(1.10);
                model.translate(getWidth() / 2, getHeight() / 2, 0);
            } else {
                model.translate(-getWidth() / 2, -getHeight() / 2, 0);
                model.homothety(0.90);
                model.translate(getWidth() / 2, getHeight() / 2, 0);
            }
        };
    }

    private EventHandler<MouseEvent> mouseDraggedEvent() {
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

    public Model getModel() {
        return model;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFacesColor(Color facesColor) {
        this.facesColor = facesColor;
    }

    public void setStrokesColor(Color strokesColor) {
        this.strokesColor = strokesColor;
    }

    public void setVerticesColor(Color verticesColor) {
        this.verticesColor = verticesColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getFacesColor() {
        return facesColor;
    }

    public Color getStrokesColor() {
        return strokesColor;
    }

    public Color getVerticesColor() {
        return verticesColor;
    }

}
