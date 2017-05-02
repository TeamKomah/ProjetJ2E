package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.Utilisateur;

/**
 * Servlet implementation class Communiquer
 */
@WebServlet("/Communiquer")
public class Communiquer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Communiquer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int idR = Integer.parseInt(request.getParameter("id"));
		try {
			Utilisateur exp = new Utilisateur((int) session.getAttribute("id"));
			Utilisateur recep = new Utilisateur(idR);
			int a = exp.tousLu(idR);
			 			
			request.setAttribute("mesdoc", exp.mesDocuments(exp));
			request.setAttribute("amis", exp.listAmis());
			request.setAttribute("nonLu", exp.getMessageNonLu());
			request.setAttribute("expediteur", exp);
			request.setAttribute("recepteur", recep);
			request.setAttribute("communication", exp.getCommunication(idR));
			this.getServletContext().getRequestDispatcher("/WEB-INF/communiquer.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
