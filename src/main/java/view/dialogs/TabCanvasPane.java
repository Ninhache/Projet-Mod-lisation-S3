package view.dialogs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Rotation;
import view.CanvasModel;

public class TabCanvasPane extends TabPane {

    public TabCanvasPane() {
        super();

        setMinWidth(500);
        setMinHeight(500);

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

}

