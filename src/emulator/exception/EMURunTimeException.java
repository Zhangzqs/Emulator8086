package emulator.exception;

//Runtime sýrasýnda oluþabilecek hatalarda bu exception gönderilir.
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
