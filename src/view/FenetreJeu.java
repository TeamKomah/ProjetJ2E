package view;


import javax.swing.*;

import controller.WargameController;
import model.Partie;
import observer.Observer;

import java.awt.*;

public class FenetreJeu extends JFrame implements Observer{
	public final static String TITRE_FENETRE = "Wargame";
	public final static int LARGEUR_FENETRE = 1150;
	public final static int HAUTEUR_FENETRE = 750;
    public final static int NB_LIGNES = 15;
    public final static int NB_COLONNES = 25;
    
    private PanneauJeu panneau;
    private Header header;


    public FenetreJeu(WargameController controller){
        initFenetreJeu(controller);
    }

    public void initFenetreJeu(WargameController controller){
    	this.setTitle(TITRE_FENETRE);
        this.setSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
        this.setLocationRelativeTo(null);
        panneau = new PanneauJeu(NB_LIGNES,NB_COLONNES);
        this.getContentPane().add(panneau);
        header = new Header(controller);
        this.getContentPane().add(BorderLayout.NORTH,header);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.getContentPane().add(new Case(0,0));
        
        this.setVisible(true);
    }
    

	public PanneauJeu getPanneau() {
		return panneau;
	}

	public Header getHeader() {
		return header;
	}

	@Override
	public void update(String str) {
		header.switchCouleurBouton();
	}
    
}
