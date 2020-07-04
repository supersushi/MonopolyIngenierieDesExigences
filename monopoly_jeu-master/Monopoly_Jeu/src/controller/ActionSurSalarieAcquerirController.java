package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ActionSurSalarieView;

public class ActionSurSalarieAcquerirController implements EventHandler<ActionEvent> {
	private ActionSurSalarieView actionSurSalarieView;

	public ActionSurSalarieAcquerirController(ActionSurSalarieView actionSurSalarieView) {
		this.actionSurSalarieView = actionSurSalarieView;
	}

	@Override
	public void handle(ActionEvent event) {
		CaseSalarieController c = (CaseSalarieController) actionSurSalarieView.getFenetreDeJeu().getPartie().getPM()
				.getCase(actionSurSalarieView.getPosition());
		if (c.getPeutMonterEnCompetence()) {
			c.monterEnCompetence(actionSurSalarieView.getFenetreDeJeu(), null);
			actionSurSalarieView.getFenetreDeJeu().setCompetence(c);
			actionSurSalarieView.getStage().close();
		} else {
			actionSurSalarieView.getErrorTxt().setText("Impossible d'obtenir une compétence.");
		}
		event.consume();
	}
}