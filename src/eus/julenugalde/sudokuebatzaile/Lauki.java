package eus.julenugalde.sudokuebatzaile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** Sudoku laukia */
public class Lauki {
	/** Laukiaren zabalera */
	public static final int ZABALERA = 9;
	
	private Laukitxo[] laukitxoak;
	
	/** Laukia hutsik hasteko */
	public Lauki() {
		laukitxoak = new Laukitxo[ZABALERA * ZABALERA];
		for (int i=0; i<laukitxoak.length; i++) {
			laukitxoak[i] = new Laukitxo();
			laukitxoak[i].setHasierako(false);
		}
	}
	
	public Lauki(File fitxategi, char bereizle) throws IOException, NumberFormatException {
		this();

		if (!fitxategi.exists()) throw new IOException("Fitxategia ez da aukitu.");
		FileReader fr = new FileReader(fitxategi);
		try {
			int i=0;
			int irakurrita;
			while (((irakurrita=fr.read()) != -1) && (i<laukitxoak.length)) {
				if (irakurrita == ';') {
					laukitxoak[i].hustu();
					laukitxoak[i++].setHasierako(false);
					//System.out.println(i + " HUTSIK");
				}
				else if (irakurrita == '\n') {}
				else{
					//irakurrita karaktere bat da, bere balioa eskuratu behar da
					int zenbakia = irakurrita - '0';
					fr.read();	//Separadorea
					if ((zenbakia >= Laukitxo.MIN_VALUE) && (zenbakia <= Laukitxo.MAX_VALUE)) {
						laukitxoak[i].setBalio(zenbakia);
						laukitxoak[i++].setHasierako(true);
						//System.out.println(i + ": " + zenbakia);
					}
					else {
						throw new IOException("Balioa ez da zuzena: " + zenbakia);
					}
				}
			} 
		} finally {
			fr.close();
		}
		
	}
	
	public boolean setLaukitxo (int posizioa, int balioa) {
		if (posizioa < 0) return false;
		if (posizioa > laukitxoak.length) return false;
		return laukitxoak[posizioa].setBalio(balioa);
	}
	
	public Laukitxo[] getLaukitxoak() {
		return laukitxoak;
	}
	
	public static void main(String[] args) {
		File f = new File("wildcatjan17.csv");
		try {
			Lauki lauki = new Lauki(f, ';');
			Laukitxo[] l = lauki.getLaukitxoak();
			int j=0;
			for (int i=0; i<l.length; i++) {
				String katea = (l[i].isHutsik()) ? " . " : (" " + l[i].getBalio() + " "); 
				System.out.print(katea);
				if ((++j) == 9) {
					System.out.println("");
					j = 0;
				}
			}
		} catch (IOException ioex) {
			System.err.println("IO error: " + ioex.getLocalizedMessage());
		} catch (NumberFormatException nfex) {
			System.err.println("Format error: " + nfex.getLocalizedMessage());
		}
	}
	
	public boolean ebatzi() {
		//TODO ebazteko kodea idatzi
		for (int i=0; i<laukitxoak.length; i++) {
			if (laukitxoak[i].isHutsik())
				laukitxoak[i].setBalio(3);
		}
		return true;
	}
}
