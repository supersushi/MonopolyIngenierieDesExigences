package model;

import java.util.Random;

/**
 * Cette classe represente un dé. Sa valeur est généré de manière aléatoire 
*@author  Massourang Jugurtha Lina Emma
*/

public class Des {

	private int de1;
	private int de2;
	private Random aleatoire = new Random();

	/**
	 * Renvoie la somme des 2 dés obtenue aléatoirement
	 * @return nombreLancé
	 */
	public int getDes() {
		return (this.de1 + this.de2);
	}

	/**
	 * Renvoie le chiffre obtenu par le dé 1
	 * @return de1
	 */
	public int getDe1(){
		return this.de1;
	}

	/**
	 * Renvoie le chiffre obtenu par le dé 2
	 * @return de2
	 */
	public int getDe2(){
		return this.de2;
	}

	/**
	 * Attribue un nombre aléatoire aux 2 dés puis les additionne
	 * @return nombreDés
	 */
	public int lancerDes() {
		this.de1 = 1 + this.aleatoire.nextInt(6);
		this.de2 = 1 + this.aleatoire.nextInt(6);

		return getDes();
	}

}