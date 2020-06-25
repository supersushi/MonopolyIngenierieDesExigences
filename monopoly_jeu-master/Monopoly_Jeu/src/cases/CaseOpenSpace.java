package cases;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action de la case Open Space
*@author   Massourang Jugurtha Lina Emma
*/
public class CaseOpenSpace extends Case {

	/**
	 * Indique le nom de la case et initialise sa valeur
	 */
	public CaseOpenSpace() {
		super("Open Space", 0);
	}

	/**
	 * M�thode permettant � un joueur de r�cup�rer l'argent de la case Open Space puis r�initialisation  � 0
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {

		Console es = new Console();

		es.println(" -> " + joueur.getNom() + " trouve " + this.getPrix() + "� dans le Open Space !");
		if(fjeu!=null)
			fjeu.afficherMessage(joueur.getNom() + " trouve " + this.getPrix() + "� dans le Open Space !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}

	public static void main(String[] args){

		System.out.println("TEST DE LA CLASSE : CaseParcGratuit");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);

		CaseOpenSpace c = (CaseOpenSpace) p.getCase(20);

		c.setPrix(100);
		System.out.println("Initialisation de la case Open Space � 100� : "+ c.toString());
		System.out.println("Joueur avant le Open Space : "+ j.toString());
		j.setPosition(20);
		c.actionCase(j, p, null);

		System.out.println("Case Open Space apr�s le passage du joueur : " + c.toString());
		System.out.println("Joueur apr�s le Open Space : " + j.toString());
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

	@Override
	public String toString() {
		return "CaseOpenSpace [" + super.toString() + "]";
	}

}
