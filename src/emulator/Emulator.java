package emulator;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import emulator.exception.AssemblerException;
import emulator.exception.EMURunTimeException;
import emulator.gui.EmulatorView;

/**
 * 
 * 8086 emulatörünün ana sýnýfý.  
 */
public class Emulator {
	
	public static final int MIN_MEMORY_SIZE = 4096; // 4KB
	
	private File assemblyFile;
	private Assembler assembler;
	private Processor processor;
	
	private EmulatorView view;
	
	/**
	 *  Emulator objesi için default contructor. Gerekli initilization yapýlýr.
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
	 *  Ýþlemci'ye tek bir instruction'u çalýþtýrmasý komutu verilir.
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
	 *  GUI'ye kendini update etmesi gerektiði sinyalini gönderir
	 */
	private void notifyViewForChanges() {
		if( view != null)
			view.updateView( this);
	}

	/**
	 * Main methodu. Yeni bir Emulatör objesi ve onun GUI'sini yaratýr. 
	 * Geri kalan etkileþim GUI üzerinden yapýlmaktadýr.
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
	 * TODO infinite loop'a girerse programý durdurmanýn bir yolu bulunmalý
	 * 
	 * Assembly kodundaki tüm instructionlar program durana kadar çalýþtýrýlýr.
	 * @throws InterruptedException 
	 */
	public void runAll() throws InterruptedException {
		while( !processor.isFinished() && !processor.isWaiting() ){
			oneStep();
			//TODO handle infinite loops..
		}
	}

	

	/**
	 *  Assembly dosyasý yeniden okunulur, iþlemci'ye en baþtan baþlamasý gerektiði sinyali gönderilir.
	 *  Dosya her deðiþtiðinde çaðrýlmasý gereklidir.
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
