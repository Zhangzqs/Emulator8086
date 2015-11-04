package emulator.exception;

//Runtime s�ras�nda olu�abilecek hatalarda bu exception g�nderilir.
public class EMURunTimeException extends Exception {

	private int instructionIndex;
	private String additionalMessage;
	
	public EMURunTimeException(int instructionIndex) {
		super();
		this.instructionIndex = instructionIndex;
		additionalMessage = "";
	}
	
	public EMURunTimeException(String string, int instructionIndex) {
		 this.instructionIndex = instructionIndex;
		 additionalMessage = string;
	}

	@Override
	public String getMessage() {
		return "Run Time Exception:" + additionalMessage + "\n in line" + instructionIndex + "\n==\n" + super.getMessage();
	}

}
