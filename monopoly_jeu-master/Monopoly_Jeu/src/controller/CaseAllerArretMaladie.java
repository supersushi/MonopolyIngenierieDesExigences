package controller;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import model.Case;
import view.FenetreDeJeu;

/**
 * Crée l'action pour aller en arret maladie
*@author Massourang Jugurtha Lina Emma
*@see Case
*/
public class CaseAllerArretMaladie extends Case {

	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case
	 */
	public CaseAllerArretMaladie() {
		super("Aller en arret maladie", 0);
	}

	/**
	 * Méthode permettant de gérer l'action quand le joueur tombe sur la case AllerArretMaladie
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Clavier es = new Clavier();

		if(joueur.getCarteSortieArretMaladie()) {
			es.println(" -> " + joueur.getNom() + " utilise sa carte et évite l arret maladie !");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " utilise sa carte et évite l arret maladie !");
			joueur.setCarteSortieArretMaladie(false);
			plateau.remettreCarteSortieArretMaladie();
		}
		else {
			joueur.setEstMalade(true);
			joueur.setPosition(10);
			es.println("-> " + joueur.getNom() + " est mis en arret maladie!");
			if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " est mis en arret maladie!");
		}
	}

	@Override
	/**
	 * Cette méthode permet de reprendre une partie en cours
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {
		fjeu.getPartie().reprendrePartie();
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
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMonterEnCompetence() {
		return false;
	}

	@Override
	public void setPatron(JoueurMonopoly j) {}

	@Override
	public void setRep(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "CaseAllerArretMaladie [" + super.toString() + "]";
	}

	@Override
	public String descriptionPoste() {
		// TODO Auto-generated method stub
		return null;
	}

}
