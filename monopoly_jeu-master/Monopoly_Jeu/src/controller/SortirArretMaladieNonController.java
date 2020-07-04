package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreSortirArretMaladie;

public class SortirArretMaladieNonController implements EventHandler<ActionEvent> {

	private FenetreSortirArretMaladie fenetreSortirArretMaladie;

	public SortirArretMaladieNonController(FenetreSortirArretMaladie fenetreSortirArretMaladie) {
		this.fenetreSortirArretMaladie = fenetreSortirArretMaladie;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreSortirArretMaladie.getStage().close();
		event.consume();
	}
}
