package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import model.Rotation;
import view.dialogs.TabCanvasPane;


/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModel extends TitledPane {
	
	private Slider slidX, slidY, slidZ;
	private Label labX, labY, labZ, labValX, labValY, labValZ;
	private TextField tfX,tfY,tfZ;
	private HBox labTfX,labTfY,labTfZ;
	private double valX,valY,valZ;
	
	public SlidersModel() {
		super();

		setText("Sliders");
		setCollapsible(true);


		VBox root = new VBox();


		SliderBox sliderBoxX = new SliderBox(Rotation.X);
		SliderBox sliderBoxY = new SliderBox(Rotation.Y);
		SliderBox sliderBoxZ = new SliderBox(Rotation.Z);


		root.getChildren().addAll(sliderBoxX, sliderBoxY, sliderBoxZ);
		setContent(root);


	}

	private void setMinMax() {
		slidX.setMin(0);
        slidY.setMin(0);
        slidZ.setMin(0);
        
        slidX.setMax(360);
        slidY.setMax(360);
        slidZ.setMax(360);
	}

	private void showTickMarks() {
		slidX.setShowTickLabels(true);
        slidY.setShowTickLabels(true);
        slidZ.setShowTickLabels(true);
        
        slidX.setMinorTickCount(0);
        slidY.setMinorTickCount(0);
        slidZ.setMinorTickCount(0);
        
        slidX.setShowTickMarks(true);
        slidY.setShowTickMarks(true);
        slidZ.setShowTickMarks(true);
        
        slidX.setBlockIncrement(10);
        slidY.setBlockIncrement(10);
        slidZ.setBlockIncrement(10);
	}

	
	public double getValX() {
		return valX;
	}

	public void setValX(int valX) {
		this.valX = valX;
	}

	public double getValY() {
		return valY;
	}

	public void setValY(int valY) {
		this.valY = valY;
	}

	public double getValZ() {
		return valZ;
	}

	public void setValZ(int valZ) {
		this.valZ = valZ;
	}       
 
	public Slider getSlidX() {
		return slidX;
	}
}


