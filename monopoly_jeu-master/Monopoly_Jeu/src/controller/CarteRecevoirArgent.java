package cartes;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 *  Cette classe permet a un joueur de se voir créditer un certain montant pour certaines action (passage a la case départ etc)
 */
public class CarteRecevoirArgent extends Carte {

	private int montant;

	/**
	 * Constructeur
	 * @param titre String
	 * @param description String
	 * @param montant int
	 *
	 */

	public CarteRecevoirArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	/**
	 * Méthode définissant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetreDeJeu
	 */

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fp) {

		Clavier es = new Clavier();

		if(getNom().equals("Anniversaire")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstFauche()) {
					plateau.getJoueur(i).retirerArgent(20);
					joueur.ajouterArgent(20);
				}
			}
			es.println("-> "+joueur.getNom()+" reçoit 20€ de chaque joueur.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" reçoit 20€ de chaque joueur.");
		}

		else {
			joueur.ajouterArgent(montant);
			es.println("-> "+joueur.getNom()+" reçoit "+montant+"€ de la Banque");
		}
	}

	@Override
	public String toString()
	{
		return "CarteRecevoirArgent [" + super.toString() + " montant= " + montant + "]";
	}

}
