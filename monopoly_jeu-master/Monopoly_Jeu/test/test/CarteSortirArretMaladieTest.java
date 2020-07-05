package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controller.CarteSortirArretMaladieController;
import jeu.JoueurMonopoly;

/**
 * Teste la classe CaseSortirArretMaladie
 * 
 * @author Massourang Jugurtha Lina Emma
 */

class CarteSortirArretMaladieTest {

	JoueurMonopoly joueur = new JoueurMonopoly("Alice", 1, 1000);
	CarteSortirArretMaladieController carteSortie = new CarteSortirArretMaladieController("Sortie", "Description");

	@Test
	/**
	 * Permet de tester la méthode action de la classe
	 */
	void actionTest() {
		carteSortie.action(joueur, null, null);
		assertTrue(joueur.getCarteSortieArretMaladie());
	}

}
