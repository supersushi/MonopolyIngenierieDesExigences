package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Fenetre;
import model.MonopolyModel;

public class RequeteMonopolyClient {
	private static final Logger LOGGER = Logger.getLogger(RequeteMonopolyClient.class.getName());

	private static int PORT = 33001;

	MonopolyModel monopoly;

	public RequeteMonopolyClient(MonopolyModel monopoly) {
		this.monopoly = monopoly;
	}

	public static Fenetre call(MonopolyModel monopoly)
			throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		InetAddress host = InetAddress.getLocalHost();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		Socket socket = new Socket(host.getHostName(), PORT);

		oos = new ObjectOutputStream(socket.getOutputStream());
		LOGGER.log(Level.INFO, "Connection to server established");

		LOGGER.log(Level.INFO, "Sending request to Socket Server");
		oos.writeObject(monopoly);

		ois = new ObjectInputStream(socket.getInputStream());
		Fenetre result = (Fenetre) ois.readObject();

		LOGGER.log(Level.INFO, "Received response" + result);

		socket.close();

		return result;
	}
}
