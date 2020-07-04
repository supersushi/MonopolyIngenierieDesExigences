package controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import view.CarteCommunauteView;

public class CommunauteQuitterController implements EventHandler<WindowEvent> {

	private CarteCommunauteView carteCommunauteView;

	public CommunauteQuitterController(CarteCommunauteView carteCommunauteView) {
		this.carteCommunauteView = carteCommunauteView;
	}

	@Override
	public void handle(WindowEvent event) {
		carteCommunauteView.getMonopolyView().getPartie().reprendrePartie();
		event.consume();
	}
}
