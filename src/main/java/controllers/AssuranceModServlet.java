package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssuranceDAO;
import dao.CompteDAO;
import model.Assurance;
import model.Compte;

/**
 * Servlet implementation class AssuranceModServlet
 */
@WebServlet("/AssuranceModServlet")
public class AssuranceModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssuranceModServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 AssuranceDAO assuranceDAO = new AssuranceDAO();
		 int assuranceId = Integer.parseInt(request.getParameter("id"));
		    Assurance assurance= assuranceDAO.findAssuranceByID(assuranceId); 
	     request.setAttribute("assurance", assurance);
         request.getRequestDispatcher("modifierAssurance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String numeroCompte = request.getParameter("numeroCompte");
	    
	    // Récupérer le compte correspondant au numéro de compte
	    CompteDAO compteDAO = new CompteDAO();
	    Compte compte = compteDAO.findByNumeroCompte(numeroCompte);

	    // Récupérer l'assurance correspondant au numéro de compte
	    AssuranceDAO assuranceDAO = new AssuranceDAO();
	    Assurance assurancebd = assuranceDAO.findAssuranceByNumeroCompte(numeroCompte);
	    double montantInitialBD = assurancebd.getMontantInitial();
	    
	    // Récupérer les paramètres du formulaire
	    String typeAssurance = request.getParameter("typeAssurance");
	    double montantInitial = Double.parseDouble(request.getParameter("montantInitial"));
	    double montantMensuel = 0; // Initialiser à 0 par défaut
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
	    LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
	  
	    if ("retraite".equals(typeAssurance)) {
	        montantMensuel = Double.parseDouble(request.getParameter("montantMensuel"));
	    } 
	    else if ("voyage".equals(typeAssurance)) {
	    	 double differenceMontant = montantInitial - montantInitialBD;
	    	    double nouveauSolde;
	    	    if (montantInitial > montantInitialBD) {
	    	        nouveauSolde = compte.getSoldeCompte() - differenceMontant;
	    	    } else {
	    	        nouveauSolde = compte.getSoldeCompte() + differenceMontant;
	    	    }
	    	    compte.setSoldeCompte(nouveauSolde);
	    	    compteDAO.updateSolde(compte.getNumeroCompte(), nouveauSolde);
	    }
	   
	    // Créer un objet Assurance avec les nouvelles valeurs
	    Assurance assurance = new Assurance();
	    assurance.setNumeroCompte(numeroCompte);
	    assurance.setTypeAssurance(typeAssurance);
	    assurance.setMontantInitial(montantInitial);
	    assurance.setMontantMensuel(montantMensuel);
	    assurance.setDateDebut(dateDebut);
	    assurance.setDateFin(dateFin);


	    // Mettre à jour l'assurance dans la base de données
	    AssuranceDAO assuranceDAO2 = new AssuranceDAO();
	    boolean modificationReussie = assuranceDAO2.update(assurance);

	    // Gérer les messages de succès ou d'erreur
	    if (modificationReussie) {
	        request.setAttribute("successMessage", "Modification réussie !");
	    } else {
	        request.setAttribute("errorMessage", "Échec de la modification !");
	    }
	    request.getRequestDispatcher("message.jsp").forward(request, response);
	}


}
