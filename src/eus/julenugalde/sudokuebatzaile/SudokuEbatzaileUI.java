package eus.julenugalde.sudokuebatzaile;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class SudokuEbatzaileUI extends JFrame {
	private JTextField[] jtfLaukitxoak;
	private JButton jbIrakurri;
	private JButton jbEbatzi;
	
	private Lauki lauki;
	private Font hasieraIturri;
	private Font besteIturri;
	private File currentPath;
	
	public SudokuEbatzaileUI() {
		lauki = new Lauki();
		besteIturri = new Font("Arial", Font.PLAIN, 12);
		hasieraIturri = new Font("Arial", Font.BOLD, 16); 
		currentPath = new File(System.getProperty("user.dir"));
		
		sortuOsagaiak();
		osagaiakBanatu();
		erantzuleakEzarri();
		leihoaKonfiguratu();
	}
	
	private void leihoaKonfiguratu() {
		setTitle("Sudoku ebatzailea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setSize(350, 350);
	}

	private void erantzuleakEzarri() {
		jbIrakurri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				try {
					File fitxategia = fitxategiaAukeratu();
					if (fitxategia != null) {
						lauki = new Lauki(fitxategia, ';');
						eguneratuLaukia();
						jbEbatzi.setEnabled(true);
					}
				}
				catch (IOException ioex) {
					JOptionPane.showMessageDialog(
							source.getTopLevelAncestor(),
							"Ezin izan da fitxategia irakurri:\n" + ioex.getLocalizedMessage(),
							"Errorea fitxategian",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jbEbatzi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lauki.ebatzi()) {
					eguneratuLaukia();
					JOptionPane.showMessageDialog(
				              ((JButton)arg0.getSource()).getTopLevelAncestor(),
				              "Sudokua ebatzita",
				              "Ebatzita",
				              JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(
							((JButton)arg0.getSource()).getTopLevelAncestor(),
							"Ezin izan da Sudokua ebatzi",
							"Ezin da ebatzi",
							JOptionPane.ERROR_MESSAGE);
				}
			}			
		});
	}

	private void sortuOsagaiak() {
		jtfLaukitxoak = new JTextField[Lauki.ZABALERA * Lauki.ZABALERA];
		for (int i=0; i<jtfLaukitxoak.length; i++) {
			jtfLaukitxoak[i] = new JTextField("");
			jtfLaukitxoak[i].setEditable(false);
			jtfLaukitxoak[i].setHorizontalAlignment(JTextField.CENTER);
			jtfLaukitxoak[i].setSize(30, jtfLaukitxoak[i].getSize().height);
			//TODO koloreak aldatu zeldak ezberdintzeko
			int lerro = i % (Lauki.ZABALERA);
		}
		jbIrakurri = new JButton("Fitxategia irakurri");
		jbEbatzi = new JButton("Ebatzi");
		jbEbatzi.setEnabled(false);
	}
	
	private void osagaiakBanatu() {
		JPanel jpLaukia = new JPanel(new GridLayout(Lauki.ZABALERA, Lauki.ZABALERA));
		for (int i=0; i<jtfLaukitxoak.length; i++) {
			jpLaukia.add(jtfLaukitxoak[i]);
		}
		
		JPanel jpBotoiak = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpBotoiak.add(jbIrakurri);
		jpBotoiak.add(jbEbatzi);
		
		add(jpLaukia, BorderLayout.CENTER);
		add(jpBotoiak, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SudokuEbatzaileUI ui = new SudokuEbatzaileUI();
		ui.setVisible(true);
	}
	
	private void eguneratuLaukia() {
		Laukitxo[] laukitxoak = lauki.getLaukitxoak();
		for (int i=0; i<jtfLaukitxoak.length; i++) {
			//Balioak
			if (laukitxoak[i].isHutsik())
				jtfLaukitxoak[i].setText("");
			else
				jtfLaukitxoak[i].setText(laukitxoak[i].getBalio() + "");
			//Hasierakoak erakutsi
			if (laukitxoak[i].isHasierakoa())
				jtfLaukitxoak[i].setFont(hasieraIturri);
			else
				jtfLaukitxoak[i].setFont(besteIturri);
		}
	}
	
	private File fitxategiaAukeratu() {
		JFileChooser chooser = new JFileChooser(currentPath);
		FileNameExtensionFilter filter = 
			 new FileNameExtensionFilter("Sudoku fitxategiak (.csv)", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			currentPath = chooser.getCurrentDirectory();
			return new File(chooser.getSelectedFile().getAbsolutePath());
		}
		else {
			return null;
		}
	}
}
