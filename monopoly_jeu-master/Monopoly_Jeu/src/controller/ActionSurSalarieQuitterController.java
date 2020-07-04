package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.ActionSurSalarieView;

public class ActionSurSalarieQuitterController implements EventHandler<WindowEvent> {

	private ActionSurSalarieView actionSurSalarieView;

	public ActionSurSalarieQuitterController(ActionSurSalarieView actionSurSalarieView) {
		this.actionSurSalarieView = actionSurSalarieView;
	}

	@Override
	public void handle(WindowEvent event) {
		actionSurSalarieView.getFenetreDeJeu()
				.refreshLabels(actionSurSalarieView.getFenetreDeJeu().getPartie().getPM());
		event.consume();
	}
}