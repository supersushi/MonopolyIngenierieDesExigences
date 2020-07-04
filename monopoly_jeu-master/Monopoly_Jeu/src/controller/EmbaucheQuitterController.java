package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreEmbaucheSalarie;

public class EmbaucheQuitterController implements EventHandler<WindowEvent> {

	private FenetreEmbaucheSalarie fenetreEmbaucheSalarie;

	public EmbaucheQuitterController(FenetreEmbaucheSalarie fenetreEmbaucheSalarie) {
		this.fenetreEmbaucheSalarie = fenetreEmbaucheSalarie;
	}

	@Override
	public void handle(WindowEvent event) {
		fenetreEmbaucheSalarie.getFenetreDeJeu().getPartie().reprendrePartie();
		event.consume();
	}
}
