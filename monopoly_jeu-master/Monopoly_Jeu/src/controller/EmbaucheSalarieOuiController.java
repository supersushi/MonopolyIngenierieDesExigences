package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreEmbaucheSalarie;

public class EmbaucheSalarieOuiController implements EventHandler<ActionEvent> {

	private FenetreEmbaucheSalarie fenetreEmbaucheSalarie;

	public EmbaucheSalarieOuiController(FenetreEmbaucheSalarie fenetreEmbaucheSalarie) {
		this.fenetreEmbaucheSalarie = fenetreEmbaucheSalarie;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreEmbaucheSalarie.getFenetreDeJeu().getPartie().getPM().getCaseActive().setRep(true);
		fenetreEmbaucheSalarie.getStage().close();
		event.consume();
	}
}