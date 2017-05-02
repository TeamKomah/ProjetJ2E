package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class Message {
	private int id;
	private String contenu;
	private Date dateMes;
	private Utilisateur expediteur;
	private Utilisateur destinataire;
	ConnectionBD connect;
	
	public Message() throws ClassNotFoundException, SQLException{
		this.connect = ConnectionBD.getConnectionBD();
	}
	
	public Message(int id, String contenu, Date dateMes, int idE, int idR) throws SQLException, ClassNotFoundException {
		super();
		this.id = id;
		this.contenu = contenu;
		this.dateMes = dateMes;
		this.expediteur = new Utilisateur(idE);
		this.destinataire = new Utilisateur(idR);
	}
	
	public int enregistrer() throws SQLException, ClassNotFoundException{
		int idE = this.expediteur.getId();
		int idR = this.destinataire.getId();
		connect = ConnectionBD.getConnectionBD();
		PreparedStatement pre = null;
		pre = (PreparedStatement) connect.getConnect().prepareStatement("INSERT INTO message (Emeteur_ID,Recepteur_ID,DateMess,Contenu_Msg) "+
									"VALUES (?,?,NOW(),?,?)");
		pre.setInt(1,idE);
		pre.setInt(2,idR);
		pre.setString(3,contenu);
		pre.setInt(4,0);
		
		return pre.executeUpdate();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateMes() {
		return dateMes;
	}

	public void setDateMes(Date dateMes) {
		this.dateMes = dateMes;
	}

	public Utilisateur getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}

	public Utilisateur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}
	
	
	
	
}
