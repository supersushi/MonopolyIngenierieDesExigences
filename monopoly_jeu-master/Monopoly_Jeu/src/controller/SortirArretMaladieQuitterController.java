package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreSortirArretMaladie;

public class SortirArretMaladieQuitterController implements EventHandler<WindowEvent> {

	private FenetreSortirArretMaladie fenetreSortirArretMaladie;

	public SortirArretMaladieQuitterController(FenetreSortirArretMaladie fenetreSortirArretMaladie) {
		this.fenetreSortirArretMaladie = fenetreSortirArretMaladie;
	}

	@Override
	public void handle(WindowEvent event) {
		fenetreSortirArretMaladie.getFenetreDeJeu().getPartie().reprendrePartie();
		event.consume();
	}
}