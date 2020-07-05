package jeu;

import java.util.ArrayList;

import application.Clavier;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.CaseModel;
import view.MonopolyView;

/**
 * Lance la partie
 * 
 * @author Massourang Jugurtha Lina Emma
 */

public class Partie {

	private PlateauMonopoly pm;
	private MonopolyView monopolyView;
	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;

	/* CONSTRUCTEUR PARTIE */

	/**
	 * Créé une partie en fonction du nombre de joueurs
	 * 
	 * @param nombreDeJoueurs int
	 * @param monopolyView    MonopolyView
	 */
	public Partie(int nombreDeJoueurs, ArrayList<String> nomsDesJoueurs, MonopolyView monopolyView) {
		this.pm = new PlateauMonopoly(nombreDeJoueurs);
		this.monopolyView = monopolyView;

		for (int i = 0; i < nombreDeJoueurs; i++) {
			pm.getJoueur(i).setNom(nomsDesJoueurs.get(i));
		}
	}

	/**
	 * Demarre la partie
	 */
	public void demarrerLaPartie() {

		final Service<Void> partieService = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						Clavier es = new Clavier();
						es.println("La partie va commencer!");

						JoueurMonopoly joueur;
						int lancé;
						CaseModel cells;

						while (!pm.finPartie() && pm.getNbTours() <= 100) {

							joueur = pm.getJoueurActif();

							if (pm.getJoueurActifID() == 0)
								es.println("- Debut du tour " + pm.getNbTours() + " -");

							es.println("C'est au joueur: " + joueur.getNom() + ". Il a " + joueur.getArgent() + "€");
							monopolyView.afficherMessage(
									"C'est à: " + joueur.getNom() + ". Il a " + joueur.getArgent() + "€");

							if (!joueur.getEstFauche()) {
								Thread.sleep(VITESSE_PARTIE);

								lancé = pm.des.lancerDes();

								if (!joueur.getEstMalade()) {

									monopolyView.afficherDes(pm);
									es.println("" + joueur.getNom() + " lance les Des... [" + pm.des.getDe1() + "]["
											+ pm.des.getDe2() + "]... et obtient un " + lancé + " !");
									pm.deplacerJoueur(joueur, lancé);
									monopolyView.deplacerPion(joueur);

									cells = pm.getCase(joueur.getPosition());
									es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur "
											+ cells.getNom());
								} else {
									es.println("Le joueur est en arret maladie.");

									cells = pm.getCase(joueur.getPosition());
								}
								Thread.sleep(VITESSE_PARTIE);

								pausePartie = true;
								cells.actionView(monopolyView);

								while (pausePartie && !PARTIE_AUTO) {
									Thread.sleep(200);
								}

								cells.action(joueur, pm, monopolyView);

								es.println(
										"" + joueur.getNom() + " possède à la fin du tour " + joueur.getArgent() + "€");
								System.out.println("et les salariés :\n" + joueur.getListeStringSalaries());
							} else {
								es.println("" + pm.getJoueurActif().getNom() + " est fauché, il ne joue pas.");
							}

							Thread.sleep(400);
							monopolyView.deplacerPion(joueur);
							monopolyView.refreshLabels(pm);

							pausePartie = !joueur.getEstFauche();
							while (pausePartie && !PARTIE_AUTO) {
								Thread.sleep(200);
							}

							es.println("");
							monopolyView.effacerDes();
							pm.setJoueurSuivant();

						}

						es.println("- La partie est terminée -");
						es.println(" Le gagnant est " + pm.estVainqueur().getNom() + " !");

						monopolyView.afficherVainqueur(pm);

						return null;
					}
				};
			}
		};
		partieService.start();
	}

	/**
	 * Renvoie le plateau du Monopoly
	 * 
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

	public void pausePartie() {
		this.pausePartie = true;
	}

	public boolean getPausePartie() {
		return this.pausePartie;
	}

	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}

}
