package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fenêtre à afficher lorqu'un joueur est en arret maladie. 
 * Choix : 
 * - Payer pour en sortir
 * - Rester et jouer sa guerison aux dés
 */
public class FenetreSortirArretMaladie {
	
	private FenetreDeJeu fjeu;
	private Stage stage;
	private HBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;
	
	/**
	 * Constructeur 
	 * @param fjeu FenetreDeJeu
	 */
	public FenetreSortirArretMaladie(FenetreDeJeu fjeu) {
		
		this.fjeu = fjeu;
		
		this.stage = new Stage();
		this.stage.setTitle("Voulez vous etre guéri? ?");
		this.stage.initOwner(fjeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialise la HBox root de la FenetreSortirArretMaladie (image, label, boutons).
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");
		
		Image i_arretmaladie = new Image("images/hospital.png");
		ImageView iv_arretmaladie = new ImageView(i_arretmaladie);
		root.getChildren().add(iv_arretmaladie);
		
		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);
		
		l_Texte = new Label("Voulez vous payer 25€ pour etre gueri ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		b_Oui = new Button("Oui");
		b_Oui.setOnAction(new EvtOui());
		buttons_horiz.getChildren().add(b_Oui);
		
		b_Non = new Button("Non");
		b_Non.setOnAction(new EvtNon());
		buttons_horiz.getChildren().add(b_Non);

		aside.getChildren().add(buttons_horiz);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Oui.isFocused())
	        	   b_Oui.fire();
	           else
	        	   b_Non.fire();
	           ev.consume(); 
	        }
	    });
	}
	
	/**
	 * Affiche la fenêtre eT réinitialise root à chaque appel.
	 */
	public void afficherFenetre() {
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,420,130);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Renvoie la Stage de la fenêtre.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Evènement 
	 * Ferme la fenêtre, réponse = TRUE.
	 */
	private class EvtOui implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			fjeu.getPartie().getPM().getCaseActive().setRep(true);
			event.consume();
		}
	}
	
	/**
	 * Evènement 
	 * Ferme la fenêtre réponse = FALSE.
	 */
	private class EvtNon implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * Evènement 
	 * Reprise de la partie lors de la fermeture de la fenêtre.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fjeu.getPartie().reprendrePartie();
			event.consume();
		}
	}
}
