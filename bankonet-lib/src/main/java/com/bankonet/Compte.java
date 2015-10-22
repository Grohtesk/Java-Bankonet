package com.bankonet;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="compte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public abstract class Compte implements CompteStat {
	
	@Id
	@GeneratedValue
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	Client clientId;
	
	@Column(name="numero",length=20)
	private String numero;

	@Column(name="intitule",length=20)
	private String intitule;
	
	@Column(name="solde")
	private double solde;
	
	@Transient
	public static final String COMPTE_PROPERTIES_URL = null;


	
//	public final static String COMPTE_PROPERTIES_URL="../bankonet-lib/compte.properties";
	
	public Compte() {
	}
	
	public Compte(String numero, String intitule, double solde) {
		if(solde<0){
			System.out.println("On ne peut pas initialiser un compte en négatif.");
			solde=0;
		}
		this.numero = numero;
		this.intitule = intitule;
		this.solde = solde;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str="n° compte : "+this.numero+"; Solde : "+solde+"; Intitulé : "+this.intitule;
		return str;
	}
	
	public double crediter(double montant) throws CreditException{
		this.solde+=montant;
		return this.solde;
	}
	public double debiter(double montant) throws DebitException {
		
		double debitmax=debitMax();
		
		if(montant > debitmax){
			throw new DebitException("Erreur, solde insuffisant. Débit max autorisé : "+debitmax);
		}
		
		this.solde-=montant;
		return this.solde;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	abstract double debitMax();
	
	public void effectuerVirement(Compte compte, double montant) throws CompteException {
		compte.debiter(montant);
		this.crediter(montant);
	}

	public String concatCompte() {		
		return new String("numero:"+this.numero+"&intitule:"+this.intitule+"&solde:"+this.solde);
	}

	
	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
}
