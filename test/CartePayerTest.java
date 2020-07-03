package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cartes.CartePayer;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe CasePayer
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class CartePayerTest {

	JoueurMonopoly joueur =  new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(3);
	CartePayer cartePayer;
	
	@Test
	/**
	 * Permet de tester la méthode carteAction de la classe
	 * dans le cas où la carte est celle du président du conseil
	 * donc le joueur 0 donne 10€ à tous les autres joueurs
	 */
	void carteActionPresidentTest() {
		cartePayer = new CartePayer("Président du conseil d'administration", "description", 10);
		cartePayer.actionCarte(plateau.getJoueur(0), plateau, null);
		assertEquals(980, plateau.getJoueur(0).getArgent());
		assertEquals(1010, plateau.getJoueur(1).getArgent());
		assertEquals(1010, plateau.getJoueur(2).getArgent());
	}
	
	@Test
	/**
	 * Permet de tester la méthode carteAction de la classe
	 * donc l'argent du joueur baisse du prix de la carte et le prix dans la case OpenSpace augmente du prix de la carte
	 */
	void carteActionTest() {
		cartePayer = new CartePayer("Carte Payer", "description", 30);
		cartePayer.actionCarte(joueur, plateau, null);
		assertEquals(970, joueur.getArgent());
		assertEquals(30, plateau.getCase(20).getPrix());
	}

}
