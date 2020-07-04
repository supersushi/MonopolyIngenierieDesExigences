package controller;

import java.util.ArrayList;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.MonopolyView;

/**
 * Crée l'action de la case Open Space
 * 
 * @author Massourang Jugurtha Lina Emma
 */
public class CaseOpenSpaceController extends Case implements DefaultControllerInterface {

	/**
	 * Indique le nom de la case et initialise sa valeur
	 */
	public CaseOpenSpaceController() {
		super("Open Space", 0);
	}

	/**
	 * Méthode permettant à un joueur de récupérer l'argent dans case Open Space
	 * puis réinitialisation à 0
	 */
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {

		Clavier es = new Clavier();

		es.println(" -> " + joueur.getNom() + " trouve " + this.getPrix() + "€ dans l'Open Space !");
		if (monopolyView != null)
			monopolyView.afficherMessage(joueur.getNom() + " trouve " + this.getPrix() + "€ dans l'Open Space !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}

	@Override
	/**
	 * Reprend le cours de la partie
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
		return "CaseOpenSpace [" + super.toString() + "]";
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
