package view;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class SlidersModel extends VBox {
	
	private Slider slidX, slidY, slidZ;
	private Label labX, labY, labZ;

	
	public SlidersModel() {
		super();
		
		labX = new Label("X : ");
		slidX = new Slider();
		labY = new Label("Y : ");
		slidY = new Slider();
		labZ = new Label("Z : ");
		slidZ = new Slider();
        
		setMinMax();

        showTickMarks();
        
       
        setSpacing(10);
        getChildren().addAll(labX,slidX,labY,slidY,labZ,slidZ);
        
	}

	private void setMinMax() {
		slidX.setMin(0);
        slidY.setMin(0);
        slidZ.setMin(0);
        
        slidX.setMax(360);
        slidY.setMax(360);
        slidZ.setMax(360);
	}

	private void showTickMarks() {
		slidX.setShowTickLabels(true);
        slidY.setShowTickLabels(true);
        slidZ.setShowTickLabels(true);
        
        slidX.setShowTickMarks(true);
        slidY.setShowTickMarks(true);
        slidZ.setShowTickMarks(true);
	}       
     
}
