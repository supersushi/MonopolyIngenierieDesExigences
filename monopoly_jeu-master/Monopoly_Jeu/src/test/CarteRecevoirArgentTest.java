package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CarteRecevoirArgentController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseRecevoirArgent
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CarteRecevoirArgentTest {
	
	JoueurMonopoly joueur =  new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(3);
	CarteRecevoirArgentController carteArgent;

	@Test
	/**
	 * Permet de tester la méthode actionCarte de la classe
	 * dans le cas où la carte est Anniversaire
	 * donc tous les joueurs donnent 20€ au joueur (0 ici) qui a tiré la carte
	 */
	void actionCarteAnniversaireTest() {
		carteArgent = new CarteRecevoirArgentController("Anniversaire", "description", 50);
		carteArgent.actionCarte(plateau.getJoueur(0), plateau, null);
		assertEquals(1040, plateau.getJoueur(0).getArgent());
		assertEquals(980, plateau.getJoueur(1).getArgent());
		assertEquals(980, plateau.getJoueur(2).getArgent());
	}
	
	@Test
	/**
	 * Permet de tester la méthide actionCarte de la classe
	 * donc l'argent du joueur augmente du prix de la carte
	 */
	void actionCarteTest() {
		carteArgent = new CarteRecevoirArgentController("Recevoir argent", "description", 60);
		carteArgent.actionCarte(joueur, null, null);
		assertEquals(1060, joueur.getArgent());
	}

}
