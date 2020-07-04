package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreCarteCommunaute;

public class CommunauteQuitterController implements EventHandler<WindowEvent> {

	private FenetreCarteCommunaute fenetreCarteCommunaute;

	public CommunauteQuitterController(FenetreCarteCommunaute fenetreCarteCommunaute) {
		this.fenetreCarteCommunaute = fenetreCarteCommunaute;
	}

	@Override
	public void handle(WindowEvent event) {
		fenetreCarteCommunaute.getFenetreDeJeu().getPartie().reprendrePartie();
		event.consume();
	}
}
