package view.components.tiltedPane;


import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Model;
import model.Rotation;
import view.components.CanvasModel;
import view.components.tabpane.TabCanvas;
import view.nodes.AutoRotaBox;
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
	private Button autoRota;
	private AtomicBoolean started;
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
		scale = new SpinnerBox();
		reset = new Button("Reset");
		scaleReset = new HBox();
		scaleReset.getChildren().addAll(scale,reset);
		
		autoRotation=new AutoRotaBox();
			    
		root.getChildren().addAll(informations,new Separator(), sliderBoxX, sliderBoxY, sliderBoxZ, scaleReset, autoRotation);
		setContent(root);
		
		reset.setOnAction(event -> {
				
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