package view.components;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The scale spinner box is linked to the currently selected canvas model
 *
 * @author Paul VANHEE
 * @version %I%, %G%
 */
public class ScaleSpinnerBox extends HBox {

    private final Label label;
    private Spinner<Double> spinner;

    private final double MAX = 2.0;
    private final double MIN = 0.01;

    public ScaleSpinnerBox() {
        super();

        this.label = new Label("Echelle: ");
        this.spinner = new Spinner<>(MIN, MAX, 1, 0.01);
        this.spinner.setMaxWidth(80);

        this.spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Echelle: ");
            spinner.getValueFactory().setValue(newValue);
            BorderPane bp = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
            if(newValue - oldValue >0){
                tp.scaleModel(1.05);
            } else {
                tp.scaleModel(0.95f);
            }
        });

        getChildren().addAll(label, spinner);
    }

	public Spinner<Double> getSpinner() {
		return spinner;
	}

	public void setSpinner(Spinner<Double> spinner) {
		this.spinner = spinner;
	}

}
