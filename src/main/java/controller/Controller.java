package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import model.Model;
import model.Vecteur;

public class Controller implements EventHandler<Event>{
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void handle(Event event) {
		Slider s = (Slider) event.getSource();
		
		if(s == null) return null; 
		
		switch(s.getId()) {
			case "SliderX":
				System.out.println(model.getMatrix());
				break;
			case "SliderY":
				System.out.println(model.getMatrix());
				break;
			default:
				System.out.println("y'a un problème frérot");
				break;
		}
		
		
	}
	
	public void changeX() {
		model.getMatrix().translation(new Vecteur(1, 1, 12));
	}
	/*public void changeY();
	public void changeZ();
	
	public void rotation();
	public void translation();
	*/
	
}
