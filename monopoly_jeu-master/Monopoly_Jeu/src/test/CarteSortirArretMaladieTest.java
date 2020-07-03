package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cartes.CarteSortirArretMaladie;
import jeu.JoueurMonopoly;

/**
 * Teste la classe CaseSortirArretMaladie
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CarteSortirArretMaladieTest {
	
	JoueurMonopoly joueur =  new JoueurMonopoly("Alice", 1, 1000);
	CarteSortirArretMaladie carteSortie = new CarteSortirArretMaladie("Sortie", "Description");

	@Test
	/**
	 * Permet de tester la méthode actionCarte de la classe
	 */
	void actionCarteTest() {
		carteSortie.actionCarte(joueur, null, null);
		assertTrue(joueur.getCarteSortieArretMaladie());
	}

}
