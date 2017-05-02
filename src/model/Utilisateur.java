package model;

import java.awt.List;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	
	public Utilisateur(int id) throws SQLException, ClassNotFoundException{
		connect = ConnectionBD.getConnectionBD();
		PreparedStatement pre = null;
		pre = (PreparedStatement) connect.getConnect().prepareStatement("SELECT * FROM utilisateur WHERE Utilisateur_ID=?");
		pre.setInt(1,id);
		ResultSet res = pre.executeQuery();
		if(res.next()){
			this.id = id;
			this.nom = res.getString(2);
			this.prenom = res.getString(3);
		}
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
	public void getUtilisateur(int id) throws SQLException{
		ResultSet res = connect.Query("SELECT *"
				+ "FROM utilisateur "
				+ "WHERE  utilisateur.Utilisateur_ID='"+id+"'");
		if(res!=null){
			res.next();
			this.setId(res.getInt(1));
			this.setNom(res.getString(2));
			this.setPrenom(res.getString(3));
			this.setDateN(res.getDate(4));
			this.setPseudo(res.getString(5));
		
		}
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
	
	public int inscription(String nom, String prenom , Date daten, String pseudo, String mdp, String email) throws SQLException{
		/*this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateN(dateN);
		this.setPseudo(pseudo);
		this.setMdp(mdp);
		this.setEmail(email);*/
		System.out.println("inscription");
		System.out.println(daten);
		 int okk = connect.St.executeUpdate("INSERT INTO utilisateur(Utilisateur_Name,Utilisateur_Fname,Utilisateur_DatN,Utilisateur_Pseudo,Utilisateur_Mdp,Utilisateur_email) "
		 		+ " VALUES( '"+nom+"','"+prenom+"', '"+daten+"','"+pseudo+"','"+mdp+"','"+email+"')");
	
		return 0;
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
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<Document> mesDocuments(Utilisateur utili) throws SQLException, ClassNotFoundException{
		ResultSet res = connect.Query("SELECT * FROM document WHERE document.Editeur_ID='"+this.id+"'");
		while(res.next()){
		mesDoc.add(new Document(res.getInt(1),res.getString(2),utili,res.getInt(4),res.getDate(5)));
		}
		return mesDoc;
	}
	/**
	 * recupere la liste des docs partager avec cet utilisation.
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<Document> listDocPartager(Utilisateur user) throws SQLException, ClassNotFoundException{
		ResultSet res = connect.Query("SELECT * FROM document "
				+ "WHERE Document_ID IN "
				+ "(SELECT Document_ID FROM accesdoc "
				+ "WHERE Contributeur_ID= '"+user.getId()+"')");
		while(res.next()){
					
			listDocPartager.add(new Document(res.getInt(1),
					res.getString(2),
					user,
					res.getInt(4),
					res.getDate(5)
					));
		}
		
		return listDocPartager;
	}
	/**
	 * methode qui recupere toute la conversation avec l'uitilisateur idAmi
	 * @param idAmi
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
		//methode qui recupere toute la conversation avec l'uitilisateur idAmi
		public ArrayList<Message> getCommunication(int idAmi) throws SQLException, ClassNotFoundException{
			ArrayList<Message> com = new ArrayList<Message>();
		
			ResultSet res = connect.Query("SELECT * FROM message "
													+ " WHERE Emeteur_ID IN ("+this.id+","+idAmi+") "
													+ " AND Recepteur_ID IN ("+this.id+","+idAmi+") "
													+ " ORDER BY DateMess ASC");
			while(res.next()){
				int idM = res.getInt(1);
				int idE = res.getInt(2);
				int idR = res.getInt(3);
				Date dateM =  res.getDate(4);
				String cont = res.getString(5);
				Message msg = new Message(idM, cont, dateM, idE, idR);
				//ajout du message dans la communication
				com.add(msg);
			}
			
			return com;
			
		}
		
		//recuperation des message non lu
		public ArrayList<int[]> getMessageNonLu() throws ClassNotFoundException, SQLException{
			ArrayList<int[]> array = new ArrayList<>();
			connect = ConnectionBD.getConnectionBD();
			PreparedStatement pre = null;
			pre = (PreparedStatement) connect.getConnect().prepareStatement
					("SELECT Emeteur_ID,COUNT(*) FROM message "
					+ "WHERE Recepteur_ID = ? AND vu = ? "
					+ "GROUP BY Emeteur_ID");
			
			pre.setInt(1,this.getId());
			pre.setInt(2,0);
			
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				int tab[] = new int[2]; 
				int id = res.getInt(1);
				int nb = res.getInt(2);
				tab[0] = id;
				tab[1] = nb;
				array.add(tab);
			}
			return array;	
		}
		
		public int tousLu(int idAmi) throws SQLException, ClassNotFoundException{
			connect = ConnectionBD.getConnectionBD();
			PreparedStatement pre = null;
			pre = (PreparedStatement) connect.getConnect().prepareStatement
					("UPDATE message set vu = ? "
					+ "WHERE Emeteur_ID = ? AND Recepteur_ID = ?");
			
			pre.setInt(1, 1);
			pre.setInt(2, idAmi);
			pre.setInt(3, this.getId());
			
			return pre.executeUpdate();
		}
		
		//public Arr
	/**
	 * nombre d'amis
	 * @return
	 * @throws SQLException
	 */
	public int nbAmis() throws SQLException{
		ResultSet res = connect.Query("select MAX(Amis_ID) from amis where Autilisateur_ID = '"+this.id+"'");
		res.next();
		return res.getInt(1);
	}
	/**
	 * recuperation de tout les amis de ses amis
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> toutlesAmis() throws SQLException{
		ArrayList<Utilisateur> amisdamis= new ArrayList<>();
		ResultSet res = connect.Query("select * from utilisateur "
				+ "where Utilisateur_ID <> '"+this.id+"'");
		while(res.next()){
			amisdamis.add(new Utilisateur(
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
		return amisdamis;
	}
	
	public ArrayList<Document> toutLesDocuments(Utilisateur utili) throws SQLException, ClassNotFoundException{
		ArrayList<Document> ttdoc= new ArrayList<>();
		ResultSet res = connect.Query("SELECT * FROM document");
	
		while(res.next()){
			utili.setId(res.getInt(3));
		ttdoc.add(new Document(res.getInt(1),res.getString(2),utili,res.getInt(4),res.getDate(5)));
		
		}
		return ttdoc;
	}
	
	public ArrayList<Utilisateur> rechercheAmis(String recherche) throws SQLException{
		ArrayList<Utilisateur> proposition = new ArrayList<>();
		ResultSet res = connect.Query("select * from utilisateur where Utilisateur_Name LIKE '"+recherche+"*', OR Utilisateur_Pseudo LIKE '"+recherche+"*'");
		
		while(res.next()){
			proposition.add(new Utilisateur(
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
		return proposition;
	}
	
	
	public boolean autorisationModif(int iddoc) throws SQLException {
		System.out.println("passage  ok");
		ResultSet res = connect.Query("SELECT* "
				+ "FROM accesdoc "
				+ "WHERE  Contributeur_ID='"+this.id+"' AND Document_Id='"+iddoc+"'");
		if(res.next()){
			
			System.out.println("user ");
			return true;
		}
	return false;
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
