package model;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Document {
	private int id;
	private String nom;
	public Utilisateur auteur;
	public Date dateC;
	private ArrayList <Utilisateur> listContributeur = new ArrayList<>();
	public String visibilite;
	private ArrayList<Document> historyDoc = new ArrayList<>();
	public File readme;
	ConnectionBD connect;
	
	public Document() throws ClassNotFoundException, SQLException{
		connect = ConnectionBD.getConnectionBD();
	}
	
	/**
	 * 
	 * @param id
	 * @param nom
	 * @param auteur
	 * @param visibilite
	 */
	public Document(int id, String nom, Utilisateur auteur, String visibilite) {
		super();
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
		this.visibilite = visibilite;
	}
	/**
	 * Ajout d'un nouveau contributeur.
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public int ajoutContributeur(Utilisateur user) throws SQLException{
		this.listContributeur.add(user);
		return connect.QueryUpdate("INSERT INTO accesdoc(Contributeur_ID,Document_ID)"
				+ "VALUES('"+user.getId()+"','"+this.id+"')");
		
	}
	/**
	 * recupere les contributeurs d'un document.
	 * @throws SQLException
	 */
	public void listContributeur() throws SQLException{
		ResultSet res = connect.Query("SELECT * FROM utilisateur"
				+ "WHERE Utilisateur_ID IN"
				+ "(SELECT Contributeur_ID FROM accesdoc"
				+ "WHERE Document_ID = '"+this.id+"')");
		while(res.next()){
			listContributeur.add(new Utilisateur(res.getInt(1),
					res.getString(2),
					res.getString(3),
					res.getDate(4),
					res.getString(5),
					res.getString(6),
					res.getString(7)));
		}	
	}
	
	/**
	 * Ajout d'une nouvelle version du document.
	 * @param doc
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int nouveaVersion(Document doc, int userId) throws SQLException{
		return connect.QueryUpdate("INSERT INTO versiondoc(Vdocument_ID,Vutilisateur_ID,DatEnregistrement)"
				+ "VALUES('"+this.id+"','"+userId+"',NOW()");
	}
	
	/**
	 * recpuere l'historique du document
	 * @throws SQLException
	 */
	public void listHistory() throws SQLException{
		ResultSet res = connect.Query("SELECT * FROM document"
				+ "WHERE Document_ID IN"
				+ "(SELECT Vdocument_ID FROM versiondoc"
				+ "WHERE Vdocument = '"+this.id+"')");
		while(res.next()){
			ResultSet user = connect.Query("SELECT * FROM utilisateur"
					+ "WHERE Utilisateur_ID='"+res.getInt(3)+"'");
			user.next();
			Utilisateur utili = new Utilisateur(
					user.getInt(1),
					user.getString(2),
					user.getString(3),
					user.getDate(4),
					user.getString(5),
					user.getString(6),
					user.getString(7));
			historyDoc.add(new Document(res.getInt(1),
					res.getString(2),
					utili,
					res.getString(4)));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public String getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(String visibilite) {
		this.visibilite = visibilite;
	}

	public ConnectionBD getConnect() {
		return connect;
	}

	public void setConnect(ConnectionBD connect) {
		this.connect = connect;
	}
	
}
