package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Carte;
import model.Case;
import view.FenetreDeJeu;

/**
 * Cr�e l'action d'une case communaut�
*@author  Massourang Jugurtha Lina Emma
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communaut�", 0);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communaut�
	 * @see Carte
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

Clavier es = new Clavier();

		Carte carte = fjeu.getPartie().getPM().tirerCarteCommunaut�();
		es.println("-> " + fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());
		fjeu.afficherMessage(fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());

		carte.actionCarte(fjeu.getPartie().getPM().getJoueurActif(), fjeu.getPartie().getPM(), fjeu);

		if(fjeu.getPartie().PARTIE_AUTO)
			fjeu.getPartie().reprendrePartie();
		else
			fjeu.afficherFenetreCarteCommunaut�(carte.getNom(), carte.getDesc());
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
		return false;
	}

	@Override
	public boolean getPeutMonterEnCompetence() {
		return false;
	}

	@Override
	public void setPatron(JoueurMonopoly j) {}

	@Override
	public void setRep(boolean b) {}

	@Override
	public String toString() {
		return "CaseCommunaute [" + super.toString() + "]";
	}

	@Override
	public String descriptionPoste() {
		// TODO Auto-generated method stub
		return null;
	}

}
