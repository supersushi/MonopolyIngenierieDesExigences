package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MonopolyView;

public class JeuTourSuivantController implements EventHandler<ActionEvent> {

	private MonopolyView monopolyView;

	public JeuTourSuivantController(MonopolyView monopolyView) {
		this.monopolyView = monopolyView;
	}

	@Override
	public void handle(ActionEvent event) {
		monopolyView.getPartie().reprendrePartie();
	}
}