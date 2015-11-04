package emulator;

/**
 * Assembly kodunda tanýmlanan variable'larýn memory'deki yerlerine yerleþtirilene kadar 
 * gerekli bilgilerinin tutulmasýný saðlayan, runtime sýrasýnda variable isimlerinden adreslerin 
 * hesaplanmasýna yarayan bir data objesi
 *
 */
public class AssemblyVariable {
	
	private OperationWidth unitWidth;
	private int lenght;
	private String name;
	private int address;
	int value;
	
	public void setAddress(int address) {
		this.address = address;
	}

	public AssemblyVariable(OperationWidth unitWidth, int lenght,int value, String name) {
		super();
		this.unitWidth = unitWidth;
		this.lenght = lenght;
		this.name = name;
		this.value = value;
	}
	
	public OperationWidth getUnitWidth() {
		return unitWidth;
	}
	public void setUnitWidth(OperationWidth unitWidth) {
		this.unitWidth = unitWidth;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAddress() {
		return address;
	}

	public int getValue() {
		return value;
	}
	
	
	
}
