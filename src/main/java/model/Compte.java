package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Compte {
	private String numeroCompte;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String numeroTelephone;
    private String typeCompte;
    private double decouvert; // attribut spécifique aux comptes courants
    private double taux; // attribut spécifique aux comptes d'épargne
    private double soldeCompte;
    private LocalDate dateCreationCompte;
    
    private static int numeroCompteCounter = 1003;
    
	public Compte() {
		super();
	}
	public Compte(String nom, String prenom, String cin, String email, String numeroTelephone,
			String typeCompte, double decouvert, double taux, double soldeCompte) {
		super();
		this.numeroCompte = genererNumeroCompte();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.email = email;
		this.numeroTelephone = numeroTelephone;
		this.typeCompte = typeCompte;
		if ("courant".equals(typeCompte)) {
            this.decouvert = decouvert;
            this.taux = 0; // pas applicable pour les comptes courants
        } else if ("epargne".equals(typeCompte)) {
            this.decouvert = 0; // pas de découvert pour les comptes d'épargne
            this.taux = taux; 
        }
		this.soldeCompte = soldeCompte;
		this.dateCreationCompte = LocalDate.now();
	}
	public Compte(String nom, String prenom, String cin, String email, String numeroTelephone, double decouvert,
			double taux, double soldeCompte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.email = email;
		this.numeroTelephone = numeroTelephone;
		this.decouvert = decouvert;
		this.taux = taux;
		this.soldeCompte = soldeCompte;
	}
	public Compte(String nom, String prenom, String cin, String email, String numeroTelephone,
			String typeCompte, double soldeCompte) {
		super();
		this.numeroCompte = genererNumeroCompte();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.email = email;
		this.numeroTelephone = numeroTelephone;
		this.typeCompte = typeCompte;
		this.soldeCompte = soldeCompte;
		this.dateCreationCompte = LocalDate.now();
	}
	 private String genererNumeroCompte() {
	        numeroCompteCounter++;
	        return "ACC-" + numeroCompteCounter;
	    }
	
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumeroTelephone() {
		return numeroTelephone;
	}
	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public double getSoldeCompte() {
		return soldeCompte;
	}
	public void setSoldeCompte(double soldeCompte) {
		this.soldeCompte = soldeCompte;
	}
	public LocalDate getDateCreationCompte() {
		return dateCreationCompte;
	}
	public void setDateCreationCompte(LocalDate dateCreationCompte) {
		this.dateCreationCompte = dateCreationCompte;
	}
	
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	@Override
	public int hashCode() {
		return Objects.hash(numeroCompte);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(numeroCompte, other.numeroCompte);
	}
	@Override
	public String toString() {
		return "Compte [numeroCompte=" + numeroCompte + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin
				+ ", email=" + email + ", numeroTelephone=" + numeroTelephone + ", typeCompte=" + typeCompte
				+ ", decouvert=" + decouvert + ", tauxInteret=" + taux + ", soldeCompte=" + soldeCompte
				+ ", dateCreationCompte=" + dateCreationCompte + "]";
	}
	
}
