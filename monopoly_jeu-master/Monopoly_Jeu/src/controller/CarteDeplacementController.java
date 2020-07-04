package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Carte;
import view.FenetreDeJeu;

/**
 * Carte de d�placement
 * 
 * @param position           int : definit ou se positionne le joueur
 * @param deplacementRelatif boolean : true si le d�placement depend de la
 *                           position
 */
public class CarteDeplacementController extends Carte implements DefaultControllerInterface {

	private int position;
	private boolean deplacementRelatif;

	/**
	 * Constructeur
	 * 
	 * @param intitule           String
	 * @param description        String
	 * @param pos                int
	 * @param deplacementRelatif boolean
	 */
	public CarteDeplacementController(String intitule, String description, int position, boolean deplacementRelatif) {
		super(intitule, description);
		this.position = position;
		this.deplacementRelatif = deplacementRelatif;
	}

	/**
	 * Definit l'action de la carte.
	 * 
	 * @param joueur       JoueurMonopoly
	 * @param plateau      PlateauMonopoly
	 * @param fenetreDeJeu FenetreDeJeu
	 */
	@SuppressWarnings("static-access")
	@Override
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fenetreDeJeu) {

		Clavier es = new Clavier();
		/*
		 * En cas de d�placement relatif � la position actuelle Pour les cartes 'Reculez
		 * ou Avancez de x cases'
		 */
		if (deplacementRelatif)
			plateau.deplacerJoueur(joueur, position);
		else {
			if (getNom().equals("ArretMaladie")) {
				if (joueur.getCarteSortieArretMaladie()) {
					es.println("-> " + joueur.getNom() + " utilise sa carte et �vite l'arret maladie !");
					if (fenetreDeJeu != null)
						fenetreDeJeu.afficherMessage(joueur.getNom() + " utilise sa carte et �vite l'arret maladie !");
					joueur.setCarteSortieArretMaladie(false);
					plateau.remettreCarteSortieArretMaladie();
				} else {
					joueur.setEstMalade(true);
					plateau.deplacerJoueur(joueur, position - joueur.getPosition());
				}
			} else if (position - joueur.getPosition() < 0) {
				plateau.deplacerJoueur(joueur, position - joueur.getPosition() + 40);
			} else {
				plateau.deplacerJoueur(joueur, position - joueur.getPosition());
			}

		}

		if (getNom().equals("Arret maladie")) {
			es.println("-> " + joueur.getNom() + " est mis en arret maladie.");
			if (fenetreDeJeu != null)
				fenetreDeJeu.afficherMessage(joueur.getNom() + " est mis en arret maladie.");
		} else {
			es.println("-> " + joueur.getNom() + " atterit sur " + plateau.getCaseActive().getNom());
			if (fenetreDeJeu != null) {
				fenetreDeJeu.afficherMessage(joueur.getNom() + " atterit sur " + plateau.getCaseActive().getNom());

				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				plateau.getCase(joueur.getPosition()).fenetreAction(fenetreDeJeu);
				fenetreDeJeu.deplacerPion(joueur);
				fenetreDeJeu.getPartie().pausePartie();
				while (fenetreDeJeu.getPartie().getPausePartie() && !fenetreDeJeu.getPartie().PARTIE_AUTO) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			plateau.getCase(joueur.getPosition()).action(joueur, plateau, fenetreDeJeu);
		}

	}

	@Override
	public String toString() {
		return "CarteDeplacement [" + super.toString() + "]";
	}

}
