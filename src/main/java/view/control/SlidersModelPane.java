package view.control;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.maths.Rotation;
import model.models.Model;
import view.components.render.AutoRotaBox;
import view.components.render.ScaleSpinnerBox;
import view.components.render.SliderBox;
import view.components.render.TabCanvas;

/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModelPane extends TitledPane {

	private SliderBox sliderBoxX, sliderBoxY, sliderBoxZ;
	private ScaleSpinnerBox scale;
	private Button reset;
	private Label title, labelVertices, labelFaces, labelAuteur, labelComment;
	private HBox scaleReset;
	private AutoRotaBox autoRotation;

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
		scale = new ScaleSpinnerBox();
		reset = new Button("Reset");
		scaleReset = new HBox();
		scaleReset.getChildren().addAll(scale,reset);
		
		autoRotation=new AutoRotaBox();

		root.getChildren().addAll(informations,new Separator(), sliderBoxX, sliderBoxY, sliderBoxZ, scaleReset, autoRotation);
		setContent(root);
		
		reset.setOnAction(event -> {
				sliderBoxX.getSlider().setValue(180);
				sliderBoxY.getSlider().setValue(180);
				sliderBoxZ.getSlider().setValue(180);
				scale.getSpinner().getValueFactory().setValue(1.0);
				BorderPane bp = (BorderPane) getParent().getScene().getRoot();
				TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
				TabCanvas tb = (TabCanvas) tp.getSelectionModel().getSelectedItem();
				tb.initDraw();
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
		Model model = tb.getCanvasTop().getModel();

		if(model != null) {
			this.title.setText("Informations sur le PLY : " + model.getNameOfFile());
			this.labelAuteur.setText("Auteur : " + model.getAuthor());
			this.labelComment.setText("Comment : " + model.getDescription());
			this.labelVertices.setText("Nombres points : " + model.getVertices().size());
			this.labelFaces.setText("Nombres faces : " + model.getFaces().size());
		}

	}
}