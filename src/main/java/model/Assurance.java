package model;

import java.time.LocalDate;
import java.util.Objects;

public class Assurance {
	private int id;
    private String numeroCompte;
    private String typeAssurance;
    private double montantInitial;
    private double montantMensuel;
    private double montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String etat;
    
    public Assurance( String numeroCompte, String typeAssurance, double montantInitial, double montantMensuel,
			double montant,LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.numeroCompte = numeroCompte;
		this.typeAssurance = typeAssurance;
		this.montantInitial = montantInitial;
		this.montantMensuel = montantMensuel;
		this.montant = montant;
		this.dateDebut =dateDebut;
		this.dateFin = dateFin;
		this.etat ="actif";
	}
    public Assurance(int id, String numeroCompte, String typeAssurance, double montantInitial, double montantMensuel,
			double montant, LocalDate dateDebut, LocalDate dateFin, String etat) {
		super();
		this.id = id;
		this.numeroCompte = numeroCompte;
		this.typeAssurance = typeAssurance;
		this.montantInitial = montantInitial;
		this.montantMensuel = montantMensuel;
		this.montant = montant;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}
	
	
	
	public Assurance() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getTypeAssurance() {
		return typeAssurance;
	}
	public void setTypeAssurance(String typeAssurance) {
		this.typeAssurance = typeAssurance;
	}
	public double getMontantInitial() {
		return montantInitial;
	}
	public void setMontantInitial(double montantInitial) {
		this.montantInitial = montantInitial;
	}
	public double getMontantMensuel() {
		return montantMensuel;
	}
	public void setMontantMensuel(double montantMensuel) {
		this.montantMensuel = montantMensuel;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assurance other = (Assurance) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Assurance [id=" + id + ", numeroCompte=" + numeroCompte + ", typeAssurance=" + typeAssurance
				+ ", montantInitial=" + montantInitial + ", montantMensuel=" + montantMensuel + ", montant=" + montant
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", etat=" + etat + "]";
	}
    
	

}
