package view;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;

/**
 * A CustomCheckBox is a checkBox which does not close when you click on it
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class CustomCheckBox extends CustomMenuItem {

    private final CheckBox checkBox;

    public CustomCheckBox(String label, boolean hideOnClick) {
        super();
        checkBox = new CheckBox(label);

        setContent(checkBox);
        setHideOnClick(hideOnClick);
    }

    public CustomCheckBox(String label) {
        this(label, false);
    }

    public void setSelected(boolean value) {
        this.checkBox.setSelected(value);
    }

    public boolean isSelected() {
        return this.checkBox.isSelected();
    }

    public BooleanProperty getSelectedProperty() {
        return this.checkBox.selectedProperty();
    }

}
