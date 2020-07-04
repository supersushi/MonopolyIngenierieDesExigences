package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreDeJeu;

public class JeuTourSuivantController implements EventHandler<ActionEvent> {

	private FenetreDeJeu fenetreDeJeu;

	public JeuTourSuivantController(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreDeJeu.getPartie().reprendrePartie();
	}
}