package command;

import java.util.Scanner;

import bankonet.metier.ClientService;

public class SupprimerClientCommand extends IhmCommand {

	private ClientService clientService;
	private Scanner sc;

	public SupprimerClientCommand(Scanner sc, ClientService clientService) {
		this.sc = sc;
		this.clientService = clientService;
	}

	@Override
	public int getInt() {
		return 7;
	}

	@Override
	public String getLibelle() {
		return "Supprimer un client";
	}

	@Override
	public void execute() {
		System.out.println("Saisissez l'identifiant du client à supprimer");
		String identifiant=sc.nextLine();
		
		try {
			clientService.supprimerClient(identifiant);
		} catch (Exception e) {
			System.out.println("Client non trouvé");
		}
	}
	
	

	
}
