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

    public void onSwitchToRight(ActionEvent e) {

        TabCanvas tabToSwitch = (TabCanvas) getSelectionModel().getSelectedItem();

        int currentIndex = getTabs().indexOf(tabToSwitch);
        int nextIndex = currentIndex+1;

        if(((nextIndex < getTabs().size()))) {

            String textPremier = getTabs().get(currentIndex).getText();
            String textDeuxieme = getTabs().get(nextIndex).getText();



            CanvasModel premier = (CanvasModel) getTabs().get(currentIndex).getContent();
            CanvasModel deuxieme = (CanvasModel) getTabs().get(nextIndex).getContent();

            getTabs().get(currentIndex).setContent(deuxieme);
            getTabs().get(nextIndex).setContent(premier);

            getTabs().get(currentIndex).setText(textDeuxieme);
            getTabs().get(nextIndex).setText(textPremier);

            getSelectionModel().select(nextIndex);
        }

    }

    public void onSwitchToLeft(ActionEvent e) {

        TabCanvas tabToSwitch = (TabCanvas) getSelectionModel().getSelectedItem();

        int currentIndex = getTabs().indexOf(tabToSwitch);
        int previousIndex = currentIndex - 1;

        if(((previousIndex >= 0))) {

            String textPremier = getTabs().get(currentIndex).getText();
            String textDeuxieme = getTabs().get(previousIndex).getText();

            Canvas premier = (Canvas) getTabs().get(currentIndex).getContent();
            Canvas deuxieme = (Canvas) getTabs().get(previousIndex).getContent();

            getTabs().get(currentIndex).setContent(deuxieme);
            getTabs().get(previousIndex).setContent(premier);

            getTabs().get(currentIndex).setText(textDeuxieme);
            getTabs().get(previousIndex).setText(textPremier);

            getSelectionModel().select(previousIndex);
        }
    }

    public void rotateModel(Event e, double angle) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().rotation(Rotation.X, angle); //15
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

    public void rotateInverseModel(ActionEvent e) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().rotation(Rotation.X, -15);
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

    public void translateModel(Event e, double distanceX, double distanceY) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().translation(distanceX,distanceY,0);
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

    public void translateInverseModel(ActionEvent e) {
        TabCanvas tab = (TabCanvas)(getSelectionModel().getSelectedItem());

        tab.getCanvas().getModel().getMatrix().translation(-tab.getCanvas().getWidth()/2, -tab.getCanvas().getHeight()/2, 0);
        tab.getCanvas().getModel().getMatrix().translation(-10,0,0);
        tab.getCanvas().getModel().getMatrix().translation(tab.getCanvas().getWidth()/2, tab.getCanvas().getHeight()/2, 0);

        tab.getCanvas().draw();
    }

}

