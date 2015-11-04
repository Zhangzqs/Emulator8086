package emulator.functionptrs;

import emulator.OperationWidth;

// add, sub, mov gibi instruction'lar bunu implement eder. ( c++'daki function pointer gibi kullanýlýr.)
public interface BasicALUFunction {
	
	public int execute( int openardLeft, int openardRight, OperationWidth w);
}
