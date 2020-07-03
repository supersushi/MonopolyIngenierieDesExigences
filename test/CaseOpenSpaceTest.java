package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cases.CaseOpenSpace;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseOpenSpace
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CaseOpenSpaceTest {
	
	JoueurMonopoly joueur1 =  new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseOpenSpace caseOpenSp = new CaseOpenSpace();

	@Test
	/**
	 * Permet de tester la méthode actionCase de la classe
	 * donc l'argent du joueur1 augmente du prix de la case et le prix de la case redevient 0
	 */
	void actionCaseTest() {
		caseOpenSp.setPrix(200);
		caseOpenSp.actionCase(joueur1, plateau, null);
		assertEquals(1200, joueur1.getArgent());
		assertEquals(0, caseOpenSp.getPrix());
	}

}
