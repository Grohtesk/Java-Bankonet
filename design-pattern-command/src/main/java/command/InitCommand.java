package command;

import bankonet.metier.client.InitService;

public class InitCommand extends IhmCommand {

	InitService initService;
	
	public InitCommand(InitService initService) {
		this.initService=initService;
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getLibelle() {
		return "Intialiser la base de donn�e";
	}

	@Override
	public void execute() {
		System.out.println("Ajout de 5 clients");
		initService.init();
	}

	

}
