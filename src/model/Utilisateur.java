package model;

import java.awt.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Utilisateur {
	
	private int id;
	private String nom;
	private String prenom;
	private Date dateN;
	private String pseudo;
	private String mdp;
	private String email;
	private ArrayList <Document> mesDoc = new ArrayList<>();
	private ArrayList <Document> listDocPartager = new ArrayList<>();
	private ArrayList<Utilisateur> mesAmis = new ArrayList<>();
	/*private ArrayList <Message> mesRecu = new ArrayList<>();
	private ArrayList <Message> mesExpedier = new ArrayList<>();*/
	ConnectionBD connect;
	
	
	public Utilisateur() throws ClassNotFoundException, SQLException{
		 connect = ConnectionBD.getConnectionBD();
	}
	public Utilisateur(int id, String nom, String prenom, Date dateN, String pseudo, String mdp, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = dateN;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
	}
	/**
	 * Identification d'un utilisateur
	 * @param pseudo
	 * @param mdp
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean identifier(String pseudo , String mdp) throws SQLException {
		ResultSet res = connect.Query("SELECT *"
				+ "FROM utilisateur "
				+ "WHERE  utilisateur.Utilisateur_Pseudo='"+pseudo+"' AND utilisateur.Utilisateur_Mdp='"+mdp+"'");
		if(res!=null){
			res.next();
			this.setId(res.getInt(1));
			this.setNom(res.getString(2));
			this.setPrenom(res.getString(3));
		
			return true;
		}
	return false;
	}
	/**
	 * Methode d'inscription d'un utilisateur
	 * @param nom
	 * @param prenom
	 * @param dateN
	 * @param pseudo
	 * @param mdp
	 * @param email
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public int inscription(String nom, String prenom , Date dateN, String pseudo, String mdp, String email) throws SQLException{
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateN(dateN);
		this.setPseudo(pseudo);
		this.setMdp(mdp);
		this.setEmail(email);
		return connect.QueryUpdate("INSERT INTO utilisateur(Utilisateur_Name,Utilisateur_Fname,Utilisateur_DatN,Utilisateur_Pseudo,Utilisateur_Mdp, Utilisateur_email)"+
		"VALUES('"+nom+"','"+prenom+"','"+dateN+"','"+pseudo+"','"+mdp+"','"+email+"')");
	}
	/**
	 * Ajout d'un ami
	 * @param ami
	 * @throws SQLException
	 */
	public int ajouterAmi(Utilisateur ami) throws SQLException{
		this.mesAmis.add(ami);
		return connect.QueryUpdate("INSERT INTO amis(Amis_ID, Autilisateur_ID)"+
		"VALUES('"+ami.id+"','"+this.id+"')");
	}
	
	/**
	 * suppression d'un utilisateur
	 * @param ami
	 * @return
	 */
	
	public boolean supprimerAmi(Utilisateur ami){
		
		
		return true;
	}
	/**
	 * recuperation des amis
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> listAmis() throws SQLException{
		
		ResultSet res = connect.Query("SELECT *"
				+ " FROM utilisateur "
				+"WHERE utilisateur.Utilisateur_ID IN "
				+"(SELECT amis.Amis_ID FROM amis, utilisateur "
				+"WHERE utilisateur.Utilisateur_ID='"+this.id+"' AND "
				+"utilisateur.Utilisateur_ID = amis.Autilisateur_ID)");
		
		while(res.next()){
			this.mesAmis.add(new Utilisateur(
					res.getInt(1),
					res.getString(2),
					res.getString(3),
					res.getDate(4),
					res.getString(5),
					res.getString(6),
					res.getString(7)
					)
					);
		}
		return mesAmis;
	}
	/**
	 * recupere les documents creés par utilisateur.
	 * @throws SQLException
	 */
	public ArrayList<Document> mesDocuments(Utilisateur utili) throws SQLException{
		ResultSet res = connect.Query("SELECT * FROM document WHERE document.Editeur_ID='"+this.id+"'");
		//Utilisateur utili = new Utilisateur( this.id,this.nom,this.prenom,this.dateN,this.pseudo,this.mdp,this.email);
		while(res.next()){
		mesDoc.add(new Document(res.getInt(1),res.getString(2),utili,res.getString(4)));
		}
		return mesDoc;
	}
	/**
	 * recupere la liste des docs partager avec cet utilisation.
	 * @throws SQLException
	 */
	public void listDocPartager() throws SQLException{
		ResultSet res = connect.Query("SELECT * FROM document"
				+ "WHERE document.Editeur_ID IN"
				+ "(SELECT Document_ID FROM accesdoc"
				+ "WHERE accesdoc.Contributeur_ID= '"+this.id+"'");
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
			listDocPartager.add(new Document(res.getInt(1),
					res.getString(2),
					utili,
					res.getString(4)
					));
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
}
