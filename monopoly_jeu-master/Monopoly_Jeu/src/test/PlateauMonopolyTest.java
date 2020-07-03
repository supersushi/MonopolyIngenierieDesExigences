package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CarteSortirArretMaladie;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;

/**
 * Teste la classe PlateauMonopoly
 * 
 * @author  Massourang Jugurtha Lina Emma
 */

class PlateauMonopolyTest {

	JoueurMonopoly joueur =  new JoueurMonopoly("Alice", 1, 1000);
	PlateauMonopoly plateau = new PlateauMonopoly(3);
	CarteSortirArretMaladie carte = new CarteSortirArretMaladie("Sortie", "description");
	
	@Test
	/**
	 * Permet de tester la m�thode deplacerJoueur de la classe
	 * d'abord dans le cas o� le joueur passe par la case d�part donc gagne 200�
	 * puis dans le cas o� le joueur ne passe pas par la case d�part donc ne gagne pas d'argent
	 */
	void deplacerJoueurTest() {
		joueur.setPosition(39);
		plateau.deplacerJoueur(joueur, 3);
		assertEquals(2, joueur.getPosition());
		assertEquals(1200, joueur.getArgent());
		plateau.deplacerJoueur(joueur, 4);
		assertEquals(6, joueur.getPosition());
		assertEquals(1200, joueur.getArgent());
	}

	@Test
	/**
	 * Permet de tester la m�thode estVainqueur de la classe
	 * dans le cas o� le joueur 1 a le plus d'argent
	 */
	void estVainqueurTest() {
		plateau.getJoueur(1).ajouterArgent(50);
		assertEquals(plateau.getJoueur(1), plateau.estVainqueur());
	}
	
	@Test
	/**
	 * Permet de tester la m�thode finPartie de la classe
	 * dans le cas o� personne n'est fauch� donc la partie n'est pas finie
	 * puis dans le cas o� tous les joueurs sauf le 1 sont fauch�s donc la partie est finie
	 */
	void finPartieTest() {
		assertFalse(plateau.finPartie());
		plateau.getJoueur(0).setEstFauche(true);
		plateau.getJoueur(2).setEstFauche(true);
		assertTrue(plateau.finPartie());
	}

}
