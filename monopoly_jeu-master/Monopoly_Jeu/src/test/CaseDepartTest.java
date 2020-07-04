package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controller.CaseDepartController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseDepart
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseDepartTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseDepartController caseDep = new CaseDepartController();

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe et vérifie que le joueur1
	 * gagne 200€
	 */
	void actionCaseTest() {
		caseDep.action(joueur1, plateau, null);
		assertEquals(1200, joueur1.getArgent());
	}

}
