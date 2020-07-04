package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ActionSurSalarieView;

public class ActionSurSalarieAjoutCompetenceController implements EventHandler<ActionEvent> {
	private ActionSurSalarieView actionSurSalarieView;
	private String competence;

	public ActionSurSalarieAjoutCompetenceController(ActionSurSalarieView actionSurSalarieView, String competence) {
		this.actionSurSalarieView = actionSurSalarieView;
		this.competence = competence;
	}

	@Override
	public void handle(ActionEvent event) {
		CaseSalarieController c = (CaseSalarieController) actionSurSalarieView.getMonopolyView().getPartie().getPM()
				.getCase(actionSurSalarieView.getPosition());
		if (c.getPeutMonterEnCompetence()) {
			c.monterEnCompetence(actionSurSalarieView.getMonopolyView(), competence);
			actionSurSalarieView.getMonopolyView().setCompetence(c);
			actionSurSalarieView.getStage().close();
		} else {
			actionSurSalarieView.getErrorTxt().setText("Impossible d'obtenir une compétence.");
		}
		event.consume();
	}
}