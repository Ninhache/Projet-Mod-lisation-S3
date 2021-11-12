package view.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Model;

public class CanvasSingle extends HBox {
	
	private CanvasModel canvasModel;
	
	public CanvasSingle() {
		super();
		canvasModel=new CanvasModel();
		getChildren().add(canvasModel);
		setHeight(300);
		setWidth(300);
	}
	
	public void setModel(Model model) {
		this.canvasModel.setModel(model);
		this.canvasModel.initDraw();
	}
	
	public void draw() {
		this.canvasModel.draw();
	}
}
