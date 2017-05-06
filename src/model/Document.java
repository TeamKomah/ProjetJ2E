package model;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Document {
	private int id;
	private String nom;
	public Utilisateur auteur;
	public Date dateC;
	private ArrayList <Utilisateur> listContributeur = new ArrayList<>();
	private ArrayList <Commentaire> listComDoc = new ArrayList<>();
	public int visibilite;
	private ArrayList<Document> historyDoc = new ArrayList<>();
	public File readme;
	public static int  nbDoc = 0;
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Document(int id, String nom, Utilisateur auteur, int visibilite, Date date) throws ClassNotFoundException, SQLException {
		this();
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
		this.visibilite = visibilite;
		this.dateC= date;
		nbDoc++;
	}
	
	/**
	 * 
	 * @param idDocmt
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Document(int idDocmt) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		connect = ConnectionBD.getConnectionBD();
		PreparedStatement pre = null;
		pre = (PreparedStatement) connect.getConnect().prepareStatement("SELECT * FROM document WHERE Document_ID=?");
		pre.setInt(1,idDocmt);
		ResultSet res = pre.executeQuery();
		if(res.next()){
			this.id = idDocmt;
			this.nom = res.getString(2);
			this.auteur = new Utilisateur(res.getInt(3));
		}
	}
	/**
	 * methode qui recupere tous les commentaire liés a un document
	 * @return une lite contenant les commentaire
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Commentaire> getCommentaireDoc() throws SQLException, ClassNotFoundException{

		ResultSet res = connect.Query("SELECT *"
				+ "FROM commentaire "
				+ "WHERE  Cdoc_ID='"+this.id+"'" + " ORDER BY dateCommentaire ASC");
		while(res.next()){
			listComDoc.add( new Commentaire (res.getInt(3),
				res.getInt(1),
				res.getString(2),
			 	res.getDate(5),this.id)
			 	);
		}

		return listComDoc;
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
	
	public int ajouterDoc() throws SQLException{
		 return connect.QueryUpdate("INSERT INTO document(Document_Name,Editeur_ID,Type_ID,DatePubli) "
				+ "VALUES('"+this.nom+"','"+this.auteur.getId()+"','"+this.visibilite+"',NOW())");
	}
	
	public int accesDoc(int user, int iddoc ) throws SQLException{
		 return connect.QueryUpdate("INSERT INTO accesdoc(Contributeur_ID,Document_Id) "
				+ "VALUES('"+user+"','"+iddoc+"')");
	}
	
	
	
	/**
	 * Ajout d'une nouvelle version du document.
	 * @param doc
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int nouveauVersion(int iddoc,int userId) throws SQLException{
		java.util.Date date = new java.util.Date();
		
		return connect.QueryUpdate("INSERT INTO versiondoc( Vdocument_ID,Vutilisateur_ID,VersionDoc_Name,DatEnregistrement) "
				+ "VALUES('"+iddoc+"','"+userId+"','"+this.nom+"',NOW())");
	}
	
	public void trouverdoc() throws SQLException{
		ResultSet res = connect.Query("SELECT * FROM document WHERE Document_ID = '"+this.id+"'");
		res.next();
		this.setId(res.getInt(1));
		this.setNom(res.getString(2));
		this.setVisibilite(4);
		this.setDateC(res.getDate(5));
	}
	
	/**
	 * recpuere l'historique du document
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public void listHistory() throws SQLException, ClassNotFoundException{
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
					res.getInt(4),res.getDate(5)));
		}
	}

	public ArrayList<String> recupererDoc() throws SQLException{
		ResultSet res = connect.Query("SELECT Document_ID, Document_Name FROM document WHERE Document_ID='"+this.id+"'");
		res.next();
		ArrayList doc = new ArrayList();
		doc.add(res.getInt(1));
		doc.add(res.getString("Document_Name"));
		return doc;
	}
	
	
	public ArrayList<String> recupererDocVersio() throws SQLException{
		ResultSet res = connect.Query("SELECT VersionDoc_ID, VersionDoc_Name FROM versiondoc WHERE VersionDoc_ID='"+this.id+"'");
		res.next();
		ArrayList doc = new ArrayList();
		doc.add(res.getInt("VersionDoc_ID"));
		doc.add(res.getString("VersionDoc_Name"));
		return doc;
	}
	
	
	public int supprimer(int id) throws SQLException{
		return connect.QueryUpdate("delete from document where Document_ID = '"+id+"'");
	}
	
	public ArrayList<Document> versions(int iddoc, Utilisateur user) throws SQLException, ClassNotFoundException{
		ResultSet res = connect.Query("select * from versiondoc where Vdocument_ID = '"+iddoc+"'");
		while(res.next()){
			user.setId(res.getInt(3));
			System.out.println("versioonn doccc");
			historyDoc.add(new Document(res.getInt(1),res.getString(4),user,0,res.getDate(5)) );
			System.out.println(res.getString(4));
		}
		return historyDoc;
	}
	
	public int nbDoc() throws SQLException{
		ResultSet res = connect.Query("select MAX(Document_ID) from document ");
		res.next();
		return res.getInt(1);
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

	public int getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(int visibilite) {
		this.visibilite = visibilite;
	}

	public ConnectionBD getConnect() {
		return connect;
	}

	public void setConnect(ConnectionBD connect) {
		this.connect = connect;
	}
	
}
