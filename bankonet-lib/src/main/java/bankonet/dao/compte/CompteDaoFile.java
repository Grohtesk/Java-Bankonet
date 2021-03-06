package bankonet.dao.compte;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bankonet.Client;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;

public class CompteDaoFile implements CompteDao {

	@Override
	public Compte getCompte(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Compte> getAll() {
		List<Compte> list=new ArrayList<Compte>();
		
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(Compte.COMPTE_PROPERTIES_URL);
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
		        
		        list.add(new CompteCourant(numero,intitule,solde,0d));
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public void remove(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	public void save(Compte compte) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			// import existing file
			FileInputStream in = new FileInputStream(Compte.COMPTE_PROPERTIES_URL);
			prop.load(in);
			in.close();
			
			output = new FileOutputStream(Compte.COMPTE_PROPERTIES_URL);
			
			// set the properties value
			prop.setProperty(compte.getNumero(), compte.concatCompte());

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			
			try {
				output = new FileOutputStream(Compte.COMPTE_PROPERTIES_URL);
	
				// set the properties value
				prop.setProperty(compte.getNumero(), compte.concatCompte());
	
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

	
}
