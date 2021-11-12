package view.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;

public class CanvasSingle extends HBox {
	
	public CanvasSingle() {
		super();
		getChildren().add(new Canvas());
	}
}
