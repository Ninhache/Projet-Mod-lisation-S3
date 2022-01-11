package view.components.render;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.maths.Rotation;
import view.control.TabCanvasPane;

/**
 *  The SliderBox is an HBox which contains an HBox and a label (displaying the actual value)
 *
 * @author Matteo MACIEIRA
 * @version %I%, %G%
 */
public class SliderBox extends HBox {

    private Label label;
    private Slider slider;

	private Spinner<Integer> spinner;

    /**
     * A SliderBox takes a Rotation in parameter to rotate according to the angle
     * @param rotation {@link Rotation}
     */
    public SliderBox(Rotation rotation) {
        super();

        this.slider = new Slider(0,360,180);
        this.label = new Label(rotation.getRotation() + " : ");
        this.spinner = new Spinner<>(0, 360, 180);
        this.spinner.setMaxWidth(80);

        setupSliders();

        this.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(rotation.getRotation() + " : ");
            spinner.getValueFactory().setValue(newValue.intValue());
            BorderPane bp = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
            tp.rotateModel(rotation,newValue.intValue()-oldValue.intValue());
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

    /**
     * Setups the initial values of the sliders
     */
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

	public void setSpinner(Spinner<Integer> spinner) {
		this.spinner = spinner;
	}
	
    public Slider getSlider() {
		return slider;
	}

	public void setSlider(Slider slider) {
		this.slider = slider;
	}
}
