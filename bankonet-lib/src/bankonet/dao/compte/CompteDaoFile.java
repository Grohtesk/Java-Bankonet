package bankonet.dao.compte;

import java.io.FileInputStream;
import java.io.IOException;
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

	@Override
	public void save(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	
}
