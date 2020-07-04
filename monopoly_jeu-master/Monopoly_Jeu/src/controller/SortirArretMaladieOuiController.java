package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreSortirArretMaladie;

public class SortirArretMaladieOuiController implements EventHandler<ActionEvent> {

	private FenetreSortirArretMaladie fenetreSortirArretMaladie;

	public SortirArretMaladieOuiController(FenetreSortirArretMaladie fenetreSortirArretMaladie) {
		this.fenetreSortirArretMaladie = fenetreSortirArretMaladie;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreSortirArretMaladie.getStage().close();
		fenetreSortirArretMaladie.getFenetreDeJeu().getPartie().getPM().getCaseActive().setRep(true);
		event.consume();
	}
}
