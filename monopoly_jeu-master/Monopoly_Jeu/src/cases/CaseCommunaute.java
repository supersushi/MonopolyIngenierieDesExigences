package cases;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Carte;
import jeudeplateau.Case;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Crée l'action d'une case communauté
*@author  Massourang Jugurtha Lina Emma
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communauté", 0);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communauté
	 * @see Carte
	 */
	public void fenetreAction(FenetreDeJeu fjeu) {

Console es = new Console();

		Carte carte = fjeu.getPartie().getPM().tirerCarteCommunauté();
		es.println("-> " + fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fjeu.afficherMessage(fjeu.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());

		carte.actionCarte(fjeu.getPartie().getPM().getJoueurActif(), fjeu.getPartie().getPM(), fjeu);

		if(fjeu.getPartie().PARTIE_AUTO)
			fjeu.getPartie().reprendrePartie();
		else
			fjeu.afficherFenetreCarteCommunauté(carte.getNom(), carte.getDesc());
	}



	@Override
	public JoueurMonopoly getProprietaire() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
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
	public boolean getPeutAcheterCompetence() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {}

	@Override
	public void setRep(boolean b) {}

	@Override
	public String toString() {
		return "CaseCommunaute [" + super.toString() + "]";
	}

}
