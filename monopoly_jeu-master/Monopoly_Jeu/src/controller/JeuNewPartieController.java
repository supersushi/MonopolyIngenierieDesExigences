package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import view.MonopolyView;

public class JeuNewPartieController implements EventHandler<ActionEvent> {

	private MonopolyView monopolyView;

	public JeuNewPartieController(MonopolyView monopolyView) {
		this.monopolyView = monopolyView;
	}

	@Override
	public void handle(ActionEvent event) {
		monopolyView.getStage().close();
		monopolyView.resetElementsGraphiques();
		monopolyView.setRoot(new StackPane());
		monopolyView.initRoot();
		Scene scene = new Scene(monopolyView.getRoot(), 655, 655);
		monopolyView.getStage().setScene(scene);
		monopolyView.getMonopolyDemarrageView().getStage().show();
	}
}
