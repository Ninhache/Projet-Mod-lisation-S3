package view.components;

import javafx.scene.layout.VBox;
import model.Model;
import model.Rotation;

public class CanvasBox extends VBox {

	private CanvasSingle canvasBot, canvasTop;
	
    public CanvasBox() {
    	this.canvasTop=new CanvasSingle();
    	this.canvasBot=new CanvasSingle();
    	setHeight(600);
        getChildren().addAll(canvasTop, canvasBot);
    }
    
    public void setModel(Model model) {
    	try {
			this.canvasTop.setModel(((Model)model.clone()));
			this.canvasBot.setModel(((Model)model.clone()));
			canvasBot.getCanvasModel().getModel().getMatrix().rotation(Rotation.X, 90);
			canvasTop.getCanvasModel().getModel().getMatrix().rotation(Rotation.Y, 90);
    	} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    }

	public CanvasSingle getCanvasTop() {
		return this.canvasTop;
	}
	
	public CanvasSingle getCanvasBot() {
		return this.canvasBot;
	}
}
