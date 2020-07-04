package controller;

import java.util.ArrayList;
import java.util.Random;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.MonopolyView;

/**
 * Crée l'action de la case Arret Maladie
 * 
 * @author Massourang Jugurtha Lina Emma
 */
public class CaseArretMaladieController extends Case implements DefaultControllerInterface {

	private boolean rep = false;

	/**
	 * Indique le nom de la case
	 */
	public CaseArretMaladieController() {
		super("Visite de controle", 0);
	}

	/**
	 * Méthode gérant tous les cas d'un joueur en arret maladie : - Si un joueur est
	 * resté 3 tours en arret maladie, il doit payer 50€ - Si un joueur fait un
	 * double au lancé de dés, il est guéri - Si un joueur possède une carte 'Sortir
	 * d'arret maladie' et qu'il l'utilise, il est guéri
	 * 
	 * @see Case
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		int lancé = plateau.des.lancerDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();

		if (joueur.getEstMalade() == true) {

			if (monopolyView != null)
				monopolyView.afficherDes(plateau);

			es.println("Voulez vous payer 25€ pour etre soigné ? ");

			if (getRep()) {
				es.println("OUI : " + joueur.getNom() + " décide de payer 25€ pour être soigné.");
				joueur.retirerArgent(25);
				rep = false;
				joueur.setEstMalade(false);
				joueur.setToursEnArretMaladie(1);
				plateau.deplacerJoueur(joueur, lancé);
				es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un " + lancé
						+ " !");
				es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur "
						+ plateau.getCaseActive().getNom());
				if (monopolyView != null)
					actionSortieArretMaladie(plateau, joueur, monopolyView);
			} else {
				if (joueur.getToursEnArretMaladie() > 2) {
					es.println("NON : " + joueur.getNom()
							+ " est a son 3e tour en arret maladie, \nil est guéri et paye 25€.");
					joueur.retirerArgent(25);
					joueur.setEstMalade(false);
					joueur.setToursEnArretMaladie(1);
					plateau.deplacerJoueur(joueur, lancé);
					es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un "
							+ lancé + " !");
					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur "
							+ plateau.getCaseActive().getNom());
					if (monopolyView != null)
						actionSortieArretMaladie(plateau, joueur, monopolyView);
				} else {
					// es.println("NON : " + joueur.getNom() + " (tour " +
					// joueur.getToursEnArretMaladie() + ") décide de ne pas payer et lance ses
					// dés...");
					if (d1 == d2) {
						es.println("  [" + d1 + "][" + d2 + "] Gagné! " + joueur.getNom() + " est soigné sans payer!");
						joueur.setEstMalade(false);
						joueur.setToursEnArretMaladie(1);
						plateau.deplacerJoueur(joueur, lancé);
						es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur "
								+ plateau.getCaseActive().getNom());
						if (monopolyView != null)
							actionSortieArretMaladie(plateau, joueur, monopolyView);
					} else {
						es.println("  [" + d1 + "][" + d2 + "] Perdu!");
						joueur.setToursEnArretMaladie(joueur.getToursEnArretMaladie() + 1);
					}
				}
			}
		} else {
			es.println("- Le joueur fait une simple visite au médecin du travail -");
			if (monopolyView != null)
				monopolyView.afficherMessage("- Le joueur fait une simple visite au médecin du travail -");
		}

	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(MonopolyView monopolyView) {

		if (monopolyView.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if (rand.nextBoolean())
				rep = true;
			monopolyView.getPartie().reprendrePartie();
		} else if (monopolyView.getPartie().getPM().getJoueurActif().getEstMalade())
			monopolyView.afficherFenetreArretMaladie();
		else
			monopolyView.getPartie().reprendrePartie();
	}

	@SuppressWarnings("static-access")
	public void actionSortieArretMaladie(PlateauMonopoly plateau, JoueurMonopoly joueur, MonopolyView monopolyView) {

		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		plateau.getCase(joueur.getPosition()).fenetreAction(monopolyView);
		monopolyView.deplacerPion(joueur);
		monopolyView.getPartie().pausePartie();
		while (monopolyView.getPartie().getPausePartie() && !monopolyView.getPartie().PARTIE_AUTO) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		plateau.getCase(joueur.getPosition()).action(joueur, plateau, monopolyView);
	}

	@Override
	public JoueurMonopoly getPatron() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getSalaire() {
		return 0;
	}

	@Override
	public int getPrixCompetence() {
		return 0;
	}

	@Override
	public int getNbCompetence() {
		return 0;
	}

	@Override
	public boolean getRep() {
		return rep;
	}

	@Override
	public boolean getPeutMonterEnCompetence() {
		return false;
	}

	@Override
	public void setPatron(JoueurMonopoly j) {
	}

	@Override
	public void setRep(boolean rep) {
		this.rep = rep;
	}

	@Override
	public String toString() {
		return "CaseArretMaladie [ " + super.toString() + ", rep=" + rep + "]";
	}

	@Override
	public String descriptionPoste() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getCompetences() {
		// TODO Auto-generated method stub
		return null;
	}

}