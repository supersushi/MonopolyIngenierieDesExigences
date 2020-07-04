package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CaseClientController;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CaseClient
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CaseClientTest {

	JoueurMonopoly joueur1 =  new JoueurMonopoly("Alice", 1, 1000);
	JoueurMonopoly joueur2 = new JoueurMonopoly("Marc", 2, 100);
	PlateauMonopoly plateau = new PlateauMonopoly(2);
	CaseClientController caseCli = new CaseClientController("client");
	
	@Test
	/**
	 * Permet de tester la méthode embauche Salarie quand un joueur a assez d'argent ainsi que dans le cas contraire
	 * Le salarié coûte 200€ donc le joueur1 peut l'embaucher mais pas le joueur2
	 */
	void embaucheSalarieTest() {
		assertTrue(caseCli.EmbaucheSalarie(joueur1, null));
		assertFalse(caseCli.EmbaucheSalarie(joueur2, null));
	}
	
	@Test
	/**
	 * Permet de vérifier que le montant du salaire est retiré de l'argent du joueur
	 */
	void payerSalaireTest() {
		joueur2.setNbEntreprises(1);
		caseCli.setPatron(joueur2);
		caseCli.payerSalaire(joueur1, null);
		assertEquals(950, joueur1.getArgent());
	}

}
