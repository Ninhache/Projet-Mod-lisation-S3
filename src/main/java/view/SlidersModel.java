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
	private Label labX, labY, labZ, valX, valY, valZ;

	
	public SlidersModel() {
		super();
		
		labX = new Label("X : ");
		slidX = new Slider();
		labY = new Label("Y : ");
		slidY = new Slider();
		labZ = new Label("Z : ");
		slidZ = new Slider();
        
		valX = new Label("0");
		valY = new Label("0");
		valZ = new Label("0");
		
		
		
		setMinMax();

        showTickMarks();
        
        
        slidX.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				valX.setText(""+newValue.intValue());
				
			}
         });
        
        slidY.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				valY.setText(""+newValue.intValue());
				
			}
         });
        
        slidZ.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				valZ.setText(""+newValue.intValue());
				
			}
         });
        
        
       
        setSpacing(10);
        getChildren().addAll(labX,slidX,valX,labY,slidY,valY,labZ,slidZ,valZ);
        
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
     
}
