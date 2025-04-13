package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompteDAO;
import model.Compte;

/**
 * Servlet implementation class CompteModServlet
 */
@WebServlet("/CompteModServlet")
public class CompteModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteModServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String numeroCompte = request.getParameter("numeroCompte");
	      //  System.out.println("Numero de compte à modifier : " + numeroCompte);
	        
	        CompteDAO compteDAO = new CompteDAO();
	        Compte compte = compteDAO.findByNumeroCompte(numeroCompte);
	        
	        request.setAttribute("compte", compte);
	      //  System.out.println("compte à modifier : " + compte);
     request.getRequestDispatcher("modifierCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		String numeroCompte =request.getParameter("numeroCompte"); System.out.println("numeroCompte : " + numeroCompte);
	    String nom = request.getParameter("nom");System.out.println("nom : " + nom);
	    String prenom = request.getParameter("prenom");System.out.println("prenom : " + prenom);
	    String cin = request.getParameter("cin");System.out.println("cin : " + cin);
	    String email = request.getParameter("email");System.out.println("email : " + email);
	    String numeroTelephone = request.getParameter("numeroTelephone");System.out.println("numeroTelephone : " + numeroTelephone);
	    String typeCompte = request.getParameter("typeCompte");System.out.println("typeCompte : " + typeCompte);
	    double decouvert = 0;
	    double taux = 0;

	    if ("courant".equals(typeCompte)) {
	        decouvert = Double.parseDouble(request.getParameter("decouvert"));System.out.println("decouvert : " + decouvert);
	    } else if ("epargne".equals(typeCompte)) {
	        taux = Double.parseDouble(request.getParameter("taux"));System.out.println("taux : " + taux);
	    }

	    double soldeCompte = Double.parseDouble(request.getParameter("soldeCompte"));System.out.println("soldeCompte : " + soldeCompte);

	    // Construire un nouvel objet Compte
	    Compte compte = new Compte();
	    compte.setNumeroCompte(numeroCompte);
	    compte.setNom(nom);
	    compte.setPrenom(prenom);
	    compte.setCin(cin);
	    compte.setEmail(email);
	    compte.setNumeroTelephone(numeroTelephone);
	    compte.setTypeCompte(typeCompte);
	   compte.setDecouvert(decouvert);
	    compte.setTaux(taux);
	    compte.setSoldeCompte(soldeCompte);
	   System.out.println("compte"+compte);
	    // Mettre à jour le compte dans la base de données
	    CompteDAO compteDAO = new CompteDAO();
	    boolean modificationReussie = compteDAO.update(compte);
	    if (modificationReussie){
	       request.setAttribute("successMessage", "Modification réussie !");
	    } else {
	        request.setAttribute("errorMessage", "Échec de la modification !");
	    }
        request.getRequestDispatcher("message.jsp").forward(request, response);

	}

}
