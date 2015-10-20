package command;

public class ExitCommand extends IhmCommand {

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return super.getInt();
	}

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return "Arrêter l'application";
	}

	@Override
	public void execute() {
		System.exit(0);
	}
	
}
