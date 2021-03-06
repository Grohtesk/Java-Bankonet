package command;

import com.bankonet.Client;

import bankonet.metier.ClientService;

public class ListeClientsCommand extends IhmCommand {
	
	private ClientService clientService;
	public ListeClientsCommand(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return "Affiche la liste des clients";
	}

	@Override
	public void execute() {
		for (Client client : clientService.getClients()) {
			System.out.println(client);
		}
	}
}
