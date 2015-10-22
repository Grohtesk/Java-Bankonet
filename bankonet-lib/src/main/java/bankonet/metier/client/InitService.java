package bankonet.metier.client;

import com.bankonet.Client;

import bankonet.dao.client.ClientDao;

public class InitService {
	
	ClientDao clientDao;
	
	public InitService(ClientDao clientDao) {
		
		this.clientDao = clientDao;
	}

	public void init() {
		Client client1=new Client("bob","Bradford","Bob","bob");
		Client client2=new Client("jim","Jerk","Jimmy","jim");
		Client client3=new Client("sal","Seppadboll","Sally","sally");
		Client client4=new Client("rob","Reznikov","Robbert","bob");
		Client client5=new Client("frank","Ferguson","Frank","frank");

		clientDao.save(client1);
		clientDao.save(client2);
		clientDao.save(client3);
		clientDao.save(client4);
		clientDao.save(client5);
	}
}
