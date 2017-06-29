package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;

/**
 * Servlet implementation class Telechargement
 */
@WebServlet("/Telechargement")
public class Telechargement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Telechargement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		int id = (int) session.getAttribute("id");
//		String nom = (String) session.getAttribute("nom");
//		String prenom = (String) session.getAttribute("prenom");
//		String chemin = this.getServletConfig().getInitParameter("chemin");

		Document doc1;
		
		int iddoc = Integer.parseInt(request.getParameter("iddoc"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		try {
			doc1 = new Document();
			doc1.setId(Integer.parseInt(request.getParameter("iddoc")));
			if(request.getParameter("iddocVersio") != null){
				doc1.recupererDocVersio();
				request.setAttribute("iddocVersion", "1");
			}
			else{
				doc1.recupererDoc();
			}
			
			System.out.println(""+doc1.getId()+" "+doc1.getNom());
			File fic = new File("C:/stockjee/"+doc1.getNom());
			
			response.setHeader("Content-Disposition", "attachment; filename="+doc1.getNom());
			
			InputStream is = null;
			OutputStream os = null;
			try{
				is = new FileInputStream(fic);
				os = response.getOutputStream();
				byte[] tmp = new byte[4096];
				int longueur;
				while((longueur = is.read(tmp)) > 0){
					os.write(tmp, 0, longueur);
				}		
			}finally{
				if(is!=null){
				is.close();
				os.close();
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("DocumentS?iddoc="+iddoc+"&userid="+userid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
