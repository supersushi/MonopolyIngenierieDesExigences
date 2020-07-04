package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.MonopolyView;

/**
 * Cr�e l'action d'une case d�part
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseDepartController extends Case implements DefaultControllerInterface {

	/**
	 * Indique le nom de la case
	 */
	public CaseDepartController() {
		super("Depart", 0);
	}

	/**
	 * Cr�dite le joueur de 200 lors de son passage par la case d�part
	 * 
	 * @see Case
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		joueur.ajouterArgent(200);
		es.println("-> " + joueur.getNom() + " s'arr�te sur la case d�part et re�oit une prime de 200�  !");
		if (monopolyView != null)
			monopolyView.afficherMessage(joueur.getNom() + " s'arr�te sur la case d�part et re�oit une prime de 200� !");
	}

	@Override
	/**
	 * Reprend la partie
	 */
	public void fenetreAction(MonopolyView monopolyView) {
		monopolyView.getPartie().reprendrePartie();
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
	public void setPatron(JoueurMonopoly j) {
	}

	@Override
	public void setRep(boolean b) {
	}

	@Override
	public String toString() {
		return "CaseDepart [" + super.toString() + "]";
	}

	@Override
	public String descriptionPoste() {
		// TODO Auto-generated method stub
		return null;
	}

}
