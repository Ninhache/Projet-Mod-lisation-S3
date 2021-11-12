package view.components;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Model;
import model.Rotation;

public class CanvasBox extends VBox {

	private CanvasSingle canvasBot, canvasTop;
	
    public CanvasBox() {
    	this.canvasTop=new CanvasSingle();
    	this.canvasBot=new CanvasSingle();
    	setHeight(600);
        getChildren().addAll(canvasTop, canvasBot,new Button());
    }
    
    public void setModel(Model model) {
    	this.canvasTop.setModel(new Model(model.getVertices(),model.getFaces(),Rotation.X,90));
    	this.canvasBot.setModel(new Model(model.getVertices(),model.getFaces(),Rotation.Y,90));
    }

}
