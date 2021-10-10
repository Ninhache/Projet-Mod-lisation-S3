package view.dialogs;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import view.CustomTabPaneSkin;

public class TabCanvasPane extends TabPane {

    public TabCanvasPane() {
        super();

        setSkin(new CustomTabPaneSkin(this));
        setMinWidth(500);
        setMinHeight(500);

    }

}
