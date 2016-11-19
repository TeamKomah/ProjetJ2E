package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.WargameController;
import model.Partie;

public class Header extends JPanel{
	private JButton boutonTour;
	public Header(WargameController controller){
		boutonTour = new JButton("FIN DE TOUR");
		boutonTour.setBackground(Color.GREEN);
		boutonTour.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.control();
			}
		});
	
		this.add(BorderLayout.CENTER, boutonTour);
	}

	/**
	 * est appelé à la fin du tour d'un joueur pour changer la couleur du bouton en fonction du joueur du tour courant
	 * @param p
	 */
	public void switchCouleurBouton() {
		if(this.boutonTour.getBackground()==Color.GRAY)
		{
			boutonTour.setBackground(Color.GREEN);
		}
		else{
			boutonTour.setBackground(Color.GRAY);
		}
		
	}

}
