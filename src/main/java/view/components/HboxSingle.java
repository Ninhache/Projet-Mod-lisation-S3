package view.components;

import javafx.scene.layout.HBox;
import model.Model;

public class HboxSingle extends HBox {
	
	private CanvasModel canvasModel;
	
	public HboxSingle() {
		super();
		canvasModel=new CanvasModel();
		getChildren().add(canvasModel);
		setHeight(300);
		setWidth(300);
		canvasModel.setWidth(300.0);
		canvasModel.setHeight(300.0);
	}
	
	public void setModel(Model model) {
		this.canvasModel.setModel(model);
		this.canvasModel.initDraw();
	}
	
	public void draw() {
		this.canvasModel.draw();
	}
	
	public CanvasModel getCanvasModel() {
		return canvasModel;
	}
	
	@Override
	public String toString() {
		return this.canvasModel.getModel().getVertices().toString();
	}
}
