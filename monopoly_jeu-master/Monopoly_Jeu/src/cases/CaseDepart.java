package cases;

import application.Clavier;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;
import view.FenetreDeJeu;

/**
 * Cr�e l'action d'une case d�part
*@author  Massourang Jugurtha Lina Emma
*/

public class CaseDepart extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseDepart() {
		super("Depart", 0);
	}

	/**
	 * Cr�dite le joueur de 200 lors de son passage par la case d�part 
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Clavier es = new Clavier();

		joueur.ajouterArgent(200);
		es.println("-> " + joueur.getNom() + " s'arr�te sur la case d�part et re�oit une prime de 200�  !");
		if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " s'arr�te sur la case d�part et re�oit une prime de 200� !");
	}


	@Override
	/**
	 * Reprend la partie
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

	@Override
	public String toString() {
		return "CaseDepart ["+super.toString()+"]";
	}
	
}
