package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.FenetreDemarrage;

public class DemarrageQuitterController implements EventHandler<WindowEvent> {

	private FenetreDemarrage fenetreDemarrage;

	public DemarrageQuitterController(FenetreDemarrage fenetreDemarrage) {
		this.fenetreDemarrage = fenetreDemarrage;
	}

	@Override
	public void handle(WindowEvent event) {
		if (fenetreDemarrage.getChoix() == 0)
			System.exit(0);
		event.consume();

	}
}