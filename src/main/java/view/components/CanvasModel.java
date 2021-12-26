package view.components;

import java.util.Collections;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Face;
import model.Model;
import model.ObservableThings.Observable;
import model.ObservableThings.Observer;
import model.Rotation;
import view.dialogs.MessageBox;
import view.stages.MainStage;

/**
 * The CanvasModel handle a model
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
    
    private boolean drawFaces = true, drawStrokes = true, drawLight;

    public CanvasModel(Model model, double width, double height) {
        super(width, height);
        this.model = model;

        if(model != null) this.model.addObserver(this);

        widthProperty().addListener(e -> {
            this.draw();
        });

        heightProperty().addListener(e -> {
            this.draw();
        });

        //addEventHandler(MouseEvent.ANY, mouseDraggedEvent());

    }
    
    public CanvasModel(Model model, double width, double height, Rotation rota, int degree) {
    	this(model,width,height);
    	initDraw();
    	this.model.rotate(rota, degree);
        // this.model.rotate(rota, degree)
    }

    public EventHandler<MouseEvent> mouseDraggedEvent() {
        EventHandler<MouseEvent> res = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseDragged) {
                if(mouseDragged.isPrimaryButtonDown()) {
                    model.rotate(Rotation.X, mouseDragged.getScreenX());
                }
            }
        };
        return res;
    }

    public CanvasModel(Model model) {
        this(model, 0,0);
    }
    
    public CanvasModel() {
    	this(null);
    }

    public void initDraw() {

        this.model.resetToDefaultValues();

        double ratio = 1;

        double distanceX = this.model.getMaxX() - this.model.getMinX();
        double distanceY = this.model.getMaxY() - this.model.getMinY();
        double distanceZ = this.model.getMaxZ() - this.model.getMinZ();

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

    // use different method to draw
    public void draw() {

        GraphicsContext gc = getGraphicsContext2D();
        setupDrawStuff(gc);

        if(this.drawFaces & !this.drawStrokes & !this.drawLight) {
            drawFaces(gc);
        } else if(!this.drawFaces & this.drawStrokes & !this.drawLight) {
            drawStrokes(gc);
        } else if (this.drawFaces & this.drawStrokes & !this.drawLight){
            drawFacesStrokes(gc);
        } else if (this.drawFaces & this.drawLight & !this.drawStrokes) {
        } else if (this.drawFaces & this.drawLight & this.drawStrokes) {
        } else if (!this.drawFaces & this.drawLight & !this.drawStrokes) {
            MessageBox.showWarning("Impossible d'afficher !", "Il est impossible d'afficher les effet de la lumière sur notre modèle, si les faces ne sont pas dessinées !");
        } else if (!this.drawFaces & this.drawLight & this.drawStrokes) {
            MessageBox.showWarning("Impossible d'afficher !", "Il est impossible d'afficher les effet de la lumière sur notre modèle, si les faces ne sont pas dessinées !");
        }

    }

    public Color getFacesColor(Face face) {
    	int[] rgbArray = face.getColor();
    	
    	if(rgbArray.length == 3)
    		if(face.getAlpha()>=0)
    			return Color.rgb(rgbArray[0], rgbArray[1],rgbArray[2],face.getAlpha());
    		else
    			return Color.rgb(rgbArray[0], rgbArray[1],rgbArray[2]);
    	else
    		return Color.LAVENDER;
    }
    
    public void drawFacesStrokes(GraphicsContext gc) {
        try {
            Collections.sort(this.model.getFaces());
        } catch (NullPointerException e) {
            return;
        }

        double[] tmpX, tmpY, tmpZ;
        int pt1, pt2;


        for(Face face : this.model.getFaces()) 
        {
        	gc.setFill(getFacesColor(face));

            tmpX = new double[face.getVertices().size()];
            tmpY = new double[face.getVertices().size()];
            tmpZ = new double[face.getVertices().size()];
            for(int i = 0 ; i < face.getVertices().size() ; i++) {
                tmpX[i] = this.model.getMatrix().getValues()[0][face.getVertices().get(i).getId()];
                tmpY[i] = this.model.getMatrix().getValues()[1][face.getVertices().get(i).getId()];
                tmpZ[i] = this.model.getMatrix().getValues()[2][face.getVertices().get(i).getId()];
                pt1 = face.getVertices().get(i).getId();
                /*if( i < face.getVertices().size() -1 ) {
                    pt2 = face.getVertices().get(i+1).getId();
                } else {
                    pt2 = face.getVertices().get(0).getId();
                }*/
                //gc.strokeLine(this.model.getMatrix().getValues()[0][pt1], this.model.getMatrix().getValues()[1][pt1], this.model.getMatrix().getValues()[0][pt2], this.model.getMatrix().getValues()[1][pt2]);
            }
            gc.strokePolygon(tmpX,tmpY,tmpX.length);
            gc.fillPolygon(tmpX, tmpY, tmpX.length);
        }
    }

    public void drawFaces(GraphicsContext gc) {
        //Collections.sort(this.model.getFaces());

        double[] tmpX, tmpY, tmpZ;

        for(Face face : this.model.getFaces()) {
        	gc.setFill(getFacesColor(face));

            tmpX = new double[face.getVertices().size()];
            tmpY = new double[face.getVertices().size()];
            tmpZ = new double[face.getVertices().size()];

            for(int i = 0 ; i < face.getVertices().size() ; i++) {
                tmpX[i] = this.model.getMatrix().getValues()[0][face.getVertices().get(i).getId()];
                tmpY[i] = this.model.getMatrix().getValues()[1][face.getVertices().get(i).getId()];
                tmpZ[i] = this.model.getMatrix().getValues()[2][face.getVertices().get(i).getId()];
            }

            gc.fillPolygon(tmpX, tmpY, tmpX.length);

        }

    }

    public void drawStrokes(GraphicsContext gc) {

        int pt1, pt2;

        for(Face face : this.model.getFaces()) {
            for(int i = 0 ; i < face.getVertices().size() ; i ++ ) {
                pt1 = face.getVertices().get(i).getId();
                if( i < face.getVertices().size() -1 ) {
                    pt2 = face.getVertices().get(i+1).getId();
                } else {
                    pt2 = face.getVertices().get(0).getId();
                }
                gc.strokeLine(this.model.getMatrix().getValues()[0][pt1], this.model.getMatrix().getValues()[1][pt1], this.model.getMatrix().getValues()[0][pt2], this.model.getMatrix().getValues()[1][pt2]);
            }
        }

    }

    private void setupDrawStuff(GraphicsContext gc) {
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, getWidth(), getHeight());

        gc.beginPath();
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
    }

    public void initListeners() {
        MainStage tmp = (MainStage) getParent().getScene().getWindow();
        CustomCheckBox cpAfficherFace = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(0));
        CustomCheckBox cpAfficherLigne = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(1));
        CustomCheckBox cpAfficherLumiere = (CustomCheckBox)(tmp.getToolBar().getMenus().get(1).getItems().get(2));

        cpAfficherFace.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.drawFaces));
        cpAfficherFace.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.drawFaces = newValue;
            if(oldValue != newValue) draw();
        });

        cpAfficherLigne.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.drawStrokes));
        cpAfficherLigne.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.drawStrokes = newValue;
            if(oldValue != newValue) draw();
        });

        cpAfficherLumiere.getSelectedProperty().bindBidirectional(new SimpleBooleanProperty(this.drawLight));
        cpAfficherLumiere.getSelectedProperty().addListener((observable, oldValue, newValue) -> {
            this.drawLight = newValue;
            if(oldValue != newValue) draw();
        });
    }

    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }

}
