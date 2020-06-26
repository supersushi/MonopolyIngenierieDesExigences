package jeumonopoly;

import java.util.ArrayList;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Case;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Lance la partie
*@author  Massourang Jugurtha Lina Emma
*/

public class Partie {

	/**
	 * @see PlateauMonopoly
	 */
	private PlateauMonopoly pm;

	/**
	 * @see FenetreDeJeu
	 */
	private FenetreDeJeu fjeu;

	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;

	/* CONSTRUCTEUR PARTIE */

	/**
	 * Crée une partie en fonction du nombre de joueurs
	 * @param nombreDeJoueurs int
	 * @param fjeu FenetreDeJeu
	 */
	public Partie(int nombreDeJoueurs, ArrayList<String> nomsDesJoueurs, FenetreDeJeu fjeu) {
		this.pm = new PlateauMonopoly(nombreDeJoueurs);
		this.fjeu = fjeu;

		for(int i=0; i<nombreDeJoueurs; i++) {
			pm.getJoueur(i).setNom(nomsDesJoueurs.get(i));
		}
	}

	/**
	 * Demarre la partie
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void demarrerLaPartie() {

		final Service<Void> partieService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                    	Console es = new Console();
                    	es.println("La partie va commencer!");

                		JoueurMonopoly joueur;
                		int lancé;
                		Case cells;

                		while(!pm.finPartie() && pm.getNbTours() <= 100) {

                			joueur = pm.getJoueurActif();

                			if(pm.getJoueurActifID() == 0)
                				es.println("- Debut du tour " + pm.getNbTours() + " -");

                			es.println("C'est au joueur: " + joueur.getNom() + ". Il a " + joueur.getArgent() + "€");
                			fjeu.afficherMessage("C'est à: " + joueur.getNom() + ". Il a " + joueur.getArgent() + "€");

                			if(!joueur.getEstFauche()) {
                				Thread.sleep(VITESSE_PARTIE);

                				lancé = pm.des.lancerDes();

                				if(!joueur.getEstMalade()) {

                    				fjeu.afficherDes(pm);
                					es.println("" + joueur.getNom() + " lance les Des... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + lancé + " !");
                					pm.deplacerJoueur(joueur, lancé);
                					fjeu.deplacerPion(joueur);

                					cells = pm.getCase(joueur.getPosition());
                					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + cells.getNom());
                				}
                				else {
                					es.println("Le joueur est en arret maladie.");

                					cells = pm.getCase(joueur.getPosition());
                				}
            					Thread.sleep(VITESSE_PARTIE);

                				pausePartie = true;
            					cells.fenetreAction(fjeu);

                    			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                				cells.actionCase(joueur, pm, fjeu);

                				es.println("" + joueur.getNom() + " possède à la fin du tour " + joueur.getArgent() + "€");
                				System.out.println("et les salariés :\n" + joueur.getListeStringSalaries());
                			}
                			else {
                				es.println("" + pm.getJoueurActif().getNom() + " est fauché, il ne joue pas.");
                			}

                			Thread.sleep(400);
                			fjeu.deplacerPion(joueur);
                			fjeu.refreshLabels(pm);

                			pausePartie = !joueur.getEstFauche();
                			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                			es.println("");
                			fjeu.effacerDes();
                			pm.setJoueurSuivant();

                		}

                		es.println("- La partie est terminée -");
                		es.println(" Le gagnant est " + pm.estVainqueur().getNom() + " !");

                		fjeu.afficherVainqueur(pm);

                		return null;
                    }
                };
            }
        };
        partieService.start();
	}

	/**
	 * Renvoie le plateau du Monopoly
	 * @return pm
	 */
	public PlateauMonopoly getPM() {
		return this.pm;
	}

	/**
	 * Permet de reprendre le cours de la partie
	 */
	public void reprendrePartie() {
		this.pausePartie = false;
	}
	public void pausePartie(){
		this.pausePartie = true;
	}
	public boolean getPausePartie(){
		return this.pausePartie;
	}

	/**
	 * Il est là car il le doit c'est tout
	 */
	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}

}
