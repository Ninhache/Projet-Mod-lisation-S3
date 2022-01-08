package view.components.items;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * A new Color Picker
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class VBoxPicker extends VBox {

    private ColorPicker colorPicker;
    private Label label;

    public VBoxPicker(String text, Color color) {

        this.label = new Label(text);

        this.colorPicker = new ColorPicker(color != null ? color : Color.WHITE);

        getChildren().addAll(this.label, this.colorPicker);
    }

    public Label getLabel() {
        return label;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Color getColor() {
        return this.colorPicker.getValue();
    }
}
