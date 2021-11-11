package view.components.tiltedPane;


import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Rotation;
import view.nodes.SliderBox;


/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModel extends TitledPane {

	private SliderBox sliderBoxX, sliderBoxY, sliderBoxZ;

	public SlidersModel() {
		super();
		setText("Sliders");

		VBox root = new VBox();

		sliderBoxX = new SliderBox(Rotation.X);
		sliderBoxY = new SliderBox(Rotation.Y);
		sliderBoxZ = new SliderBox(Rotation.Z);

		root.getChildren().addAll(sliderBoxX, sliderBoxY, sliderBoxZ);
		setContent(root);

	}

}