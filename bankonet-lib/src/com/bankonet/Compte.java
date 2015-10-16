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
	private final static String COMPTE_PROPERTIES_URL="../bankonet-lib/compte.properties";
	
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

	public static HashMap<String, Compte> retriveComptes() {
		HashMap<String,Compte> map=new HashMap<String,Compte>();
		
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(COMPTE_PROPERTIES_URL);
			prop.load(in);
			in.close();
			
			
			Iterator it = prop.entrySet().iterator();
		    while (it.hasNext()) {
		    	HashMap<String,String> compteMap=new HashMap<String,String>();
		        Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();
		        String compteString=entry.getValue();
		        String[] compteArray=compteString.split("&");
		        
		        for (int i = 0; i < compteArray.length; i++) {
		        	String[] a=compteArray[i].split(":");
					compteMap.put(a[0], a[1]);
				}
		        String numero=compteMap.get("numero");
		        String intitule=compteMap.get("intitule");
		        double solde=Double.parseDouble(compteMap.get("solde"));
		        
		        map.put(numero, new CompteCourant(numero,intitule,solde,0d));
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return map;
	}
	
}
