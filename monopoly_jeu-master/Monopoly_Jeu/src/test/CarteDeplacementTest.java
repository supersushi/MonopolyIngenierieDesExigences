package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controller.CarteDeplacementController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseDeplacement
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CarteDeplacementTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CarteDeplacementController carteDep;

	@Test
	/**
	 * Permet de tester la m�thode actionCarte de la classe dans le cas o� la carte
	 * a un d�placement relatif et o� le d�placement am�ne le joueur1 � passer par
	 * la case d�part donc le joueur1 change de position et gagne 200�
	 */
	void actionCarteDepRelatifTest() {
		carteDep = new CarteDeplacementController("d�placement", "relatif", 23, true);
		joueur1.setPosition(24);
		carteDep.action(joueur1, plateau, null);
		assertEquals(7, joueur1.getPosition());
		assertEquals(1200, joueur1.getArgent());
	}

	@Test
	/**
	 * Permet de tester la m�thode actionCarte de la classe dans le cas o� la carte
	 * est un arr�t maladie et o� le joueur1 poss�de une carte de sortie d'arr�t
	 * maladie donc le joueur1 perd sa carte de sortie maladie
	 */
	void actionCarteDepArretMalTest() {
		carteDep = new CarteDeplacementController("ArretMaladie", "non relatif", 13, false);
		joueur1.setCarteSortieArretMaladie(true);
		carteDep.action(joueur1, plateau, null);
		assertFalse(joueur1.getCarteSortieArretMaladie());
	}

	@Test
	/**
	 * Permet de tester la m�thode actionCarte de la classe dans le cas o� la carte
	 * est un arr�t maladie mais o� le joueur1 ne poss�de pas de carte de sortie
	 * d'arr�t maladie donc le joueur tombe malade et se d�place sur la case de la
	 * carte
	 */
	void actionCarteMaladeTest() {
		carteDep = new CarteDeplacementController("ArretMaladie", "non relatif", 13, false);
		joueur1.setPosition(3);
		carteDep.action(joueur1, plateau, null);
		assertTrue(joueur1.getEstMalade());
		assertEquals(13, joueur1.getPosition());
	}

}
