package bankonet.dao.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import com.bankonet.Client;

public class ClientDaoFile implements ClientDao {

	@Override
	public void setClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client getClient(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
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
			FileInputStream in = new FileInputStream(Client.CLIENTPROPERTIESURL);
			prop.load(in);
			in.close();
			
			output = new FileOutputStream(Client.CLIENTPROPERTIESURL);
			
			// set the properties value
			prop.setProperty(client.getIdentifiant(), client.concatClient());

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			
			try {
				output = new FileOutputStream(Client.CLIENTPROPERTIESURL);
	
				// set the properties value
				prop.setProperty(client.getIdentifiant(), client.concatClient());
	
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