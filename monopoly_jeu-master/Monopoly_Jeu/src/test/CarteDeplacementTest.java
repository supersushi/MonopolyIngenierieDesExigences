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
	 * Permet de tester la méthode actionCarte de la classe dans le cas où la carte
	 * a un déplacement relatif et où le déplacement amène le joueur1 à passer par
	 * la case départ donc le joueur1 change de position et gagne 200€
	 */
	void actionCarteDepRelatifTest() {
		carteDep = new CarteDeplacementController("déplacement", "relatif", 23, true);
		joueur1.setPosition(24);
		carteDep.action(joueur1, plateau, null);
		assertEquals(7, joueur1.getPosition());
		assertEquals(1200, joueur1.getArgent());
	}

	@Test
	/**
	 * Permet de tester la méthode actionCarte de la classe dans le cas où la carte
	 * est un arrêt maladie et où le joueur1 possède une carte de sortie d'arrêt
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
	 * Permet de tester la méthode actionCarte de la classe dans le cas où la carte
	 * est un arrêt maladie mais où le joueur1 ne possède pas de carte de sortie
	 * d'arrêt maladie donc le joueur tombe malade et se déplace sur la case de la
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
