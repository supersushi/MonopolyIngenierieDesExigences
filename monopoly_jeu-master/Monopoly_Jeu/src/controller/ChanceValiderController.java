package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CarteChanceView;

public class ChanceValiderController implements EventHandler<ActionEvent> {

	private CarteChanceView carteChanceView;

	public ChanceValiderController(CarteChanceView carteChanceView) {
		this.carteChanceView = carteChanceView;
	}

	@Override
	public void handle(ActionEvent event) {
		carteChanceView.getStage().close();
		event.consume();
	}
}