package app;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import bankonet.dao.DaoFactory;
import bankonet.dao.DaoFactoryJpa;
import bankonet.metier.ClientService;
import bankonet.metier.client.InitService;
import command.*;

public class ConsoleApp {

	private DaoFactory daoFactory = new DaoFactoryJpa();
	private ClientService clientService = new ClientService(daoFactory.getDaoClient());
	private InitService initService= new InitService(daoFactory.getDaoClient());
	private List<IhmCommand> commands=Arrays.asList(
			new ListeClientsCommand(clientService),
			new ExitCommand(),
			new OuvrirCompteCourrantCommand(),
			new InitCommand(initService));
	
	private Scanner sc;
	
	public static void main(String[] args) {
		ConsoleApp app=new ConsoleApp();
		app.sortCommands();
		app.menu();
	}

	private void sortCommands() {
		Collections.sort(commands, new Comparator<IhmCommand>() {
	        @Override
	        public int compare(IhmCommand cmd1, IhmCommand cmd2)
	        {
	        	return (cmd1.getInt() > cmd2.getInt()) ? 1 : -1;

	        }
	    });
	}

	private void menu() {
		System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
		
		for (IhmCommand ihmCommand : commands) {
			System.out.println(ihmCommand.getInt()+" : "+ihmCommand.getLibelle());
		}
		System.out.println("Veuillez choisir une option");
		this.sc=new Scanner(System.in);
		int choix=sc.nextInt();
		
		commands.get(choix).execute();
		
		this.menu();
	}
}
