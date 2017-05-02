package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("passage");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("mdp");
			String email = request.getParameter("email");
			String dateN = request.getParameter("dateN");
			
			String[] d = dateN.split("-");
			int Y = Integer.parseInt(d[2]);
			int M = Integer.parseInt(d[1]);
			int J = Integer.parseInt(d[0]);
			Date date = new Date(Y,M,J);
			
			
			System.out.println("passage");
			try {
				Utilisateur user = new Utilisateur();
				
				System.out.println(Y+"-"+M+"-"+J);
		
				int ok = user.inscription(nom, prenom,  new Date(Y,M,J), pseudo, mdp, email);
				
				if(user.identifier(pseudo, mdp)){
					System.out.println("passage inscrip "+ok);
				user.getId();
				HttpSession session = request.getSession();
				session.setAttribute("id", user.getId());
				session.setAttribute("nom", nom);
				session.setAttribute("prenom", prenom);
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("mdp", mdp);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			response.sendRedirect("Connection");
	}

}
