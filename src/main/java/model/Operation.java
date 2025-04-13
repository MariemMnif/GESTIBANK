package model;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {
	    private int id;
	    private int numOperation; 
	    private String numCompte; 
	    private String type;
	    private double montant;
	    private LocalDate date;
	    
		public Operation( int numOperation, String numCompte, String type, double montant) {
			super();
			this.numOperation = numOperation;
			this.numCompte = numCompte;
			this.type = type;
			this.montant = montant;
			this.date = LocalDate.now();
		}
		public Operation(int numOperation, String numCompte, String type, double montant, LocalDate date) {
			super();
			this.numOperation = numOperation;
			this.numCompte = numCompte;
			this.type = type;
			this.montant = montant;
	
		}
		public Operation() {
			super();
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getNumOperation() {
			return numOperation;
		}
		public void setNumOperation(int numOperation) {
			this.numOperation = numOperation;
		}
		public String getNumCompte() {
			return numCompte;
		}
		public void setNumCompte(String numCompte) {
			this.numCompte = numCompte;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getMontant() {
			return montant;
		}
		public void setMontant(double montant) {
			this.montant = montant;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		@Override
		public int hashCode() {
			return Objects.hash(date);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Operation other = (Operation) obj;
			return Objects.equals(date, other.date);
		}
		@Override
		public String toString() {
			return "Operation [id=" + id + ", numOperation=" + numOperation + ", numCompte=" + numCompte + ", type="
					+ type + ", montant=" + montant + ", date=" + date + "]";
		}
	    
		

}
