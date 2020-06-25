package cases;

import jeudeplateau.Carte;
import cartes.CartePayer;
import cartes.CarteSortirArretMaladie;
import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case chance
*@author  Massourang Jugurtha Lina Emma
*/
public class CaseChance extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseChance() {
		super("Chance", 0);
	}
	
	/**
	 * Méthode définissant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetreDeJeu
	 */
	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * @see Carte
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

		Console es = new Console();

		Carte carte = fjeu.getPartie().getPM().tirerCarteChance();
		es.println("-> " + fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fjeu.afficherMessage(fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());

		if(fjeu.getPartie().PARTIE_AUTO)
			fjeu.getPartie().reprendrePartie();
		else
			fjeu.afficherFenetreCarteChance(carte.getNom(), carte.getDesc());

		fjeu.getPartie().pausePartie();
		while(fjeu.getPartie().getPausePartie() && !fjeu.getPartie().PARTIE_AUTO){
			try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }

		carte.actionCarte(fjeu.getPartie().getPM().getJoueurActif(), fjeu.getPartie().getPM(), fjeu);
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
		return "CaseChance [" + super.toString() + "]";
	}


	public static void main(String[] args) {

		System.out.println("TEST DE LA CLASSE : CaseChance");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);
		CartePayer payer = new CartePayer("Amende", "Amende pour excès de vitesse : 15€.", 15);
		System.out.println(payer.toString());
		payer.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann perd 15€
		System.out.println(p.getCase(20).toString());

		CarteSortirArretMaladie arretMal = new CarteSortirArretMaladie("Guerir", "Vous êtes gueri. \n(Cette carte doit être conservée)");
		System.out.println(arretMal.toString());
		arretMal.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann possède la carte de sortie d arret maladie
	}
}
