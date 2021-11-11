package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Rotation;
import view.dialogs.TabCanvasPane;

public class SliderBox extends HBox {

    private Rotation rotation;

    private Label label;
    private Slider slider;
    private TextField textField;

    public SliderBox(Rotation rotation) {
        super();

        this.rotation = rotation;

        this.slider = new Slider(0,360,180);
        this.label = new Label(rotation.getRotation() + " : " + this.slider.valueProperty().intValue());
        this.textField = new TextField();
        this.textField.setMaxWidth(40);
        this.textField.setText("0");

        setupSliders();

        this.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(rotation.getRotation() + " : " + correctString(newValue.intValue()));
            BorderPane bp = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
            tp.rotateModel(rotation,newValue.intValue()-oldValue.intValue() );
        });

        this.textField.textProperty().addListener((observable, oldValue, newValue) -> {

            try {
                this.slider.setValue(Integer.parseInt(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        getChildren().addAll(label, slider, textField);

    }

    private String correctString(int value) {
        String res = "" + value;

        while(res.length() < 3) {
            res = "0" + res;
        }

        return res;
    }

    private void setupSliders() {
        this.slider.setShowTickLabels(true);
        this.slider.setMinorTickCount(0);
        this.slider.setShowTickMarks(true);
        this.slider.setBlockIncrement(10);
    }

    public Label getLabel() {
        return label;
    }

    public Slider getSlider() {
        return slider;
    }

    public TextField getTextField() {
        return textField;
    }
}
