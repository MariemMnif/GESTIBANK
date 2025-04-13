package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompteDAO;

/**
 * Servlet implementation class CompteSuppServlet
 */
@WebServlet("/CompteSuppServlet")
public class CompteSuppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteSuppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String numeroCompte = request.getParameter("numeroCompte");
	       System.out.println("Numéro de compte à supprimer : " + numeroCompte);
	        CompteDAO compteDAO = new CompteDAO();
	        boolean suppressionReussie = compteDAO.delete(numeroCompte);
	        System.out.println("test");
	        if (suppressionReussie){
	            // Définir un message de succès et le passer à la page JSP
	            request.setAttribute("successMessage", "Suppression réussie !");
	        } else {
	            // Définir un message d'erreur et le passer à la page JSP
	            request.setAttribute("errorMessage", "Échec de la suppression !");
	        }
	        request.getRequestDispatcher("message.jsp").forward(request, response);
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
