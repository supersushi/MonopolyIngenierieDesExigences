package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.FenetreActionSurSalarie;

public class ActionSurSalarieAjoutCompetenceController implements EventHandler<ActionEvent> {
	private FenetreActionSurSalarie fenetreActionSurSalarie;
	private String competence;

	public ActionSurSalarieAjoutCompetenceController(FenetreActionSurSalarie fenetreActionSurSalarie, String competence) {
		this.fenetreActionSurSalarie = fenetreActionSurSalarie;
		this.competence = competence;
	}

	@Override
	public void handle(ActionEvent event) {
		CaseSalarieController c = (CaseSalarieController) fenetreActionSurSalarie.getFenetreDeJeu().getPartie().getPM()
				.getCase(fenetreActionSurSalarie.getPosition());
		if (c.getPeutMonterEnCompetence()) {
			c.monterEnCompetence(fenetreActionSurSalarie.getFenetreDeJeu(), competence);
			fenetreActionSurSalarie.getFenetreDeJeu().setCompetence(c);
			fenetreActionSurSalarie.getStage().close();
		} else {
			fenetreActionSurSalarie.getErrorTxt().setText("Impossible d'obtenir une compétence.");
		}
		event.consume();
	}
}