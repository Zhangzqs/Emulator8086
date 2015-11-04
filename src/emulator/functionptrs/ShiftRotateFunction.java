package emulator.functionptrs;

import emulator.OperationWidth;

//Runtime s�ras�nda olu�abilecek hatalarda bu exception g�nderilir.
public interface ShiftRotateFunction {

	int execute(int leftValue, int rightValue, OperationWidth width);
}
