package emulator.functionptrs;

import emulator.OperationWidth;

// C'deki function pointer i�levini g�ren bir interface. MUL, DIV i�in kullan�l�r
public interface MultiplicationDivisionFunction {

	void execute(int value, OperationWidth width);

}
