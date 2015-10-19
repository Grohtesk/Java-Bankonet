package com.bankonet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public abstract class Compte implements CompteStat {
	
	private String numero;
	private String intitule;
	private double solde;
	public final static String COMPTE_PROPERTIES_URL="../bankonet-lib/compte.properties";
	
	public Compte() {
	}
	
	public Compte(String numero, String intitule, double solde) {
		if(solde<0){
			System.out.println("On ne peut pas initialiser un compte en n�gatif.");
			solde=0;
		}
		this.numero = numero;
		this.intitule = intitule;
		this.solde = solde;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str="n� compte : "+this.numero+"; Solde : "+solde+"; Intitul� : "+this.intitule;
		return str;
	}
	
	public double crediter(double montant) throws CreditException{
		this.solde+=montant;
		return this.solde;
	}
	public double debiter(double montant) throws DebitException {
		
		double debitmax=debitMax();
		
		if(montant > debitmax){
			throw new DebitException("Erreur, solde insuffisant. D�bit max autoris� : "+debitmax);
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

	public void save() {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			// import existing file
			FileInputStream in = new FileInputStream(COMPTE_PROPERTIES_URL);
			prop.load(in);
			in.close();
			
			output = new FileOutputStream(COMPTE_PROPERTIES_URL);
			
			// set the properties value
			prop.setProperty(this.numero, this.concatCompte());

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			
			try {
				output = new FileOutputStream(COMPTE_PROPERTIES_URL);
	
				// set the properties value
				prop.setProperty(this.numero, this.concatCompte());
	
				// save properties to project root folder
				prop.store(output, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TODO stocker les comptes
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private String concatCompte() {		
		return new String("numero:"+this.numero+"&intitule:"+this.intitule+"&solde:"+this.solde);
	}
	
}
