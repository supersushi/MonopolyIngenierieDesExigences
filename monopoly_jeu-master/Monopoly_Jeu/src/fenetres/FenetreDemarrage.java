package fenetres;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fenêtre à afficher au démarrage d'une nouvelle {@link jeumonopoly.Partie}, permettant de sélectionner le nombre de joueur.
 * @see FenetreDeJeu
 */
public class FenetreDemarrage {

	private FenetreDeJeu fjeu;
	private Stage stage;
	private VBox root;
	private Label l_NbJoueurs;private Label l_NbJoueurs2;
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
	private Button b_Valider;
	private int choix = 0;

	/**
	 * Unique constructeur de la classe {@link FenetreDemarrage}, prenant en paramètre la {@link FenetreDeJeu} fjeu.
	 * @param fjeu FenetreDeJeu
	 * @see FenetreDeJeu
	 */
	public FenetreDemarrage(FenetreDeJeu fjeu) {

		this.fjeu = fjeu;

		this.stage = new Stage();

		this.stage.setTitle("UPM-Monopoly");
		this.stage.initOwner(fjeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		AnchorPane AnchorPaneRoot = new AnchorPane();

		//Scene scene = new Scene(AnchorPaneRoot, 884.0,495.0);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


		root = new VBox();

		initRoot();
	    Scene scene = new Scene(root, 884.0,495.0/*400,190*/);


		stage.setScene(scene);

		stage.setOnHiding(new EvtQuitter());
	}

	/**
	 * Initialise la VBox root de la FenetreDemarrage avec une {@link ListView} de nombres de joueurs et un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
       // root.resize(884, 495);

		l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");
		l_NbJoueurs2 = new Label("                                                        Coder par Hegel Motokoua");


		VBox vBox1 =new VBox();
		vBox1.setPrefHeight(884.0);
		vBox1.setPrefWidth(495.);
		vBox1.setStyle("-fx-background-color: #005395");
		vBox1.setBlendMode(BlendMode.SRC_OVER);
		vBox1.setDepthTest(DepthTest.INHERIT);
		vBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox1.setCenterShape(true);
		l_NbJoueurs2.setCenterShape(true);

		ImageView imageView1=new ImageView();
		imageView1.setFitHeight(500.0);
		imageView1.setFitWidth(500.0);
		imageView1.setPickOnBounds(true);
		imageView1.setPreserveRatio(true);

		imageView1.setImage(new Image("/images/ml3.png"));

		VBox.setMargin((imageView1), new Insets(10,0,0,170));

		vBox1.getChildren().add(imageView1);



		vBox1.getChildren().add(l_NbJoueurs2);



		root.getChildren().add(vBox1);



		root.getChildren().add(l_NbJoueurs);
		for(int i=0; i<4; i++) {
			listeJoueurs.add(new TextField(i<2?"Joueur"+(i+1):""));
			listeJoueurs.get(i).setPromptText("Nom du joueur "+(i+1));
			root.getChildren().add(listeJoueurs.get(i));
		}
		b_Valider = new Button("Valider");
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setDefaultButton(true);
		b_Valider.setOnAction(new EvtValider());

		root.getChildren().add(b_Valider);



		VBox vBox2 =new VBox();
		vBox2.setPrefHeight(884.0);
		vBox2.setPrefWidth(495.);
		vBox2.setStyle("-fx-background-color: #005395");
		vBox2.setBlendMode(BlendMode.SRC_OVER);
		vBox2.setDepthTest(DepthTest.INHERIT);
		vBox2.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox2.setCenterShape(true);
		l_NbJoueurs2.setCenterShape(true);

		ImageView imageView2=new ImageView();
		imageView2.setFitHeight(300.0);
		imageView2.setFitWidth(300.0);
		imageView2.setPickOnBounds(true);
		imageView2.setPreserveRatio(true);

		imageView2.setImage(new Image("/images/LOGO_UPM_UNIVERSITE_PRIVEE_DE_MARRAKECH.jpg"));

		VBox.setMargin((imageView2), new Insets(10,0,0,270));

		vBox2.getChildren().add(imageView2);

		root.getChildren().add(vBox2);
	}



	/**
	 * Renvoie la Stage de la fenêtre de démarrage.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Evènement 
	 * Récupération du nombre de joueurs dans la page d'acceuil avant de lancer une partie .
	 * @see FenetreDeJeu
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			ArrayList<String> champs = new ArrayList<String>();
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fjeu.setPartie(champs.size(), champs);
				fjeu.getStage().show();
				stage.close();
			}
			event.consume();
		}
	}

	/**
	 * Evènement 
	 * Ferme la fenêtre de démarrage et arrête le programme si l'on a pas cliqué sur le bouton Valider.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}
}
