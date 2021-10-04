package view.dialogs;

import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;

public class CustomCheckBox extends CustomMenuItem {

    private CheckBox checkBox;

    public CustomCheckBox(String label, boolean hideOnClick) {
        super();
        checkBox = new CheckBox(label);

        setContent(checkBox);

        setHideOnClick(hideOnClick);
        checkBox.setStyle("-fx-text-fill: black;");
    }

    public CustomCheckBox(String label) {
        this(label, false);
    }
}
