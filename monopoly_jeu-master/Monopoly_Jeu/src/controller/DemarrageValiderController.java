package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import view.MonopolyDemarrageView;
import view.MonopolyView;

public class DemarrageValiderController implements EventHandler<ActionEvent> {

	private ArrayList<TextField> listePlayer = new ArrayList<TextField>();
	private MonopolyDemarrageView monopolyDemarrageView;
	private MonopolyView monopolyView;

	public DemarrageValiderController(MonopolyDemarrageView monopolyDemarrageView) {
		this.monopolyDemarrageView = monopolyDemarrageView;
		this.monopolyView = monopolyDemarrageView.getMonopolyView();
		this.listePlayer = monopolyDemarrageView.getListePlayer();
	}

	@Override
	public void handle(ActionEvent event) {
		ArrayList<String> champs = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			if (listePlayer.get(i).getText() != null && !listePlayer.get(i).getText().isEmpty())
				champs.add(listePlayer.get(i).getText());
		}
		if (champs.size() >= 2) {
			monopolyDemarrageView.setChoix(1);
			monopolyView.setPartie(champs.size(), champs);
			monopolyView.getStage().show();
			monopolyDemarrageView.getStage().close();
		}
		event.consume();
	}
}