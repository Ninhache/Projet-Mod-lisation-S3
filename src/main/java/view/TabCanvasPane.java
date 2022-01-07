package view;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.io.PlyReader;
import model.maths.Rotation;

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
//            	Model model = ((TabCanvas)getTabs().get(numberSelected.intValue())).getCanvas().getModel();
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

        tab.getCanvas().getModel().translate(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().rotate(r, angle); //15
        tab.getCanvas().getModel().translate(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

    }

    public void translateModel(double distanceX, double distanceY, double distanceZ) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().translate(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().translate(distanceX,distanceY,distanceZ);
        tab.getCanvas().getModel().translate(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

    }

    public void scaleModel(double scale){
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().translate(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().homothety(scale);
        tab.getCanvas().getModel().translate(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

    }

}

