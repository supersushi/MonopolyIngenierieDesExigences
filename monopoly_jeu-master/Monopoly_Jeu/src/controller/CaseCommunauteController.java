package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Carte;
import model.Case;
import view.MonopolyView;

/**
 * Cr�e l'action d'une case communaut�
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class CaseCommunauteController extends Case implements DefaultControllerInterface {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunauteController() {
		super("Communaut�", 0);
	}

	@Override
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communaut�
	 * 
	 * @see Carte
	 */
	public void fenetreAction(MonopolyView monopolyView) {

		Clavier es = new Clavier();

		Carte carte = monopolyView.getPartie().getPM().tirerCarteCommunaut�();
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

}
