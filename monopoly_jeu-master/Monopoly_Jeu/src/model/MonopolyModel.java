package model;

import java.io.Serializable;

import view.FenetreDeJeu;

public class MonopolyModel implements Serializable {
	private String request;
	private FenetreDeJeu fenetreDeJeu;

	public MonopolyModel(String request, FenetreDeJeu fenetreDeJeu) {
		super();
		this.request = request;
		this.fenetreDeJeu = fenetreDeJeu;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public FenetreDeJeu getFenetreDeJeu() {
		return fenetreDeJeu;
	}

	public void setFenetreDeJeu(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}

}
