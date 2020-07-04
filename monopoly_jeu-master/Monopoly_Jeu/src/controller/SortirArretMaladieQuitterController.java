package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.SortirArretMaladieView;

public class SortirArretMaladieQuitterController implements EventHandler<WindowEvent> {

	private SortirArretMaladieView sortirArretMaladieView;

	public SortirArretMaladieQuitterController(SortirArretMaladieView sortirArretMaladieView) {
		this.sortirArretMaladieView = sortirArretMaladieView;
	}

	@Override
	public void handle(WindowEvent event) {
		sortirArretMaladieView.getFenetreDeJeu().getPartie().reprendrePartie();
		event.consume();
	}
}