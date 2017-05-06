package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Commentaire;
import model.Document;

/**
 * Servlet implementation class ChargerCommentaire
 */
@WebServlet("/ChargerCommentaire")
public class ChargerCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargerCommentaire() {
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
		
		int id = Integer.parseInt(request.getParameter("doc"));
		
		try {
			Document doc = new Document(id);
			ArrayList<Commentaire> list = doc.getCommentaireDoc();
			
			response.getWriter().write("<list>");
			for (int i = 0; i < list.size(); i++) {
				Commentaire com = list.get(i);
				response.getWriter().write("<com>"
						+ "<cont>"+com.getContenu()+"</cont>"
						+ "<date>"+com.getDateCommentaire()+"</date>"
						+ "<idU>"+com.getExpediteurCom().getId()+"</idU>"
						+ "<pseudo>"+com.getExpediteurCom().getPseudo()+"</pseudo>"
						+ "</com>");
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
