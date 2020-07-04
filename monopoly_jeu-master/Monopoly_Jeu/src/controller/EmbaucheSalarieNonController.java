package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.EmbaucheSalarieView;

public class EmbaucheSalarieNonController implements EventHandler<ActionEvent> {

	private EmbaucheSalarieView embaucheSalarieView;

	public EmbaucheSalarieNonController(EmbaucheSalarieView embaucheSalarieView) {
		this.embaucheSalarieView = embaucheSalarieView;
	}

	@Override
	public void handle(ActionEvent event) {
		embaucheSalarieView.getStage().close();
		event.consume();
	}
}