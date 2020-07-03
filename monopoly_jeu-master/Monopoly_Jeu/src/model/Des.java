package model;

import java.util.Random;

/**
 * Cette classe represente un d�. Sa valeur est g�n�r� de mani�re al�atoire 
*@author  Massourang Jugurtha Lina Emma
*/

public class Des {

	private int de1;
	private int de2;
	private Random aleatoire = new Random();

	/**
	 * Renvoie la somme des 2 d�s obtenue al�atoirement
	 * @return nombreLanc�
	 */
	public int getDes() {
		return (this.de1 + this.de2);
	}

	/**
	 * Renvoie le chiffre obtenu par le d� 1
	 * @return de1
	 */
	public int getDe1(){
		return this.de1;
	}

	/**
	 * Renvoie le chiffre obtenu par le d� 2
	 * @return de2
	 */
	public int getDe2(){
		return this.de2;
	}

	/**
	 * Attribue un nombre al�atoire aux 2 d�s puis les additionne
	 * @return nombreD�s
	 */
	public int lancerDes() {
		this.de1 = 1 + this.aleatoire.nextInt(6);
		this.de2 = 1 + this.aleatoire.nextInt(6);

		return getDes();
	}

}