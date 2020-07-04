package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreCarteCommunaute;

public class CommunauteValiderController implements EventHandler<ActionEvent> {

	private FenetreCarteCommunaute fenetreCarteCommunaute;

	public CommunauteValiderController(FenetreCarteCommunaute fenetreCarteCommunaute) {
		this.fenetreCarteCommunaute = fenetreCarteCommunaute;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreCarteCommunaute.getStage().close();
		event.consume();
	}
}