package view.nodes;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Rotation;

public class SpinnerBox extends HBox {


    private Label label;
    private Spinner<Double> spinner;

    private final double MAX = 2.0;
    private final double MIN = 0.1;
    public SpinnerBox() {
        super();


        this.label = new Label("Echelle: ");
        this.spinner = new Spinner<Double>(MIN,MAX,1,0.1);
        this.spinner.setMaxWidth(80);


        this.spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Echelle: ");
            spinner.getValueFactory().setValue(newValue.doubleValue());
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
