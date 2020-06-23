package fenetres;

import cases.CaseCompetence;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jeudeplateau.Case;

/**
 * Fenêtre d'afficher lors du clique sur une compétence.
	 * Actions : 
	 * - Poser une Competence
	 * - Revendre une Competence
	 * - Revendre le Competence
 * @see FenetreDeJeu
 */
public class FenetreActionSurCompetence {
	
	private FenetreDeJeu fjeu;
	private Stage stage;
	private HBox root;
	private int position;
	private Label txt;
	private Label errorTxt;
	private Button acqComp;
	private Button revendreComp;
	
	/**
	 * Constructeur 
	 * @param fjeu FenetreDeJeu
	 * @see FenetreDeJeu
	 */
	public FenetreActionSurCompetence(FenetreDeJeu fjeu) {
		
		this.fjeu = fjeu;
		
		this.stage = new Stage();
		this.stage.setTitle("Action sur le Competence :");
		this.stage.initOwner(fjeu.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialisation de root : image, un label et des boutons. 
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");
		
		Image i_Competence;
		
		switch(position) {
		case 5: i_Competence = new Image("images/gare.jpg"); break;
		case 15: i_Competence = new Image("images/gare.jpg"); break;
		case 25: i_Competence = new Image("images/gare.jpg"); break;
		case 35: i_Competence = new Image("images/gare.jpg"); break;
		case 28: i_Competence = new Image("images/eau.jpg"); break;
		case 12: i_Competence = new Image("images/elec.jpg"); break;
		default: {
			String couleur = fjeu.getPartie().getPM().getCase(position).getCouleur();
			i_Competence = new Image("images/m_"+couleur+".jpg");
		}; break;
		}
		
		ImageView iv_Competence = new ImageView(i_Competence);
		root.getChildren().add(iv_Competence);
		
		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);
		
		txt = new Label("Que voulez-vous faire sur le Competence "+ fjeu.getPartie().getPM().getCase(position).getNom() +" ?");
		aside.getChildren().add(txt);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		acqComp = new Button("Poser une Competence ("+fjeu.getPartie().getPM().getCase(position).getPrixCompetence()+"€)");
		acqComp.setOnAction(new EvtAcquerir());
		if(position != 5 && position != 15 && position != 25 && position != 35 && position != 12 && position != 28)
			buttons_horiz.getChildren().add(acqComp);
		
		revendreComp = new Button("Revendre le Competence");
		revendreComp.setOnAction(new EvtRevendre());
		buttons_horiz.getChildren().add(revendreComp);

		aside.getChildren().add(buttons_horiz);
		
		errorTxt = new Label("");
		errorTxt.setTextFill(Color.RED);
		aside.getChildren().add(errorTxt);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(acqComp.isFocused())
	        	   acqComp.fire();
	           else
	        	   revendreComp.fire();
	           ev.consume(); 
	        }
	    });
	}
	
	/**
	 * Affichage de la fenêtre et réinitialisation de root.
	 * @param pos int
	 */
	public void afficherFenetre(int pos) {
		position = pos;
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,470,130);
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
	 * Acquisition d'une competence et l'ajoute dans la case correspondante de la fenetre principale.
	 */
	private class EvtAcquerir implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			CaseCompetence c = (CaseCompetence) fjeu.getPartie().getPM().getCase(position);
			if(c.getPeutAcheterCompetence()) {
				c.ajouterCompetence(fjeu);
				fjeu.setCompetence(c);
				stage.close();
			}
			else errorTxt.setText("Impossible d'obtenir une compétence.");
			event.consume();
		}
	}
	
	/**
	 * Evènement 
	 * Revente d'une compétence à un autre joueur.
	 */
	private class EvtRevendre implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			int prixRevente = fjeu.getPartie().getPM().getCase(position).getPrix() + fjeu.getPartie().getPM().getCase(position).getNbCompetence()*fjeu.getPartie().getPM().getCase(position).getPrixCompetence();
			fjeu.afficherMessage(fjeu.getPartie().getPM().getCase(position).getProprietaire().getNom() + " revend " + fjeu.getPartie().getPM().getCase(position).getNom() + " pour " + prixRevente + "€");
			fjeu.getPartie().getPM().getJoueurActif().getListeCompetences().remove(fjeu.getPartie().getPM().getCase(position));
			fjeu.getPartie().getPM().getCase(position).setProprietaire(null);
			fjeu.getPartie().getPM().getCase(position).getSignet().setFill(Color.TRANSPARENT);
			fjeu.getPartie().getPM().getJoueurActif().getListeCouleur();
			Case c = fjeu.getPartie().getPM().getCase(position); 
			
			if(c.getId() == 5 || c.getId() == 15 || c.getId() == 25 || c.getId() == 35){
				fjeu.getPartie().getPM().getJoueurActif().setNbEntreprises(fjeu.getPartie().getPM().getJoueurActif().getNbEntreprises()-1);
			}
			else if(c.getId() == 12 || c.getId() == 28){
					fjeu.getPartie().getPM().getJoueurActif().setNbServices(fjeu.getPartie().getPM().getJoueurActif().getNbServices()-1);
			}
			else {
				CaseCompetence t = (CaseCompetence) fjeu.getPartie().getPM().getCase(position); 
				t.setNbCompetence(0);
			}
			
			for(int i=0; i<6; i++) {
				fjeu.getPartie().getPM().getCase(position).Competences.get(i).setFill(Color.TRANSPARENT);
			}
			fjeu.getPartie().getPM().getJoueurActif().ajouterArgent(prixRevente);
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * Évènement 
	 * Fermeture de la fenêtre et mise à jour des labels.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fjeu.refreshLabels(fjeu.getPartie().getPM());
			event.consume();
		}
	}
	
}
