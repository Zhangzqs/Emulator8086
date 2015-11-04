package emulator;

import java.util.ArrayList;

import javax.swing.ListModel;


/**
 * 
 * RAM'i simule eden temsili memory objesi
 */
public class Memory {
	
	protected static final int DEFAULT_MEMORY_SIZE = 1024;
	
	protected byte[] bytes;
	
	public Memory(){
		bytes = new byte[DEFAULT_MEMORY_SIZE];
	}
	
	public Memory( int sizeOfMemoryInBytes){
		bytes = new byte[sizeOfMemoryInBytes];
	}
	
	public void clear(){
		bytes = new byte[ bytes.length];
	}
	public byte[] getByteArray() {
		return bytes;
	}

	/**
	 * TODO 32 bit-write desteklenmeli
	 * 
	 * Gelen deðeri memory'e uygun þekilde ( little-endian) yazar.
	 * 
	 * @param memAddress
	 * @param value
	 * @param width
	 */
	public void write(int memAddress, int value, OperationWidth width) {
		//TODO 32 bit mode ekle
		if( width.isEightBit() ){
			bytes[memAddress] = (byte) (value & 0xFF);
		}
		else{
			bytes[memAddress] = (byte) ((value & 0xFF00) >>> 8);
			bytes[memAddress + 1] = (byte) (value & 0xFF);
		}
		
	}

	/**
	 * TODO 32 bit-read desteklenmeli mi?
	 * istenilen adresteki deðeri memory'den uygun bir þekilde alýr. ( little-endian)
	 * 
	 * @param memAddress
	 * @param width
	 * @return
	 */
	public int read(int memAddress, OperationWidth width) {
		if( width.isEightBit() ){
			return bytes[memAddress];
		}
		else{
			int msb = bytes[memAddress];
			int lsb = bytes[memAddress+1];
			return (msb << 8) + lsb;
		}
	}

	public void reset( int size) {
		bytes = new byte[size];
		
	}

	
}
