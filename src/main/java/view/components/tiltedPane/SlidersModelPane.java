package view.components.tiltedPane;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Model;
import model.Rotation;
import view.components.tabpane.TabCanvas;
import view.nodes.SliderBox;
import view.nodes.SpinnerBox;
import view.nodes.TabCanvasPane;


/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModelPane extends TitledPane {

	private SliderBox sliderBoxX, sliderBoxY, sliderBoxZ;
	private SpinnerBox scale;
	private Button reset;
	private Label title, labelVertices, labelFaces, labelAuteur, labelComment;
	private HBox scaleReset;

	public SlidersModelPane() {
		super();
		setText("Sliders");

		VBox root = new VBox();

		VBox informations = new VBox();

		title = new Label("Informations sur le PLY :");
		labelVertices = new Label("Nombres points :");
		labelFaces = new Label("Nombres faces :");
		labelAuteur = new Label("Auteur :");
		labelComment = new Label("Commentaires :");

		informations.getChildren().addAll(title, labelVertices, labelFaces, labelAuteur, labelComment);

		sliderBoxX = new SliderBox(Rotation.X);
		sliderBoxY = new SliderBox(Rotation.Y);
		sliderBoxZ = new SliderBox(Rotation.Z);
		scale = new SpinnerBox();
		reset = new Button("Reset");
		scaleReset = new HBox();
		scaleReset.getChildren().addAll(scale,reset);

		root.getChildren().addAll(informations,new Separator(), sliderBoxX, sliderBoxY, sliderBoxZ, scaleReset);
		setContent(root);
		
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sliderBoxX.getSlider().setValue(180);
				sliderBoxY.getSlider().setValue(180);
				sliderBoxZ.getSlider().setValue(180);
				scale.getSpinner().getValueFactory().setValue(1.0);
				BorderPane bp = (BorderPane) getParent().getScene().getRoot();
				TabCanvasPane tp = (TabCanvasPane) bp.getCenter();

				TabCanvas tb = (TabCanvas) tp.getTabs();
				Model model = tb.getCanvas().getModel();
				
				model.getMatrix().resetToDefaultValues();
			}
		
		});

	}

	public void updateInformations(Number number) {
		if(number.intValue() == -1) {
			this.setExpanded(false);
			return;
		}

		BorderPane bp = (BorderPane) getParent().getScene().getRoot();
		TabCanvasPane tp = (TabCanvasPane) bp.getCenter();

		TabCanvas tb = (TabCanvas) tp.getTabs().get(number.intValue());
		Model model = tb.getCanvas().getModel();

		if(model != null) {
			this.title.setText("Informations sur le PLY : " + model.getNameOfFile());
			this.labelAuteur.setText("Auteur : " + model.getAuthor());
			this.labelComment.setText("Comment : " + model.getDescription());
			this.labelVertices.setText("Nombres points : " + model.getVertices().size());
			this.labelFaces.setText("Nombres faces : " + model.getFaces().size());
		}

	}
}