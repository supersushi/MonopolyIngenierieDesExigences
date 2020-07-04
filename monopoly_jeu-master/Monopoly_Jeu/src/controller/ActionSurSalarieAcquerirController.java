package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.ActionSurSalarieView;

/**
 * @author Massourang Jugurtha Lina Emma
 *
 *         Permet de gérer l'acquisition d'un salarie
 */
public class ActionSurSalarieAcquerirController implements EventHandler<ActionEvent> {
	private ActionSurSalarieView actionSurSalarieView;

	public ActionSurSalarieAcquerirController(ActionSurSalarieView actionSurSalarieView) {
		this.actionSurSalarieView = actionSurSalarieView;
	}

	@Override
	public void handle(ActionEvent event) {
		CaseSalarieController c = (CaseSalarieController) actionSurSalarieView.getMonopolyView().getPartie().getPM()
				.getCase(actionSurSalarieView.getPosition());
		if (c.getPeutMonterEnCompetence()) {
<<<<<<< HEAD
			c.monterEnCompetence(actionSurSalarieView.getMonopolyView());
			actionSurSalarieView.getMonopolyView().setCompetence(c);
=======
			c.monterEnCompetence(actionSurSalarieView.getFenetreDeJeu(), null);
			actionSurSalarieView.getFenetreDeJeu().setCompetence(c);
>>>>>>> branch 'master' of https://github.com/supersushi/MonopolyIngenierieDesExigences.git
			actionSurSalarieView.getStage().close();
		} else {
			actionSurSalarieView.getErrorTxt().setText("Impossible d'obtenir une compétence.");
		}
		event.consume();
	}
}