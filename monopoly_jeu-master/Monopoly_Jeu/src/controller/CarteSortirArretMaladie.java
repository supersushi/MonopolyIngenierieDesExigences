package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Carte;
import view.FenetreDeJeu;

/**
 * Carte permettant d'�tre gu�ri, et donc de ne plus �tre en arr�t maladie. 
 * Carte conserv�e par le joueur.
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
		
		Clavier es = new Clavier();
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
