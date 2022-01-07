package view;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.maths.Rotation;


public class AutoRotaBox extends HBox {
	
	private Button autoRota;
	private Slider speedSlider;
	private int vitesse;
	
	public AutoRotaBox() {
		super();
		this.autoRota=new Button();
		this.speedSlider=new Slider(1,20,1);
		this.vitesse=51;
		
		speedSlider.setShowTickLabels(true);
		
		ImageView playImg=initImage("/img/play.png");
		
		ImageView stopImg=initImage("/img/pause.png");
		
		AtomicBoolean started = new AtomicBoolean(false);
		
	    autoRota.setGraphic(playImg);
	    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(vitesse), event -> {
	        BorderPane bp = (BorderPane) getParent().getScene().getRoot();
	        TabCanvasPane tp = (TabCanvasPane) bp.getCenter();
	        tp.rotateModel(Rotation.Y, 1);
	    }));
	    
	    timeline.setCycleCount(Animation.INDEFINITE);

	    autoRota.setOnAction(event -> {
	        started.set(!started.get());
	        if(started.get()) {
	            timeline.play();
	            autoRota.setGraphic(stopImg);
	        } else {
	            timeline.pause();
	            autoRota.setGraphic(playImg);
	        }}
	    );
	    
	    this.speedSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				vitesse-=newValue.intValue();
				
			}
		});
	    
	    getChildren().addAll(autoRota,speedSlider);
	}
	
	public ImageView initImage(String path) {
		ImageView img=new ImageView(path);
		img.setFitHeight(32);
		img.setFitWidth(32);
		return img;
		
	}

	public Button getAutoRota() {
		return autoRota;
	}

	public void setAutoRota(Button autoRota) {
		this.autoRota = autoRota;
	}

	public Slider getSpeed() {
		return speedSlider;
	}

	public void setSpeed(Slider speed) {
		this.speedSlider = speed;
	}
	
}
