package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreActionSurSalarie;

public class ActionSurSalarieQuitterController implements EventHandler<WindowEvent> {

	private FenetreActionSurSalarie fenetreActionSurSalarie;

	public ActionSurSalarieQuitterController(FenetreActionSurSalarie fenetreActionSurSalarie) {
		this.fenetreActionSurSalarie = fenetreActionSurSalarie;
	}

	@Override
	public void handle(WindowEvent event) {
		fenetreActionSurSalarie.getFenetreDeJeu()
				.refreshLabels(fenetreActionSurSalarie.getFenetreDeJeu().getPartie().getPM());
		event.consume();
	}
}