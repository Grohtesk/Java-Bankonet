package com.bankonet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Client {
	private String identifiant;
	private String nom;
	private String prenom;
	private String password;
	private Map<String,Compte> listeComptes;
	private int nbCompteCourrant;
	private int nbCompteEpargne;
	private final static String CLIENTPROPERTIESURL="../bankonet-lib/client.properties";
	
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



	public void save() {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			// import existing file
			FileInputStream in = new FileInputStream(CLIENTPROPERTIESURL);
			prop.load(in);
			in.close();
			
			output = new FileOutputStream(CLIENTPROPERTIESURL);
			
			// set the properties value
			prop.setProperty(this.identifiant, this.concatClient());

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			
			try {
				output = new FileOutputStream(CLIENTPROPERTIESURL);
	
				// set the properties value
				prop.setProperty(this.identifiant, this.concatClient());
	
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
	
	
	public String concatClient() {
		String comptes="";
		Iterator it = listeComptes.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,Compte> pair = (Map.Entry<String,Compte>)it.next();
	        comptes+=pair.getValue().getNumero()+",";
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    comptes=comptes.substring(0, comptes.length()-1);
		String str="identifiant:" + identifiant + "&nom:" + nom + "&prenom:" + prenom + "&password:" + password	+ "&listeComptes:" + comptes;
		
		
		return str;
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
	
	public static HashMap<String,Client> retriveClients() {
		HashMap<String,Client> map=new HashMap<String,Client>();
		
		// import existing file
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(CLIENTPROPERTIESURL);
			prop.load(in);
			in.close();
			
			
			Iterator it = prop.entrySet().iterator();
		    while (it.hasNext()) {
		    	HashMap<String,String> clientMap=new HashMap<String,String>();
		        Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();
		        String clientString=entry.getValue();
		        String[] clientArray=clientString.split("&");
		        
		        for (int i = 0; i < clientArray.length; i++) {
		        	String[] a=clientArray[i].split(":");
					clientMap.put(a[0], a[1]);
				}
		        String idendifiant=clientMap.get("identifiant");
		        String nom=clientMap.get("nom");
		        String prenom=clientMap.get("prenom");
		        String password=clientMap.get("password");
		        String comptes=clientMap.get("listeComptes");
		        
		        HashMap<String,Compte> compteMap=Compte.retriveComptes();
		        
		        Client client=new Client(idendifiant,nom,prenom,password);
		        
		        String[] comptetArray=comptes.split(",");
		        
		        for (int i = 0; i < comptetArray.length; i++) {
		        	client.addCompte(compteMap.get(comptetArray[i]));
				}
		        
		        map.put(idendifiant, client);
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
		return map;
	}
	
	public static Client connect(String login, String password) throws ClientNonTrouveException {
		// TODO Auto-generated method stub
		HashMap<String,Client> mapClient=retriveClients();
		
		if(mapClient.containsKey(login)){
			Client client=mapClient.get(login);
			if(client.getPassword().equals(password)){
				return client;
			}else{
				throw new ClientNonTrouveException("Connexion impossible");
			}
		}else throw new ClientNonTrouveException();
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
