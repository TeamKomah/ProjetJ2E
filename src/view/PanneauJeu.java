package view;

import java.awt.GridLayout;

import javax.swing.JPanel;


public class PanneauJeu extends JPanel{
	public PanneauJeu(int nbLignes, int nbCol){
		this.setLayout(new GridLayout(nbLignes,nbCol));
		for(int i=0;i<nbLignes;i++)
        {
            for(int j=0;j<nbCol;j++)
            {
                this.add(new Case(i,j));
            }
        }
	}
}

