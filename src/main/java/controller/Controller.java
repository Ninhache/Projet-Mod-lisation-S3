package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import model.Model;
import model.Vector;

public class Controller implements EventHandler<Event>{
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void handle(Event event) {
		Slider s = (Slider) event.getSource();
		
		//if(s == null) return ; 
		
		switch(s.getId()) {
			case "SliderX":
				break;
			case "SliderY":
				break;
			default:
				break;
		}
		
		
	}
	
	public void changeX() {
		model.getMatrix().translation(new Vector(1, 1, 12));
	}
	/*public void changeY();
	public void changeZ();
	
	public void rotation();
	public void translation();
	*/
	
}
