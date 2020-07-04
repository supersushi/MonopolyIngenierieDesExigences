package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Carte;
import view.MonopolyView;

/**
 * Carte permettant d'�tre gu�ri, et donc de ne plus �tre en arr�t maladie.
 * Carte conserv�e par le joueur.
 */
public class CarteSortirArretMaladieController extends Carte implements DefaultControllerInterface {

	/**
	 * Constructeur
	 * 
	 * @param titre       String
	 * @param description String
	 */
	public CarteSortirArretMaladieController(String titre, String description) {
		super(titre, description);
	}

	/**
	 * M�thode d�finissant l'action de la carte.
	 * 
	 * @param joueur  JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param monopolyView      FenetreDeJeu
	 */
	@Override
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();
		es.println("-> " + joueur.getNom() + " recoit la carte 'Sortir d arret maladie' !");
		if (monopolyView != null)
			monopolyView.afficherMessage(joueur.getNom() + " recoit la carte 'Sortir d arret maladie' !");

		joueur.setCarteSortieArretMaladie(true);
	}

	@Override
	public String toString() {
		return "CarteSortirArretMaladie [" + super.toString() + "]";
	}
}
