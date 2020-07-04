package controller;

import java.util.ArrayList;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.CarteModel;
import model.CaseModel;
import view.MonopolyView;

/**
 * Crée l'action d'une case chance
 * 
 * @author Massourang Jugurtha Lina Emma
 */
public class CaseChanceController extends CaseModel implements DefaultControllerInterface {

	/**
	 * Indique le nom de la case
	 */
	public CaseChanceController() {
		super("Chance", 0);
	}

	/**
	 * Méthode définissant l'action de la carte.
	 * 
	 * @param joueur  JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp      FenetreDeJeu
	 */
	@Override
	public void action(JoueurMonopoly joueur, PlateauMonopoly plateau, MonopolyView monopolyView) {
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * 
	 * @see Carte
	 */
	public void fenetreAction(MonopolyView monopolyView) {

		Clavier es = new Clavier();

		CarteModel carte = monopolyView.getPartie().getPM().tirerCarteChance();
		es.println("-> " + monopolyView.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());
		monopolyView.afficherMessage(monopolyView.getPartie().getPM().getJoueurActif().getNom() + " tire la carte " + carte.getNom());

		if (monopolyView.getPartie().PARTIE_AUTO)
			monopolyView.getPartie().reprendrePartie();
		else
			monopolyView.afficherCarteChanceView(carte.getNom(), carte.getDesc());

		monopolyView.getPartie().pausePartie();
		while (monopolyView.getPartie().getPausePartie() && !monopolyView.getPartie().PARTIE_AUTO) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		carte.action(monopolyView.getPartie().getPM().getJoueurActif(), monopolyView.getPartie().getPM(), monopolyView);
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
		return "CaseChance [" + super.toString() + "]";
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
