package cartes;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Carte permettant d'être guéri, et donc de ne plus être en arrêt maladie. 
 * Carte conservée par le joueur.
 * @see Carte
 */
public class CarteSortirArretMaladie extends Carte {

	/**
	 * Constructeur 
	 * @param titre String
	 * @param description String
	 */
	public CarteSortirArretMaladie(String titre, String description) {
		super(titre, description);
	}

	/**
	 * Méthode définissant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetreDeJeu
	 */
	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fp) {
		
		Console es = new Console();
		es.println("-> "+joueur.getNom()+" recoit la carte 'Sortir d arret maladie' !");
		if(fp != null)
			fp.afficherMessage(joueur.getNom()+" recoit la carte 'Sortir d arret maladie' !");
		
		joueur.setCarteSortieArretMaladie(true);
	}
	@Override
	public String toString() {
		return "CarteSortirArretMaladie [" + super.toString() +"]";
	}
}
