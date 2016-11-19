package View;


import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame{
	public final static String TITRE_FENETRE = "Wargame";
	public final static int LARGEUR_FENETRE = 1150;
	public final static int HAUTEUR_FENETRE = 750;
    public final static int NB_LIGNES = 15;
    public final static int NB_COLONNES = 25;


    public FenetreJeu(){
        initFenetreJeu();
    }

    public void initFenetreJeu(){
    	this.setTitle(TITRE_FENETRE);
        this.setSize(new Dimension(LARGEUR_FENETRE,HAUTEUR_FENETRE));
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new GridLayout(NB_LIGNES,NB_COLONNES));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.getContentPane().add(new Case(0,0));
        for(int i=0;i<NB_LIGNES;i++)
        {
            for(int j=0;j<NB_COLONNES;j++)
            {
                this.getContentPane().add(new Case(i,j));
            }
        }
        this.setVisible(true);
    }
}
