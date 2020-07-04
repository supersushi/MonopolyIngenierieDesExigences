package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CarteCommunauteView;

public class CommunauteValiderController implements EventHandler<ActionEvent> {

	private CarteCommunauteView carteCommunauteView;

	public CommunauteValiderController(CarteCommunauteView carteCommunauteView) {
		this.carteCommunauteView = carteCommunauteView;
	}

	@Override
	public void handle(ActionEvent event) {
		carteCommunauteView.getStage().close();
		event.consume();
	}
}