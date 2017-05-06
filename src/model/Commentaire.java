package model;

import java.sql.SQLException;
import java.util.Date;
import com.mysql.jdbc.PreparedStatement;

public class Commentaire {
	private int idCommentaire;
	private String contenu ;
	private Date dateCommentaire;
	private Utilisateur expediteurCom;
	private Document doc;
	ConnectionBD connect;
	
	
	public Commentaire() throws ClassNotFoundException, SQLException{
		this.connect = ConnectionBD.getConnectionBD();
	}
	
	public Commentaire(int idU,int idCom,String contenuCom, Date dateCom, int idDocmt) throws ClassNotFoundException, SQLException{
		super();
		this.idCommentaire = idCom;
		this.expediteurCom = new Utilisateur(idU) ;
		this.contenu = contenuCom ;
		this.dateCommentaire = dateCom ;
		this.doc = new Document(idDocmt);
	}
	
	public int enregistrerCommentaire() throws SQLException, ClassNotFoundException{
		connect = ConnectionBD.getConnectionBD();
		PreparedStatement pre = null;
		pre = (PreparedStatement) connect.getConnect().prepareStatement
				("INSERT INTO commentaire (Commentaire,AutCommentaire_ID,CDoc_ID,dateCommentaire) "+
									"VALUES (?,?,?,NOW())");
		pre.setString(1,contenu);
		pre.setInt(2,this.expediteurCom.getId());
		pre.setInt(3, this.doc.getId());
		
		return pre.executeUpdate();
	}

	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public Utilisateur getExpediteurCom() {
		return expediteurCom;
	}

	public void setExpediteurCom(Utilisateur expediteurCom) {
		this.expediteurCom = expediteurCom;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document ndoc) {
		doc = ndoc;
	}

	public ConnectionBD getConnect() {
		return connect;
	}

	public void setConnect(ConnectionBD connect) {
		this.connect = connect;
	}
	

}
