package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreCarteChance;

public class ChanceValiderController implements EventHandler<ActionEvent> {

	private FenetreCarteChance fenetreCarteChance;

	public ChanceValiderController(FenetreCarteChance fenetreCarteChance) {
		this.fenetreCarteChance = fenetreCarteChance;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreCarteChance.getStage().close();
		event.consume();
	}
}