package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.CarteChanceView;

public class ChanceQuitterController implements EventHandler<WindowEvent> {

	private CarteChanceView carteChanceView;

	public ChanceQuitterController(CarteChanceView carteChanceView) {
		this.carteChanceView = carteChanceView;
	}

	@Override
	public void handle(WindowEvent event) {
		carteChanceView.getMonopolyView().getPartie().reprendrePartie();
		event.consume();
	}
}