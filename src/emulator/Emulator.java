package emulator;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import emulator.exception.AssemblerException;
import emulator.exception.EMURunTimeException;
import emulator.gui.EmulatorView;

/**
 * 
 * 8086 emulat�r�n�n ana s�n�f�.  
 */
public class Emulator {
	
	public static final int MIN_MEMORY_SIZE = 4096; // 4KB
	
	private File assemblyFile;
	private Assembler assembler;
	private Processor processor;
	
	private EmulatorView view;
	
	/**
	 *  Emulator objesi i�in default contructor. Gerekli initilization yap�l�r.
	 */
	public Emulator(){
		assemblyFile = null;
		try {
			assembler = new Assembler(assemblyFile);
		} catch (AssemblerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processor = new Processor( assembler, MIN_MEMORY_SIZE);
	}
	
	//Getter ve Setter Methodlar
	public File getAssemblyFile() {
		return assemblyFile;
	}

	public void setAssemblyFile(File assemblyFile) {
		this.assemblyFile = assemblyFile;
		try {
			assembler.setAssemblyFile(assemblyFile);
			processor.reset();
		} catch (AssemblerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyViewForChanges();
	}

	public Assembler getAssembler() {
		return assembler;
	}

	public void setView(EmulatorView view) {
		this.view = view;
		view.setEmulator( this);
		notifyViewForChanges();
	}

	public EmulatorView getView() {
		return view;
	}

	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	
	
	/**
	 *  ��lemci'ye tek bir instruction'u �al��t�rmas� komutu verilir.
	 */
	public void oneStep(){
		try {
			processor.fetch();
			processor.execute();
		} catch (EMURunTimeException rte) {
			JOptionPane.showMessageDialog(getView(), "Run Time Exception:\n" + rte.getMessage() + "\n" + rte.getStackTrace().toString());
			rte.printStackTrace();
		}catch( AssemblerException ae){
			JOptionPane.showMessageDialog(getView(), "Assembler Exception:\n" + ae.getMessage() + "\n" + ae.getStackTrace().toString());
			ae.printStackTrace();
		}
		notifyViewForChanges();
	}

	/**
	 *  GUI'ye kendini update etmesi gerekti�i sinyalini g�nderir
	 */
	private void notifyViewForChanges() {
		if( view != null)
			view.updateView( this);
	}

	/**
	 * Main methodu. Yeni bir Emulat�r objesi ve onun GUI'sini yarat�r. 
	 * Geri kalan etkile�im GUI �zerinden yap�lmaktad�r.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try {
					Emulator emu = new Emulator();
					File file = new File( "test.asm" );
					if( !file.canRead() ){
						file.createNewFile();
					}
					emu.setAssemblyFile(file);
					EmulatorView view = new EmulatorView( emu);
					emu.setView( view);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( null, "Exception Occured!\n" + e.getMessage() + "\n" + e.getStackTrace());
					e.printStackTrace();
				}
		    }
		 });
	}

	/**
	 * TODO infinite loop'a girerse program� durdurman�n bir yolu bulunmal�
	 * 
	 * Assembly kodundaki t�m instructionlar program durana kadar �al��t�r�l�r.
	 * @throws InterruptedException 
	 */
	public void runAll() throws InterruptedException {
		while( !processor.isFinished() && !processor.isWaiting() ){
			oneStep();
			//TODO handle infinite loops..
		}
	}

	

	/**
	 *  Assembly dosyas� yeniden okunulur, i�lemci'ye en ba�tan ba�lamas� gerekti�i sinyali g�nderilir.
	 *  Dosya her de�i�ti�inde �a�r�lmas� gereklidir.
	 */
	public void reset() {
		try {
			assembler.setAssemblyFile(assemblyFile);
			processor.reset();
			processor.startOS();
		} catch (AssemblerException e) {
			JOptionPane.showMessageDialog( view, "Exception while reseting: " + e.getMessage() );
			e.printStackTrace();
		}
		notifyViewForChanges();
	}

}
