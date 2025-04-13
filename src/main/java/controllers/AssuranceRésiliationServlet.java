package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssuranceDAO;

/**
 * Servlet implementation class AssuranceRésiliationServlet
 */
@WebServlet("/AssuranceRésiliationServlet")
public class AssuranceRésiliationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssuranceRésiliationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("assuranceId"+id);
        AssuranceDAO assuranceDAO = new AssuranceDAO();
        boolean modificationReussie = assuranceDAO.updateEtatAssurance(id, "inactif");

        if (modificationReussie) {
	        request.setAttribute("successMessage", "Modification réussie !");
	    } else {
	        request.setAttribute("errorMessage", "Échec de la modification !");
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
