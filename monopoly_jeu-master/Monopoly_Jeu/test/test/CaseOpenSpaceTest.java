package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controller.CaseOpenSpaceController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseOpenSpace
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CaseOpenSpaceTest {

	JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseOpenSpaceController caseOpenSp = new CaseOpenSpaceController();

	@Test
	/**
	 * Permet de tester la méthode action de la classe donc l'argent du joueur1
	 * augmente du prix de la case et le prix de la case redevient 0
	 */
	void actionTest() {
		caseOpenSp.setPrix(200);
		caseOpenSp.action(joueur1, plateau, null);
		assertEquals(1200, joueur1.getArgent());
		assertEquals(0, caseOpenSp.getPrix());
	}

}
