package cases;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 * Crée l'action d'une case impôt ou d'une taxe de luxe
*@author  Massourang Jugurtha Lina Emma
*/
public class CaseImpots extends Case {

	/**
	 * @param nom String : Intitulé de la case
	 * @param valeur int : prix de l'impot à payer
	 */
	public CaseImpots(String nom, int valeur) {
		super(nom, valeur);
	}

	/**
	 * Cette méthode débite le joueur et crédite l'Open Space
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Clavier es = new Clavier();

		es.println(" -> " + joueur.getNom() + " crédite de " + this.getPrix() + "€ l'Open Space.");
		if(fjeu != null)
			fjeu.afficherMessage(joueur.getNom() + " crédite de " + this.getPrix() + "€ l'Open Space.");

		joueur.retirerArgent(this.getPrix());

		int nouveauMontantOpenSpace = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantOpenSpace);
	}

	
	@Override
	/**
	 * Reprend le cours de la partie
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
	
	

}
