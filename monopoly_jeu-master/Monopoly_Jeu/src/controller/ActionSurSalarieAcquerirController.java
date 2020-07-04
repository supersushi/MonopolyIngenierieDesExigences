package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreActionSurSalarie;

public class ActionSurSalarieAcquerirController implements EventHandler<ActionEvent> {
	private FenetreActionSurSalarie fenetreActionSurSalarie;

	public ActionSurSalarieAcquerirController(FenetreActionSurSalarie fenetreActionSurSalarie) {
		this.fenetreActionSurSalarie = fenetreActionSurSalarie;
	}

	@Override
	public void handle(ActionEvent event) {
		CaseSalarieController c = (CaseSalarieController) fenetreActionSurSalarie.getFenetreDeJeu().getPartie().getPM()
				.getCase(fenetreActionSurSalarie.getPosition());
		if (c.getPeutMonterEnCompetence()) {
			c.monterEnCompetence(fenetreActionSurSalarie.getFenetreDeJeu());
			fenetreActionSurSalarie.getFenetreDeJeu().setCompetence(c);
			fenetreActionSurSalarie.getStage().close();
		} else {
			fenetreActionSurSalarie.getErrorTxt().setText("Impossible d'obtenir une compétence.");
		}
		event.consume();
	}
}