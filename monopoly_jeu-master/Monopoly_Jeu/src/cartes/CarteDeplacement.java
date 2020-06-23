package cartes;

import fenetres.FenetreDeJeu;
import io.Console;
import jeudeplateau.Carte;
import jeumonopoly.JoueurMonopoly;
import jeumonopoly.PlateauMonopoly;

/**
 * Type de carte pour les déplacements.
 * @param position int definit ou se positionne le joueur
 * @param deplacementRelatif boolean true si le déplacement depend de la position 
 * @see Carte
 */
public class CarteDeplacement extends Carte {
	
	
	private int position;
	private boolean deplacementRelatif;
	
	/**
	 * Constructeur 
	 * @param intitule String
	 * @param description String
	 * @param pos int
	 * @param deplacementRelatif boolean
	 */
	public CarteDeplacement(String intitule, String description, int position, boolean deplacementRelatif) {
		super(intitule, description);
		this.position = position;
		this.deplacementRelatif = deplacementRelatif;
	}

	/**
	 * Definit l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fjeu FenetreDeJeu
	 */
	@SuppressWarnings("static-access")
	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetreDeJeu fjeu) {
		
		Console es = new Console();
		/*En cas de déplacement relatif à la position actuelle
		Pour les cartes 'Reculez ou Avancez de x cases'*/
		if(deplacementRelatif) 
			plateau.deplacerJoueur(joueur, position);
		else {
			if(getNom().equals("ArretMaladie")) {
				if(joueur.getCarteSortieArretMaladie()) {
					es.println("-> " + joueur.getNom() + " utilise sa carte et évite l'arret maladie !");
					if(fjeu != null)
						fjeu.afficherMessage(joueur.getNom() + " utilise sa carte et évite l'arret maladie !");
					joueur.setCarteSortieArretMaladie(false);
					plateau.remettreCarteSortieArretMaladie();
				}
				else {
					joueur.setEstMalade(true);
					plateau.deplacerJoueur(joueur, position-joueur.getPosition());
				}
			}
			else if(position-joueur.getPosition()<0)
				plateau.deplacerJoueur(joueur, position-joueur.getPosition()+40);
			else
				plateau.deplacerJoueur(joueur, position-joueur.getPosition());
		}
		
		if(getNom().equals("Arret maladie")) {
			es.println("-> "+joueur.getNom()+" est mis en arret maladie.");
			if(fjeu != null)
				fjeu.afficherMessage(joueur.getNom()+" est mis en arret maladie.");
		}
		else {
			es.println("-> "+joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
			if(fjeu != null)
				fjeu.afficherMessage(joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			plateau.getCase(joueur.getPosition()).fenetreAction(fjeu);
			fjeu.deplacerPion(joueur);
			fjeu.getPartie().pausePartie();
			while(fjeu.getPartie().getPausePartie() && !fjeu.getPartie().PARTIE_AUTO){ try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
			plateau.getCase(joueur.getPosition()).actionCase(joueur, plateau, fjeu);
		}
		
	}
	@Override
	public String toString() {
		return "CarteDeplacement [" + super.toString() + "]";
	}

}
