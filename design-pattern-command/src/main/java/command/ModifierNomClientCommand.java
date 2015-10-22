package command;

import java.util.Scanner;

import com.bankonet.Client;

import bankonet.metier.ClientService;

public class ModifierNomClientCommand extends IhmCommand {
	
	ClientService clientService;
	private Scanner sc;
	
	public ModifierNomClientCommand(Scanner sc, ClientService clientService) {
		this.sc = sc;
		this.clientService=clientService;
	}

	@Override
	public int getInt() {
		return 6;
	}

	@Override
	public String getLibelle() {
		return "Modifier le nom d'un client";
	}

	@Override
	public void execute() {
		System.out.println("Entrez l'identifiant du client");
		String identifiant=sc.nextLine();
		try {
			Client client=clientService.getClient(identifiant);
			System.out.println("Définissez un nouveau nom");
			String nom=sc.nextLine();
			client.setNom(nom);
			clientService.updateClient(client);
		} catch (Exception e) {
			System.out.println("Auccun client trouvé");
		}
	}

}
