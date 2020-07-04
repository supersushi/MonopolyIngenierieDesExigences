package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.EmbaucheSalarieView;

public class EmbaucheSalarieOuiController implements EventHandler<ActionEvent> {

	private EmbaucheSalarieView embaucheSalarieView;

	public EmbaucheSalarieOuiController(EmbaucheSalarieView embaucheSalarieView) {
		this.embaucheSalarieView = embaucheSalarieView;
	}

	@Override
	public void handle(ActionEvent event) {
		embaucheSalarieView.getFenetreDeJeu().getPartie().getPM().getCaseActive().setRep(true);
		embaucheSalarieView.getStage().close();
		event.consume();
	}
}