package cartes;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Carte permettant d'�tre gu�ri, et donc de ne plus �tre en arr�t maladie. 
 * Carte conserv�e par le joueur.
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
	 * M�thode d�finissant l'action de la carte. 
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
