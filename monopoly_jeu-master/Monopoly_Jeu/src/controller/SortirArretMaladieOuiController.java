package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.SortirArretMaladieView;

public class SortirArretMaladieOuiController implements EventHandler<ActionEvent> {

	private SortirArretMaladieView sortirArretMaladieView;

	public SortirArretMaladieOuiController(SortirArretMaladieView sortirArretMaladieView) {
		this.sortirArretMaladieView = sortirArretMaladieView;
	}

	@Override
	public void handle(ActionEvent event) {
		sortirArretMaladieView.getStage().close();
		sortirArretMaladieView.getFenetreDeJeu().getPartie().getPM().getCaseActive().setRep(true);
		event.consume();
	}
}
