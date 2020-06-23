package cartes;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 *  Cette classe permet a un joueur de se voir cr�diter un certain montant pour certaines action (passage a la case d�part etc)
 * @see Carte
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
	 * M�thode d�finissant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetreDeJeu
	 */

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fp) {

		Console es = new Console();

		if(getNom().equals("Anniversaire")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstFauche()) {
					plateau.getJoueur(i).retirerArgent(10);
					joueur.ajouterArgent(10);
				}
			}
			es.println("-> "+joueur.getNom()+" re�oit 10� de chaque joueur.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" re�oit 10� de chaque joueur.");
		}

		else {
			joueur.ajouterArgent(montant);
			es.println("-> "+joueur.getNom()+" re�oit "+montant+"� de la Banque");
		}
	}

	@Override
	public String toString()
	{
		return "CarteRecevoirArgent [" + super.toString() + " montant= " + montant + "]";
	}

}
