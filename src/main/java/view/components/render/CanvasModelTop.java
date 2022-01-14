package view.components.render;

import java.util.Comparator;
import java.util.List;
import javafx.scene.paint.Color;
import model.observers.Observable;
import model.observers.Observer;
import model.maths.Vector;
import model.models.Face;
import model.models.Model;
import model.models.Vertex;

/**
 * The CanvasModelTop handles a model
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class CanvasModelTop extends AbstractCanvas implements Observer {

    public CanvasModelTop(Model model, double width, double height) {
        super(model, width, height/2);

    }

    @Override
    public void update(Observable o) {
        this.draw();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.draw();
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
        this.setupDrawStuff();

        this.model.getFaces().sort(Comparator.comparingDouble(Face::getAverageZ));

        for(Face face : this.model.getFaces() ) {
            if(!super.canvasDrawHandler.isDrawFaces() || face.vecteurFace().dot(AbstractCanvas.CAMERA) > 0) {
                drawFace(face);
            }
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

        if(this.canvasDrawHandler.isDrawLight()) {
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
            this.gc.setFill(Color.rgb((int)((colorLumos.getRed()*255)*coeffLumos), (int)((colorLumos.getGreen()*255)*coeffLumos), (int)((colorLumos.getBlue()*255)*coeffLumos)));
        } else {
            this.gc.setFill(getFaceColor(face));
        }

        if(this.canvasDrawHandler.isDrawVertices()) {

            this.gc.setFill(this.verticesColor);
            for(Vertex vertex : points) {
                this.gc.fillOval(vertex.getX(), vertex.getY(), 3, 3);
            }
            this.gc.setFill(this.facesColor == null ? getFaceColor(face) : this.verticesColor);
        }

        if(this.canvasDrawHandler.isDrawStrokes()) {
            this.gc.strokePolygon(xArray,yArray, points.size());
        }

        if(this.canvasDrawHandler.isDrawFaces()) {
            this.gc.fillPolygon(xArray,yArray, points.size());
        }
    }




}
