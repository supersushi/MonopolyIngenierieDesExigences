package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import view.FenetreDeJeu;

public class JeuNewPartieController implements EventHandler<ActionEvent> {

	private FenetreDeJeu fenetreDeJeu;

	public JeuNewPartieController(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}

	@Override
	public void handle(ActionEvent event) {
		fenetreDeJeu.getStage().close();
		fenetreDeJeu.resetElementsGraphiques();
		fenetreDeJeu.setRoot(new StackPane());
		fenetreDeJeu.initRoot();
		Scene scene = new Scene(fenetreDeJeu.getRoot(), 655, 655);
		fenetreDeJeu.getStage().setScene(scene);
		fenetreDeJeu.getFenetreDemarrage().getStage().show();
	}
}
