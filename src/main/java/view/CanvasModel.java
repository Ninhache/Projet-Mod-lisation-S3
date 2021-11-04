package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import model.*;
import view.dialogs.CustomCheckBox;
import view.stages.MainStage;

import java.util.Collections;

/**
 * The CanvasModel handle a model
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class CanvasModel extends Canvas {

    private final Model model;

    private boolean drawFaces = true, drawStrokes = true, drawLight;

    public CanvasModel(Model model, double width, double height) {
        super(width, height);
        this.model = model;

        widthProperty().addListener(e -> {
            this.draw();
        });

        heightProperty().addListener(e -> {
            this.draw();
        });

    }

    public CanvasModel(Model model) {
        this(model, 0,0);
    }

    public void initDraw() {

        this.model.getMatrix().resetToDefaultValues();

        double ratio = 1;

        double distanceX = this.model.getMaxX() - this.model.getMinX();
        double distanceY = this.model.getMaxY() - this.model.getMinY();
        double distanceZ = this.model.getMaxZ() - this.model.getMinZ();

        if( distanceX > distanceY ) {
            ratio = (getWidth() / distanceX) ;
        } else {
            ratio = (getHeight() / distanceY) ;
        }
/*
        System.out.println("distanceX :" + distanceX);
        System.out.println("distanceY :" + distanceY);
        System.out.println("distanceZ :" + distanceZ);
        System.out.println(ratio);
 */

        //this.model.getMatrix().translation(centerX/2, centerY/2, centerZ/2);
        this.model.getMatrix().homothety(ratio);
        this.model.getMatrix().translation(this.getWidth()/2 - (this.model.getBarycenterX() * ratio), this.getHeight()/2 - (this.model.getBarycenterY() * ratio), 0);

        initListeners();
        draw();
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();

        setupDrawStuff(gc);

        if(this.drawFaces && !drawLight) drawFaces(gc);
        if(this.drawStrokes) drawStrokes(gc);
       // if(this.drawLight && this.drawFaces) drawLight();


    }

    public void drawFaces(GraphicsContext gc) {
        Collections.sort(this.model.getFaces());

        double[] tmpX, tmpY, tmpZ;

        gc.setFill(Color.RED);

        for(Face face : this.model.getFaces()) {

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

/*
    	double ratio = (canvasWidth*canvasWidth)/(canvasWidth/canvasHeight);
    	Vector v = new Vector(canvasWidth/2, canvasHeight/2, 0);
    	m = m.homothety(50);
        m = m.translation(v);
        //m = m.translation(new Vector(-(canvasWidth/2), 1, 0));
        //m = m.rotation(Rotation.Z, 45);
        System.out.println(ratio);

        //System.out.println(m);
    	for (int i = 0; i < m.getColumnCount(); i++) {
			gc.fillOval(m.getValues()[0][i], m.getValues()[1][i], 5, 5);
			//gc.fill();
		}
*/


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
}
