package view.nodes;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Model;
import model.PlyReader;
import model.Rotation;
import view.components.CanvasModel;
import view.components.tabpane.TabCanvas;

import java.io.File;

public class TabCanvasPane extends TabPane {
	
    public TabCanvasPane() {
        super();

        setMinWidth(500);
        setMinHeight(500);

        getSelectionModel().selectedIndexProperty().addListener((observableValue, numberOld, numberSelected) -> {

            try {
            	((RightMenu) ((BorderPane) getParent().getScene().getRoot()).getRight()).getModelAccordion().getSlidersModel().updateInformations(numberSelected);
//            	Model model=((TabCanvas) getSelectionModel().)).getCanvas().getModel();
            	Model model = ((TabCanvas)getTabs().get(numberSelected.intValue())).getCanvas().getModel();
            	((RightMenu) ((BorderPane) getParent().getScene().getRoot()).getRight()).getCanvasBox().setModel(model);
            } catch (Exception e) {
            	//e.printStackTrace();
            }
        });

    }

    public void addModel(File file) {

        PlyReader read = new PlyReader();
        TabCanvas tab = new TabCanvas();
        try {
            read.setFile(file);
            tab = new TabCanvas(read.readPly(), getWidth(), getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getTabs().add(tab);

        tab.getCanvas().setId("mainCanvas");
        tab.updateDraw();
        getSelectionModel().select(tab);

        // add manage recent
        ((SuperToolBar)((BorderPane) getParent().getScene().getRoot()).getTop()).addToOpenRecent(file);
    }



    public void onCloseRequest(ActionEvent e) {
        TabCanvasPane cp = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();
        cp.getTabs().remove(cp.getSelectionModel().getSelectedItem());
    }

    public void rotateModel(Rotation r,double angle) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().rotation(r, angle); //15
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

    public void translateModel(double distanceX, double distanceY, double distanceZ) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().translation(distanceX,distanceY,distanceZ);
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

    public void scaleModel(double scale){
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().homothety(scale);
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

}

