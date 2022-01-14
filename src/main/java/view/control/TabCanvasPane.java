package view.control;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.io.PlyReader;
import model.maths.Rotation;
import view.components.render.TabCanvas;

import java.io.File;

/**
 * Permit to do some action on the model
 *
 * @author Paul VANHEE - Neo ALMEIDA
 * @version %I%, %G%
 */
public class TabCanvasPane extends TabPane {

    public TabCanvasPane() {
        super();

        setMinWidth(500);
        setMinHeight(500);

        getSelectionModel().selectedIndexProperty().addListener((observableValue, numberOld, numberSelected) -> {

            try {
            	((RightMenu) ((BorderPane) getParent().getScene().getRoot()).getRight()).getModelAccordion().getSlidersModel().updateInformations(numberSelected);
            } catch (Exception e) {}
        });

    }

    public void addModel(File file) throws Exception {

        PlyReader read = new PlyReader();
        read.setFile(file);
        TabCanvas tab = new TabCanvas(read.readPly(), getWidth(), getHeight());

        getTabs().add(tab);
        tab.initDraw();
        getSelectionModel().select(tab);

        ((SuperToolBar)((BorderPane) getParent().getScene().getRoot()).getTop()).addToOpenRecent(file);
    }



    public void onCloseRequest(ActionEvent e) {
        TabCanvasPane cp = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();
        cp.getTabs().remove(cp.getSelectionModel().getSelectedItem());
    }

    public void rotateModel(Rotation r,double angle) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvasTop().getModel().translate(-tab.getCanvasTop().getWidth()/2, -tab.getCanvasTop().getHeight()/2, 0);
        tab.getCanvasTop().getModel().rotate(r, angle); //15
        tab.getCanvasTop().getModel().translate(tab.getCanvasTop().getWidth()/2, tab.getCanvasTop().getHeight()/2, 0);

    }

    public void translateModel(double distanceX, double distanceY, double distanceZ) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvasTop().getModel().translate(-tab.getCanvasTop().getWidth()/2, -tab.getCanvasTop().getHeight()/2, 0);
        tab.getCanvasTop().getModel().translate(distanceX,distanceY,distanceZ);
        tab.getCanvasTop().getModel().translate(tab.getCanvasTop().getWidth()/2, tab.getCanvasTop().getHeight()/2, 0);

    }

    public void scaleModel(double scale){
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvasTop().getModel().translate(-tab.getCanvasTop().getWidth()/2, -tab.getCanvasTop().getHeight()/2, 0);
        tab.getCanvasTop().getModel().homothety(scale);
        tab.getCanvasTop().getModel().translate(tab.getCanvasTop().getWidth()/2, tab.getCanvasTop().getHeight()/2, 0);

    }

}

