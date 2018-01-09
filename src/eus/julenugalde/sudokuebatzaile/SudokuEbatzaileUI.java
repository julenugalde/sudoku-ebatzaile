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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SudokuEbatzaileUI extends JFrame {
	private JTextField[] jtfLaukitxoak;
	private JButton jbIrakurri;
	private JButton jbEbatzi;
	
	private Lauki lauki;
	private Font hasieraIturri;
	private Font besteIturri;
	
	public SudokuEbatzaileUI() {
		lauki = new Lauki();
		besteIturri = new Font("Arial", Font.PLAIN, 12);
		hasieraIturri = new Font("Arial", Font.BOLD, 14);
		
		sortuOsagaiak();
		osagaiakBanatu();
		erantzuleakEzarri();
		leihoaKonfiguratu();
	}
	
	private void leihoaKonfiguratu() {
		// TODO Auto-generated method stub
		setTitle("Sudoku ebatzailea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 50);
		setSize(350, 350);
	}

	private void erantzuleakEzarri() {
		jbIrakurri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File fitxategia = fitxategiaAukeratu();
					if (fitxategia != null) {
						lauki = new Lauki(fitxategia, ';');
						eguneratuLaukia();
						jbEbatzi.setEnabled(true);
					}
				}
				catch (IOException ioex) {
					//TODO erakutsi ERRORE leiho bat
					System.err.println("error");
				}
			}
		});
		
		jbEbatzi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lauki.ebatzi();
				eguneratuLaukia();
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
		//TODO ondo egin
		return new File("wildcatjan17.csv");
	}
}
