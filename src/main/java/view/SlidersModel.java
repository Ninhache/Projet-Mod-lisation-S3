package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModel extends VBox {
	
	private Slider slidX, slidY, slidZ;
	private Label labX, labY, labZ, labValX, labValY, labValZ;
	private double valX,valY,valZ;
	
	public SlidersModel() {
		super();
		
		labX = new Label("X : ");
		slidX = new Slider();
		labY = new Label("Y : ");
		slidY = new Slider();
		labZ = new Label("Z : ");
		slidZ = new Slider();
        
		labValX = new Label("0");
		labValY = new Label("0");
		labValZ = new Label("0");
		
		valX = 0;
		valY = 0;
		valZ = 0;
		
		setMinMax();

        showTickMarks();
        
        
        slidX.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valX = (double) newValue;
				labValX.setText(""+(int) valX);
				
			}
         });
        
        slidY.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valY = (double) newValue;
				labValY.setText(""+(int) valY);
				
			}
         });
        
        slidZ.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valZ = (double) newValue;
				labValZ.setText(""+(int) valZ);
				
			}
         });
        
        
       
        setSpacing(10);
        getChildren().addAll(labX,slidX,labValX,labY,slidY,labValY,labZ,slidZ,labValZ);
        
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
        
        slidX.setMinorTickCount(0);
        slidY.setMinorTickCount(0);
        slidZ.setMinorTickCount(0);
        
        slidX.setShowTickMarks(true);
        slidY.setShowTickMarks(true);
        slidZ.setShowTickMarks(true);
        
        slidX.setBlockIncrement(10);
        slidY.setBlockIncrement(10);
        slidZ.setBlockIncrement(10);
	}

	
	public double getValX() {
		return valX;
	}

	public void setValX(int valX) {
		this.valX = valX;
	}

	public double getValY() {
		return valY;
	}

	public void setValY(int valY) {
		this.valY = valY;
	}

	public double getValZ() {
		return valZ;
	}

	public void setValZ(int valZ) {
		this.valZ = valZ;
	}       
     
}
