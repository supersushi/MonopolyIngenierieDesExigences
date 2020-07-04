package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.SortirArretMaladieView;

public class SortirArretMaladieNonController implements EventHandler<ActionEvent> {

	private SortirArretMaladieView sortirArretMaladieView;

	public SortirArretMaladieNonController(SortirArretMaladieView sortirArretMaladieView) {
		this.sortirArretMaladieView = sortirArretMaladieView;
	}

	@Override
	public void handle(ActionEvent event) {
		sortirArretMaladieView.getStage().close();
		event.consume();
	}
}
