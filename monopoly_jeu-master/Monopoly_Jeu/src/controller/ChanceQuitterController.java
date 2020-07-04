package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreCarteChance;

public class ChanceQuitterController implements EventHandler<WindowEvent> {

	private FenetreCarteChance fenetreCarteChance;

	public ChanceQuitterController(FenetreCarteChance fenetreCarteChance) {
		this.fenetreCarteChance = fenetreCarteChance;
	}

	@Override
	public void handle(WindowEvent event) {
		fenetreCarteChance.getFenetreDeJeu().getPartie().reprendrePartie();
		event.consume();
	}
}