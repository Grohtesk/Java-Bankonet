package com.bankonet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Client {
	private String identifiant;
	private String nom;
	private String prenom;
	private String password;
	private Map<String,Compte> listeComptes;
	private int nbCompteCourrant;
	private int nbCompteEpargne;
	public final static String CLIENTPROPERTIESURL="../bankonet-lib/client.properties";
	
	public Client(String identifiant, String nom, String prenom, String password) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.listeComptes=new HashMap<String,Compte>();
	}

	

	public Map<String, Compte> getListeComptes() {
		return listeComptes;
	}

	public void addCompte(Compte compte) {
		this.listeComptes.put(compte.getNumero(), compte);
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
		return nom.toUpperCase() + " " + prenom + ", identifiant : " + identifiant	+ ". comptes courants : " + nbCompteCourrant+", comptes epargne : "+nbCompteEpargne;
	}

	public void consulterSoldes() {
		// TODO Auto-generated method stub
		Iterator<Entry<String, Compte>> it = this.listeComptes.entrySet().iterator();
		int i=0;
	    while (it.hasNext()) {
	    	i++;
	    	Compte compte=it.next().getValue();
	    	System.out.println(i+". Compte n�"+compte.getNumero()+" : solde="+compte.getSolde());
	    }
	}
	
	
}
