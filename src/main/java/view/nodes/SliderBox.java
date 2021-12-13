package view.nodes;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Rotation;
import view.nodes.TabCanvasPane;

public class SliderBox extends HBox {

    private Rotation rotation;

    private Label label;
    private Slider slider;
    private Spinner<Integer> spinner;

    public SliderBox(Rotation rotation) {
        super();

        this.rotation = rotation;

        this.slider = new Slider(0,360,180);
        this.label = new Label(rotation.getRotation() + " : ");
        this.spinner = new Spinner<Integer>(0,360,180);
        this.spinner.setMaxWidth(80);

        setupSliders();

        this.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(rotation.getRotation() + " : ");
            spinner.getValueFactory().setValue(newValue.intValue());
            BorderPane bp = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
            tp.rotateModel(rotation,newValue.intValue()-oldValue.intValue());
            ((RightMenu)bp.getRight()).updateSideDraws();
        });

        this.spinner.valueProperty().addListener((observable, oldValue, newValue) -> {

            try {
                this.slider.setValue(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        getChildren().addAll(label, slider, spinner);

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

	public void setLabel(Label label) {
		this.label = label;
	}

	public Slider getSlider() {
		return slider;
	}

	public void setSlider(Slider slider) {
		this.slider = slider;
	}

	public Spinner<Integer> getSpinner() {
		return spinner;
	}

	public void setSpinner(Spinner<Integer> spinner) {
		this.spinner = spinner;
	}

}
