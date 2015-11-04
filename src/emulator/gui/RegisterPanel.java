package emulator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javafx.scene.layout.Border;

import javax.swing.JPanel;

import emulator.Emulator;

public class RegisterPanel extends JPanel {
	
	private Emulator emulator;
	private GeneralPurposeRegistersPanel general;
	private SegmentRegisterPanel segment;
	private PtrIndexRegistersPanel ptrIndexregisterPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private double width;
	private double height;

	public RegisterPanel(Emulator emulator, double w, double h){
		width = w;
		height = h;
		this.emulator = emulator;
		general = new GeneralPurposeRegistersPanel(emulator, w, h );
		segment = new SegmentRegisterPanel(emulator, w, h);
		
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		ptrIndexregisterPanel = new PtrIndexRegistersPanel( emulator, w, h);
		
		leftPanel.setLayout( new GridLayout( 1, 2));
		leftPanel.add( general);
		leftPanel.add( segment);
		
		rightPanel.setLayout( new BorderLayout());;
		rightPanel.add( ptrIndexregisterPanel);
		
		setBackground( Color.red);
		setLayout( new GridLayout(1,2) );
		add( leftPanel);
		add( rightPanel);
		setPreferredSize( new Dimension( (int)(w * GUIConstants.REGISTER_PANEL_FRACTION_W), (int)(h * GUIConstants.REGISTER_PANEL_FRACTION_H)));
	}

	public void setEmulator(Emulator emulator) {
		this.emulator = emulator;
	}

	public void updateView() {
		general.updateView();
		segment.updateView();
		ptrIndexregisterPanel.updateView();
		
	}


	public void setValRepMode(int mode) {
		general.setValRepMode(mode);
		segment.setValRepMode(mode);
		ptrIndexregisterPanel.setValRepMode(mode);
		
	}
}
