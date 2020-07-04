package view;

import controller.EmbaucheQuitterController;
import controller.EmbaucheSalarieNonController;
import controller.EmbaucheSalarieOuiController;
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
import model.Fenetre;

/**
 * Fenêtre à afficher lorqu'on atterit sur un salarié sans Patron. Action
 * réalisable : l'embaucher
 */
public class FenetreEmbaucheSalarie extends Fenetre {

	private FenetreDeJeu fenetreDeJeu;
	private Stage stage;
	private HBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;

	/**
	 * Constructeur prenant en paramètr FenetreDeJeu
	 * 
	 * @param fp FenetreDeJeu
	 */
	public FenetreEmbaucheSalarie(FenetreDeJeu fenetreDeJeu) {

		this.fenetreDeJeu = fenetreDeJeu;

		this.stage = new Stage();
		this.stage.setTitle("Embaucher un salarié");
		this.stage.initOwner(fenetreDeJeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		stage.setOnHiding(new EmbaucheQuitterController(this));
	}

	/**
	 * Initialise la HBox root de la FenetreEmbaucheSalarie avec une image, un label
	 * posant une question et des boutons Oui/Non.
	 */
	private void initRoot() {
		root.setStyle("-fx-background-color: #CDE6D0; ");
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(10);

		Image i_Competence;

		switch (fenetreDeJeu.getPartie().getPM().getCaseActive().getNom()) {
		case "Client : Gestion d'actifs":
			i_Competence = new Image("images/contest.png");
			break;
		case "Client : Bancaire":
			i_Competence = new Image("images/contest.png");
			break;
		case "Client : BTP":
			i_Competence = new Image("images/contest.png");
			break;
		case "Client : Service":
			i_Competence = new Image("images/contest.png");
			break;
		case "Or":
			i_Competence = new Image("images/gold.png");
			break;
		case "Entreprise IT":
			i_Competence = new Image("images/computer.png");
			break;
		default: {
			String couleur = fenetreDeJeu.getPartie().getPM().getCaseActive().getCouleur();
			i_Competence = new Image("images/c_" + couleur + ".png");
		}
			;
			break;
		}

		ImageView iv_Competence = new ImageView(i_Competence);
		root.getChildren().add(iv_Competence);

		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);

		l_Texte = new Label("Voulez vous embaucher " + fenetreDeJeu.getPartie().getPM().getCaseActive().getNom()
				+ " pour " + fenetreDeJeu.getPartie().getPM().getCaseActive().getPrix() + "€ ? \n \n"
				+ fenetreDeJeu.getPartie().getPM().getCaseActive().descriptionPoste());
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);

		b_Oui = new Button("Oui");
		b_Oui.setOnAction(new EmbaucheSalarieOuiController(this));
		buttons_horiz.getChildren().add(b_Oui);

		b_Non = new Button("Non");
		b_Non.setOnAction(new EmbaucheSalarieNonController(this));
		buttons_horiz.getChildren().add(b_Non);
		b_Oui.setStyle(" -fx-background-color:#090a0c,\n"
				+ "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
				+ "        linear-gradient(#20262b, #191d22),\n"
				+ "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n"
				+ "    -fx-background-radius: 5,4,3,5;\n" + "    -fx-background-insets: 0,1,2,0;\n"
				+ "    -fx-text-fill: white;\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
				+ "    -fx-font-family: \"Arial\";\n" + "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n"
				+ "    -fx-font-size: 12px;\n" + "    -fx-padding: 10 20 10 20;");
		b_Non.setStyle(" -fx-background-color:#090a0c,\n"
				+ "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
				+ "        linear-gradient(#20262b, #191d22),\n"
				+ "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n"
				+ "    -fx-background-radius: 5,4,3,5;\n" + "    -fx-background-insets: 0,1,2,0;\n"
				+ "    -fx-text-fill: white;\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
				+ "    -fx-font-family: \"Arial\";\n" + "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n"
				+ "    -fx-font-size: 12px;\n" + "    -fx-padding: 10 20 10 20;");

		aside.getChildren().add(buttons_horiz);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				if (b_Oui.isFocused())
					b_Oui.fire();
				else
					b_Non.fire();
				ev.consume();
			}
		});
	}

	/**
	 * Affiche la fenêtre en réinitialisant la HBox root à chaque appel.
	 */
	public void afficherFenetre() {
		root = new HBox();
		initRoot();

		Scene scene = new Scene(root, 600, 200);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Renvoie la Stage de la fenêtre.
	 * 
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	public FenetreDeJeu getFenetreDeJeu() {
		return fenetreDeJeu;
	}

	public void setFenetreDeJeu(FenetreDeJeu fenetreDeJeu) {
		this.fenetreDeJeu = fenetreDeJeu;
	}
}
