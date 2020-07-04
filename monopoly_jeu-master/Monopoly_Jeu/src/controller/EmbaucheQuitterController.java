package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.EmbaucheSalarieView;

public class EmbaucheQuitterController implements EventHandler<WindowEvent> {

	private EmbaucheSalarieView embaucheSalarieView;

	public EmbaucheQuitterController(EmbaucheSalarieView embaucheSalarieView) {
		this.embaucheSalarieView = embaucheSalarieView;
	}

	@Override
	public void handle(WindowEvent event) {
		embaucheSalarieView.getMonopolyView().getPartie().reprendrePartie();
		event.consume();
	}
}
