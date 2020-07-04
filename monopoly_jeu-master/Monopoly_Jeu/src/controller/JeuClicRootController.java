package controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Case;
import view.FenetreDeJeu;
import view.FenetreEmbaucheSalarie;

public class JeuClicRootController implements EventHandler<MouseEvent> {

	private FenetreDeJeu fenetreDeJeu;

	public JeuClicRootController(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}

	/**
	 * Évènement lorqu'on clic dans la StackPane root : en fonction des coordonnées
	 * du pointeurs, on peux obtenir la position de la case visée. <br>
	 * Si cette position est une position valide (càd que l'on clic sur une
	 * {@link CaseSalarieController} qui appartient au joueur dont c'est le tour), alors on
	 * peut déclencher l'affichage d'une {@link FenetreEmbaucheSalarie} avec en
	 * paramètre la position cliquée.
	 *
	 */
	@Override
	public void handle(MouseEvent event) {

		int pos = -1;

		if (event.getSceneX() < 84) {
			if (event.getSceneY() < 84)
				pos = 20;
			else if (event.getSceneY() < 570)
				pos = 19 - (int) ((event.getSceneY() - 84) / 54);
			else
				pos = 10;
		} else if (event.getSceneX() < 570) {
			if (event.getSceneY() < 84)
				pos = 21 + (int) ((event.getSceneX() - 84) / 54);
			else if (event.getSceneY() >= 570)
				pos = 9 - (int) ((event.getSceneX() - 84) / 54);
		} else {
			if (event.getSceneY() < 84)
				pos = 30;
			else if (event.getSceneY() < 570)
				pos = 31 + (int) ((event.getSceneY() - 84) / 54);
			else
				pos = 0;
		}

		ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
		for (int i = 0; i < 40; i++) {
			CasesInterdites.add(i);
		}
		CasesInterdites.add(-1);
		for (Case t : fenetreDeJeu.getPartie().getPM().getJoueurActif().getListeSalaries()) {
			CasesInterdites.remove((Object) (t.getId()));
		}

		if (!CasesInterdites.contains(pos)) {
			fenetreDeJeu.getFenetreActionSurSalarie().afficherFenetre(pos);
		}
	}
}
