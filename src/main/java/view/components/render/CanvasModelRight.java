package view.components.render;

import model.models.Face;
import model.models.Model;
import model.models.Vertex;
import model.observers.Observable;

import java.util.Comparator;
import java.util.List;

public class CanvasModelRight extends AbstractCanvas {

    public CanvasModelRight(Model model, double width, double height) {
        super(model, width/2, height/2);


    }

    @Override
    public void update(Observable o) {
        this.draw();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.draw();
    }

    @Override
    public void draw() {
        this.setupDrawStuff();

        this.model.getFaces().sort(Comparator.comparingDouble(Face::getAverageX));

        for(Face face : this.model.getFaces() ) {
            if(super.canvasDrawHandler.isDrawFaces()) {
                drawFace(face);
            }
        }
    }

    @Override
    public void initDraw() {
        this.draw();
    }

    @Override
    public void drawFace(Face face) {
        List<Vertex> points = face.getVertices();

        double[] yArray = points.stream().mapToDouble(Vertex::getY).toArray();
        double[] zArray = points.stream().mapToDouble(z -> z.getZ() + getWidth()/2).toArray();

        this.gc.setFill(getFaceColor(face));

        if(this.canvasDrawHandler.isDrawStrokes()) {
            this.gc.strokePolygon(zArray,yArray, points.size());
        }

        if(this.canvasDrawHandler.isDrawFaces()) {
            this.gc.fillPolygon(zArray,yArray, points.size());
        }
    }
}
