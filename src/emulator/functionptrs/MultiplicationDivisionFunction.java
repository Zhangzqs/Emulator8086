package emulator.functionptrs;

import emulator.OperationWidth;

// C'deki function pointer iþlevini gören bir interface. MUL, DIV için kullanýlýr
public interface MultiplicationDivisionFunction {

	void execute(int value, OperationWidth width);

}
