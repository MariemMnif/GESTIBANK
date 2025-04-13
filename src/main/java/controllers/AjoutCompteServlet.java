package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompteDAO;
import model.Compte;

/**
 * Servlet implementation class AjoutCompteServlet
 */
@WebServlet("/AjoutCompteServlet")
public class AjoutCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.getRequestDispatcher("code.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String cin = request.getParameter("cin");
	        String email = request.getParameter("email");
	        String numeroTelephone = request.getParameter("numeroTelephone");
	        String typeCompte = request.getParameter("typeCompte");
	        double decouvert = 0;
	        double taux = 0;
	        System.out.println(nom);
	        System.out.println(decouvert);
	        if ("courant".equals(typeCompte)) {
	            decouvert = Double.parseDouble(request.getParameter("decouvert"));
	        } else if ("epargne".equals(typeCompte)) {
	            taux = Double.parseDouble(request.getParameter("taux"));
	        }
	        double soldeCompte = Double.parseDouble(request.getParameter("soldeCompte"));
	        
	        System.out.println(nom+prenom+cin+email+numeroTelephone+typeCompte+"decouvert         "+decouvert+"taux     "+taux+"          "+soldeCompte);

	        // Créer un objet Compte avec les paramètres
	        Compte compte = new Compte(nom, prenom, cin, email, numeroTelephone, typeCompte, decouvert, taux, soldeCompte);

	        // Enregistrer le compte dans la base de données
	        CompteDAO compteDAO = new CompteDAO();

	        boolean success = compteDAO.save(compte);

	        if (success) {
	            // Définir un message de succès et le passer à la page JSP
	            request.setAttribute("successMessage", "Compte ajouté avec succès");
	        } else {
	            // Définir un message d'erreur et le passer à la page JSP
	            request.setAttribute("errorMessage", "Erreur lors de l'ajout du compte");
	        }

	        // Rediriger vers la page JSP
	        request.getRequestDispatcher("message.jsp").forward(request, response);
	    }
	}


