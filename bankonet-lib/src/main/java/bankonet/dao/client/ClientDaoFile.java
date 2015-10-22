package bankonet.dao.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.bankonet.Client;
import com.bankonet.Compte;

import bankonet.dao.compte.CompteDaoFile;

public class ClientDaoFile implements ClientDao {

	@Override
	public Client getClient(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAll() {
		
		List<Client> list= new ArrayList<Client>();
		
		// import existing file
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("url");
			
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

		        CompteDaoFile compteDao=new CompteDaoFile();
		        Client client=new Client(idendifiant,nom,prenom,password);
		        
		        String[] comptetArray=comptes.split(",");
		        
		        for (int i = 0; i < comptetArray.length; i++) {
		        	client.addCompte(compteDao.getCompte(comptetArray[i]));
				}
		        
		        list.add(client);
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	@Override
	public void remove(Client client) {
		// TODO Auto-generated method stub
		
	}

	public void save(Client client) {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			// import existing file
			FileInputStream in = new FileInputStream("url");
			prop.load(in);
			in.close();
			
			output = new FileOutputStream("url");
			
			// set the properties value
			prop.setProperty(client.getIdentifiant(), concatClient(client));

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			
			try {
				output = new FileOutputStream("url");
	
				// set the properties value
				prop.setProperty(client.getIdentifiant(), concatClient(client));
	
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


	private String concatClient(Client client) {
		String comptes="";
		Iterator<Entry<String, Compte>> it = null;
	    while (it.hasNext()) {
	        Map.Entry<String,Compte> pair = (Map.Entry<String,Compte>)it.next();
	        comptes+=pair.getValue().getNumero()+",";
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    comptes=comptes.substring(0, comptes.length()-1);
		String str="identifiant:" + client.getIdentifiant() + "&nom:" + client.getNom() + "&prenom:" + client.getPrenom() + "&password:" + client.getPassword()	+ "&listeComptes:" + comptes;
				
		return str;
	}

	@Override
	public List<Client> findByName(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByFirstname(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		
	}
	

}
