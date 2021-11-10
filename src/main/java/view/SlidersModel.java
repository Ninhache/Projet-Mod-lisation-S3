package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import model.Rotation;
import view.dialogs.TabCanvasPane;


/**
 * Sliders that allow to rotate the model on the X, Y or Z axis
 *
 * @author Matteo Macieira
 * @version %I%, %G%
 */

public class SlidersModel extends VBox {
	
	private Slider slidX, slidY, slidZ;
	private Label labX, labY, labZ, labValX, labValY, labValZ;
	private TextField tfX,tfY,tfZ;
	private HBox labTfX,labTfY,labTfZ;
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
		
		tfX = new TextField();
		tfX.setMaxWidth(40);
		tfX.setText("0");
		tfY = new TextField();
		tfY.setMaxWidth(40);
		tfY.setText("0");
		tfZ = new TextField();
		tfZ.setMaxWidth(40);
		tfZ.setText("0");
		
		valX = 0;
		valY = 0;
		valZ = 0;
		
		labTfX = new HBox();
		labTfY = new HBox();
		labTfZ = new HBox();
		
		
		labTfX.getChildren().addAll(labX,tfX);
		labTfY.getChildren().addAll(labY,tfY);
		labTfZ.getChildren().addAll(labZ,tfZ);
		
		setMinMax();

        showTickMarks();
        
        
        slidX.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valX = (double) newValue;
				labValX.setText(""+(int) valX);
				BorderPane bp=(BorderPane) getParent().getScene().getRoot();
				TabCanvasPane tp=(TabCanvasPane) bp.getCenter();
				tp.rotateModel(Rotation.X,newValue.intValue()-oldValue.intValue());
			}
         });
        
        slidY.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valY = (double) newValue;
				labValY.setText(""+(int) valY);
				BorderPane bp=(BorderPane) getParent().getScene().getRoot();
				TabCanvasPane tp=(TabCanvasPane) bp.getCenter();
				tp.rotateModel(Rotation.Y,newValue.intValue()-oldValue.intValue());
			}
         });
        
        slidZ.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				valZ = (double) newValue;
				labValZ.setText(""+(int) valZ);
				BorderPane bp=(BorderPane) getParent().getScene().getRoot();
				TabCanvasPane tp=(TabCanvasPane) bp.getCenter();
				tp.rotateModel(Rotation.Z,newValue.intValue()-oldValue.intValue());
			}
         });
        
        tfX.textProperty().addListener(new ChangeListener<>() {


			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				valX=(double) Integer.parseInt(newValue);
				slidX.setValue(valX);				
			}
		});
        
        tfY.textProperty().addListener(new ChangeListener<>() {


			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				valY=(double) Integer.parseInt(newValue);
				slidY.setValue(valY);				
			}
		});
       
        tfZ.textProperty().addListener(new ChangeListener<>() {


			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				valZ=(double) Integer.parseInt(newValue);
				slidZ.setValue(valZ);				
			}
		});
        
        setSpacing(10);
        getChildren().addAll(labTfX,slidX,labValX,labTfY,slidY,labValY,labTfZ,slidZ,labValZ);
        
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
 
	public Slider getSlidX() {
		return slidX;
	}
}


