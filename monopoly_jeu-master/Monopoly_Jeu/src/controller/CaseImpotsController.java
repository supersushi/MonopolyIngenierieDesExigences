package controller;

import java.util.ArrayList;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.CaseModel;
import view.MonopolyView;

/**
 * Cr�e l'action d'une case imp�t ou d'une taxe de luxe
 * 
 * @author Massourang Jugurtha Lina Emma
 */
public class CaseImpotsController extends CaseModel implements DefaultControllerInterface {

	/**
	 * @param nom    String : Intitul� de la case
	 * @param valeur int : prix de l'impot � payer
	 */
	public CaseImpotsController(String nom, int valeur) {
		super(nom, valeur);
	}

	/**
	 * Cette m�thode d�bite le joueur et cr�dite l'Open Space
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		es.println(" -> " + joueur.getNom() + " cr�dite de " + this.getPrix() + "� l'Open Space.");
		if (monopolyView != null)
			monopolyView.afficherMessage(joueur.getNom() + " cr�dite de " + this.getPrix() + "� l'Open Space.");

		joueur.retirerArgent(this.getPrix());

		int nouveauMontantOpenSpace = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantOpenSpace);
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void actionView(MonopolyView monopolyView) {
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
