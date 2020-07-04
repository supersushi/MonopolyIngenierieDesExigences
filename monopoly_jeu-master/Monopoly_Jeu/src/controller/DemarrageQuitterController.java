package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.MonopolyDemarrageView;

public class DemarrageQuitterController implements EventHandler<WindowEvent> {

	private MonopolyDemarrageView monopolyDemarrageView;

	public DemarrageQuitterController(MonopolyDemarrageView monopolyDemarrageView) {
		this.monopolyDemarrageView = monopolyDemarrageView;
	}

	@Override
	public void handle(WindowEvent event) {
		if (monopolyDemarrageView.getChoix() == 0)
			System.exit(0);
		event.consume();

	}
}