package View;


import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame{

    public final static int NB_LIGNES = 15;
    public final static int NB_COLONNES = 25;


    public FenetreJeu(){
        initFenetreJeu();
    }

    public void initFenetreJeu(){
        this.setSize(new Dimension(1000,750));
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
