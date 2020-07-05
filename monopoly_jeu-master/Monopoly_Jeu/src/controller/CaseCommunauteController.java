package controller;

import java.util.ArrayList;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.CarteModel;
import model.CaseModel;
import view.MonopolyView;

/**
 * Crée l'action d'une case communauté
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseCommunauteController extends CaseModel implements DefaultControllerInterface {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunauteController() {
		super("Communauté", 0);
	}

	@Override
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communauté
	 * 
	 * @see Carte
	 */
	public void actionView(MonopolyView monopolyView) {

		Clavier es = new Clavier();

		CarteModel carte = monopolyView.getPartie().getPM().tirerCarteCommunauté();
		es.println("-> " + monopolyView.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());
		monopolyView.afficherMessage(monopolyView.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());

		carte.action(monopolyView.getPartie().getPM().getJoueurActif(), monopolyView.getPartie().getPM(), monopolyView);

		if (monopolyView.getPartie().PARTIE_AUTO)
			monopolyView.getPartie().reprendrePartie();
		else
			monopolyView.afficherCarteCommunauteView(carte.getNom(), carte.getDesc());
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
		return "CaseCommunaute [" + super.toString() + "]";
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
