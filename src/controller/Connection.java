package controller;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.ConnectionBD;
import model.Utilisateur;

/**
 * Servlet implementation class Connection
 */
//@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	if(session.getAttribute("id")!=null){
		try {
			Utilisateur user = new Utilisateur();
			user.setId((int)(session.getAttribute("id")));
			request.setAttribute("amis", user.listAmis());
			request.setAttribute("nonLu", user.getMessageNonLu());
			request.setAttribute("Documents", user.toutLesDocuments(new Utilisateur()));
			if(request.getParameter("profdoc") != null){
				
				user.setId(Integer.parseInt(request.getParameter("profdoc")));
				request.setAttribute("mesdocs",user.mesDocuments(user));
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			}
			
			else if(request.getParameter("doc") != null){
				
				request.setAttribute("mesdocs",user.mesDocuments(user));
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			}
			else if(request.getParameter("docsparta") != null){
				
				request.setAttribute("docspartager",user.listDocPartager(user));
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			}
			else if(request.getParameter("ajAmi")!=null){
				request.setAttribute("lesAmisDamis", user.toutlesAmis());
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			}
			 if(request.getParameter("aj") != null){
				 this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			 }
			 if(request.getParameter("ajoutAmi") != null){
				 int idd = Integer.parseInt(request.getParameter("ajoutAmi"));
				 String Anom = request.getParameter("Anom");
				 String Aprenom = request.getParameter("Aprenom");
				 Utilisateur ami = new Utilisateur();
				 ami.setId(idd);
				 ami.setNom(Anom);
				 ami.setPrenom(Aprenom);
				 user.ajouterAmi(ami);
				 this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			 }
			 
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}
	else{
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		
		try {
			
			Utilisateur user = new Utilisateur();
			HttpSession session = request.getSession();
			
			if(user.identifier(pseudo, mdp)){
				
				session.setAttribute("nom", user.getNom());
				session.setAttribute("prenom", user.getPrenom());
				session.setAttribute("id", user.getId());
				request.setAttribute("amis", user.listAmis());
				request.setAttribute("Documents", user.toutLesDocuments(new Utilisateur()));
				request.setAttribute("nonLu", user.getMessageNonLu());
				
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}
			else{
				
				System.out.println("identifiant ou mot de passe incorrect");
				this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("Erreur de connection à la Base de Donnes");
			e.printStackTrace();
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			System.out.println("un probleme est survenu lors de la connection");
			System.out.println("ressayer la connection");
			e.printStackTrace();
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
		
	}
}
