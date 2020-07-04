package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import view.FenetreDeJeu;
import view.FenetreDemarrage;

public class DemarrageValiderController implements EventHandler<ActionEvent> {

	private ArrayList<TextField> listePlayer = new ArrayList<TextField>();
	private FenetreDemarrage fenetreDemarrage;
	private FenetreDeJeu fenetreDeJeu;

	public DemarrageValiderController(FenetreDemarrage fenetreDemarrage) {
		this.fenetreDemarrage = fenetreDemarrage;
		this.fenetreDeJeu = fenetreDemarrage.getFenetreDeJeu();
		this.listePlayer = fenetreDemarrage.getListePlayer();
	}

	@Override
	public void handle(ActionEvent event) {
		ArrayList<String> champs = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			if (listePlayer.get(i).getText() != null && !listePlayer.get(i).getText().isEmpty())
				champs.add(listePlayer.get(i).getText());
		}
		if (champs.size() >= 2) {
			fenetreDemarrage.setChoix(1);
			fenetreDeJeu.setPartie(champs.size(), champs);
			fenetreDeJeu.getStage().show();
			fenetreDemarrage.getStage().close();
		}
		event.consume();
	}
}