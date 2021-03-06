package com.bankonet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="client")
@NamedQueries({
	@NamedQuery(name = "Client.getClientByLogin", query = "SELECT c FROM Client c WHERE c.identifiant=:identifiant"),
	@NamedQuery(name = "Client.getClientByFirstname", query = "SELECT c FROM Client c WHERE c.prenom LIKE '%:prenom%'"),
	@NamedQuery(name = "Client.getClientByName", query = "SELECT c FROM Client c WHERE c.nom=:nom")
	
})
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="client",cascade = CascadeType.ALL)
	private List<Compte> listeComptes;
	
	@Column(name="identifiant",length=25,unique=true)
	private String identifiant;
	
	@Column(name="nom",length=25)
	private String nom;
	
	@Column(name="prenom",length=20)
	private String prenom;
	
	@Column(name="password",length=20)
	private String password;
	
	@Transient
	private int nbCompteCourrant;
	@Transient
	private int nbCompteEpargne;
//	public final static String CLIENTPROPERTIESURL="../bankonet-lib/client.properties";
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public Client(String identifiant, String nom, String prenom, String password) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.listeComptes=new ArrayList<Compte>();
	}

	

	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public void addCompte(Compte compte) {
		this.listeComptes.add(compte);
	}
	
	/// GET SET ///
	
	public String getIdentifiant() {
		return identifiant;
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

	public int getNbCompteCourrant() {
		return nbCompteCourrant;
	}

	public void setNbCompteCourrant(int nbCompteCourrant) {
		this.nbCompteCourrant = nbCompteCourrant;
	}

	public int getNbCompteEpargne() {
		return nbCompteEpargne;
	}

	public void setNbCompteEpargne(int nbCompteEpargne) {
		this.nbCompteEpargne = nbCompteEpargne;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Id : "+id+", Nom : "+ nom.toUpperCase() + " " + prenom + ", identifiant : " + identifiant	+ ". comptes courants : " + nbCompteCourrant+", comptes epargne : "+nbCompteEpargne;
	}

	public void consulterSoldes() {
		// TODO Auto-generated method stub
		int i=0;
		for (Compte compte : listeComptes) {
			i++;
	    	System.out.println(i+". Compte n�"+compte.getNumero()+" : solde="+compte.getSolde());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	
}
