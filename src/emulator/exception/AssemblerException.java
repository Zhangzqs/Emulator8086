package emulator.exception;

/**
 *
 * Assemble ederken yanlýþ bir durum olduðunda bu exception throw edilecektir.
 *
 */
public class AssemblerException extends Exception {

	String messageAppendix;
	
	public AssemblerException(Exception e) {
		super();
		messageAppendix = "\n======\nAssembler exception : \n";
	}

	public AssemblerException() {
		super();
		messageAppendix = "Assembler exception : \n";
	}
	
	public AssemblerException(String string) {
		super();
		messageAppendix = "Assembler exception - " + string + ": \n";
	}

	@Override
	public String getMessage() {
		return messageAppendix;
	}
	

}
