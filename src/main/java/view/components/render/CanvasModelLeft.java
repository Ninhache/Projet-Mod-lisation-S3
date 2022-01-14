package view.components.render;

import model.models.Face;
import model.models.Model;
import model.models.Vertex;
import model.observers.Observable;

import java.util.Comparator;
import java.util.List;

public class CanvasModelLeft extends AbstractCanvas {

    public CanvasModelLeft(Model model, double width, double height) {
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

    public void draw() {
        this.setupDrawStuff();

        this.model.getFaces().sort(Comparator.comparingDouble(Face::getAverageY));

        for(Face face : this.model.getFaces() ) {
            if(super.canvasDrawHandler.isDrawFaces()) {
                drawFace(face);
            }
        }
    }

    public void initDraw() {
        initListeners();
        draw();
    }


    public void drawFace(Face face) {
        List<Vertex> points = face.getVertices();

        double[] xArray = points.stream().mapToDouble(x -> x.getX() - getWidth()/2).toArray();
        double[] zArray = points.stream().mapToDouble(z -> z.getZ() + getHeight()/2).toArray();

        this.gc.setFill(getFaceColor(face));

        if(this.canvasDrawHandler.isDrawStrokes()) {
            this.gc.strokePolygon(xArray, zArray, points.size());
        }

        if(this.canvasDrawHandler.isDrawFaces()) {
            this.gc.fillPolygon(xArray, zArray, points.size());
        }
    }

}