package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import view.FenetreDeJeu;

public class ConnexionMonopolyClient implements Runnable {
	private Socket connexion = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private FenetreDeJeu fenetreDeJeu;
	// Notre liste de commandes. Le serveur nous répondra différemment selon la
	// commande utilisée.
	private static int count = 0;
	private String name = "Client-";

	public ConnexionMonopolyClient(String host, int port) {
		name += ++count;
		try {
			connexion = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {

			in = new ObjectInputStream(connexion.getInputStream());
			out = new ObjectOutputStream(connexion.getOutputStream());
			out.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			setFenetreDeJeu((FenetreDeJeu) in.readObject());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (fenetreDeJeu.getStage().isShowing() || fenetreDeJeu.getFenetreDemarrage().getStage().isShowing()) {

		}

	}

	// Méthode pour lire les réponses du serveur
	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[4096];
		stream = in.read(b);
		response = new String(b, 0, stream);
		return response;
	}

	public FenetreDeJeu getFenetreDeJeu() {
		return fenetreDeJeu;
	}

	public void setFenetreDeJeu(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}

}
