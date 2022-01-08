package view.stages;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.components.render.TabCanvas;
import view.control.TabCanvasPane;
import view.components.items.VBoxPicker;

/**
 * Permit to select the color of every model's elements
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class ColorHandlerStage extends ExtendedStage {

	private VBoxPicker verticesBox, strokesBox, facesBox, backgroundBox;
	
	private final Button closeButton, applyButton;
	
	private TabCanvasPane tabcanvas;
	
	public ColorHandlerStage(TabCanvasPane tabcanvas) {
		super();
		this.tabcanvas = tabcanvas;
		
		VBox root = new VBox();
        root.setPadding(new Insets(18));
        root.setSpacing(10);

        closeButton = new Button("Fermer");
        closeButton.setDefaultButton(true);
        closeButton.setOnAction(this::onCloseBtnClicked);
        closeButton.requestFocus();
        closeButton.setPadding(new Insets(5));

        applyButton = new Button("Appliquer");
        applyButton.setOnAction(this::onApplyBtnClicked);
        applyButton.setPadding(new Insets(5));

        Scene scene = new Scene(root, 400,300);
        setScene(scene);
        setResizable(false);

        setTitle("Color Selector");

        TabCanvas tabCanvas = (TabCanvas) this.tabcanvas.getSelectionModel().getSelectedItem();

        this.verticesBox = new VBoxPicker("Points", tabCanvas.getVerticesColor());
        this.strokesBox = new VBoxPicker("Strokes", tabCanvas.getStrokesColor());
        this.facesBox = new VBoxPicker("Faces", tabCanvas.getFacesColor());
        this.backgroundBox = new VBoxPicker("Background", tabCanvas.getBackgroundColor());

        HBox bottomMenu = new HBox(applyButton, closeButton);
		bottomMenu.setAlignment(Pos.CENTER);


        root.getChildren().addAll(this.verticesBox, this.strokesBox, this.facesBox, this.backgroundBox, new Separator(Orientation.HORIZONTAL), bottomMenu );
        root.setAlignment(Pos.CENTER);
	}

    private void onCloseBtnClicked(ActionEvent e) {
        this.close();
    }
    
    private void onApplyBtnClicked(ActionEvent e) {
        TabCanvas tabCanvas = (TabCanvas) this.tabcanvas.getSelectionModel().getSelectedItem();

        tabCanvas.setVerticesColor(this.verticesBox.getColor());
        tabCanvas.setFacesColor(this.facesBox.getColor());
        tabCanvas.setStrokesColor(this.strokesBox.getColor());
        tabCanvas.setBackgroundColor(this.backgroundBox.getColor());

        tabCanvas.draw();
        this.close();
    }
}
