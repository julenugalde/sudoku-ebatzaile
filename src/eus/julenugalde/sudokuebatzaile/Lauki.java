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

	
	public boolean ebatzi() {
		//TODO ebazteko kodea idatzi
		for (int i=0; i<laukitxoak.length; i++) {
			if (laukitxoak[i].isHutsik())
				laukitxoak[i].setBalio(3); 
		}
		System.out.println("Egiaztatu: " + egiaztatu());
		return true;
	}
	
	private int hurrengoPosizioHutsa() {
		for (int i=0; i<laukitxoak.length; i++) {
			if (laukitxoak[i].isHutsik())
				return i;
		}
		return -1;
	}
	
	private boolean saiatuBalioa(int posizioa, int balioa) {
		//TODO EGIN
		return false;
	}
	
	/** Sudokuren arauak betetzen diren egiaztatu
	 * 
	 * @return <code>true</code> sudokua zuzena bada, <code>false</code> akatsik badago
	 */
	public boolean egiaztatu() {
		for (int i=0; i<laukitxoak.length; i++) {
			//hutsik ez badago,begiratu 
			if (!laukitxoak[i].isHutsik()) {
				int balioa = laukitxoak[i].getBalio();
				//Begiratu lerro berean balioa errepikatzen den
				int j = i + 1;
				while ((j%Lauki.ZABALERA != 0) && (j<laukitxoak.length)) {
					//System.out.println(i + "-" + j + " egiaztatzen (lerroa)");
					if (balioa == laukitxoak[j].getBalio()) {
						System.err.println("Error: " + i + "-" + j);
						return false;
					}						
					j++;
				}
				//Begiratu zutabe berean balioa errepikatzen den
				j = i + Lauki.ZABALERA;
				while (j<laukitxoak.length) {
					//System.out.println(i + "-" + j + " egiaztatzen (zutabea)");
					if (balioa == laukitxoak[j].getBalio()) {
						System.err.println("Error: " + i + "-" + j);
						return false;
					}
					j+=Lauki.ZABALERA;
				}
			}
		}
		return true;
	}
	
	public boolean isEbatzita() {
		if (egiaztatu()) {
			for (int i=0; i<laukitxoak.length; i++) {
				if (laukitxoak[i].isHutsik()) return false;
			}
		}
		return true;
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
			
			lauki.egiaztatu();
			
		} catch (IOException ioex) {
			System.err.println("IO error: " + ioex.getLocalizedMessage());
		} catch (NumberFormatException nfex) {
			System.err.println("Format error: " + nfex.getLocalizedMessage());
		}
	}

}
