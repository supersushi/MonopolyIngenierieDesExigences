package cases;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case départ
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
	 * Crédite le joueur de 200 lors de son passage par la case départ 
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Console es = new Console();

		joueur.ajouterArgent(200);
		es.println("-> " + joueur.getNom() + " s'arrête sur la case départ et reçoit une prime de 200€  !");
		if(fjeu!=null) fjeu.afficherMessage(joueur.getNom() + " s'arrête sur la case départ et reçoit une prime de 200€ !");
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
	public static void main(String[] args){

		System.out.println("TEST DE LA CLASSE : CaseDepart \n");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);

		CaseDepart c = (CaseDepart) p.getCase(0);

		j.setPosition(38);
		System.out.println("\n Le joueur est sur la case " + p.getCase(j.getPosition()).toString()+" \n");
		p.deplacerJoueur(j, 2);
		c.actionCase(j, p, null);
		System.out.println("Le joueur possède : " + j.getArgent()+"€ \n");

		j.setPosition(38);
		System.out.println("\n Le joueur est sur la case " + p.getCase(j.getPosition()).toString() + " \n");
		p.deplacerJoueur(j, 3);
		System.out.println("\n Le joueur est sur la case " + p.getCase(j.getPosition()).getNom()+" \n");
		System.out.println("Le joueur possède : " + j.getArgent()+"€ \n");
	}
}
