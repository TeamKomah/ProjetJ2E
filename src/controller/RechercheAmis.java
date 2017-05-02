package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;

/**
 * Servlet implementation class RechercheAmis
 */
@WebServlet("/RechercheAmis")
public class RechercheAmis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheAmis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		HttpSession session = request.getSession();
		String rech = request.getParameter("text");
		try {
			Utilisateur user = new Utilisateur((int) session.getAttribute("id"));
			ArrayList<Utilisateur> list = user.rechercheAmis(rech);
			response.getWriter().write("<list>");
			for (int i = 0; i < list.size(); i++) {
				Utilisateur amis = list.get(i);
				response.getWriter().write("<ami>"
						+ "<id>"+amis.getId()+"</id>"
						+ "<nom>"+amis.getNom()+"</nom>"
						+ "<prenom>"+amis.getPrenom()+"</prenom>"
						+ "</ami>");
			}
			response.getWriter().write("</list>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
