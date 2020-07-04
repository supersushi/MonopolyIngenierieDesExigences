package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreEmbaucheSalarie;

public class EmbaucheSalarieNonController implements EventHandler<ActionEvent> {

	private FenetreEmbaucheSalarie fenetreEmbaucheSalarie;

	public EmbaucheSalarieNonController(FenetreEmbaucheSalarie fenetreEmbaucheSalarie) {
		this.fenetreEmbaucheSalarie = fenetreEmbaucheSalarie;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreEmbaucheSalarie.getStage().close();
		event.consume();
	}
}