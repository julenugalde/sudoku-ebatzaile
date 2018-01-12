package eus.julenugalde.sudokuebatzaile;

/**
 * Laukitxo bakoitza Sudoku laukian
 */
public class Laukitxo {
	/**	Balioa laukitxoa hutsik badago */
	public static final int HUTSIK = 0;
	/** Balio minimoa laukitxoan */
	public static final int MIN_VALUE = 1;
	/** Balio maximoa laukitxoan */
	public static final int MAX_VALUE = 9;
	
	private int balio;
	private boolean flagHasierako;
	
	/** Eraikilea. Hutsik hasten du laukitxoa
	 */
	public Laukitxo() {
		balio = HUTSIK;
	}
	
	/** Laukitxoaren balioa eskuratzeko
	 * 
	 * @return Laukitxoaren balioa
	 */
	public int getBalio() {
		return balio;
	}
	
	/** Laukitxoaren balioa ezartzeko
	 * 
	 * @param balioBerria Laukitxoaren balio berria
	 * @return <code>true</code> balioa zuzena bada, <code>false</code> balioa baliogarria ez bada
	 */
	public boolean setBalio(int balioBerria) {
		if ((balioBerria == HUTSIK) || ((balioBerria>=MIN_VALUE) && (balioBerria<=MAX_VALUE))) {
			balio = balioBerria;
			return true;
		}
		else return false; 
	}
	
	/** Laukitxoa hutsik dagoen ikusteko
	 * 
	 * @return <code>true</code> laukitxoa hutsik badago
	 */
	public boolean isHutsik() {
		return balio == HUTSIK;
	}
	
	/** Laukitxoaren balioa ezabatu eta husteko
	 */
	public void hustu() {
		balio = HUTSIK;
	}
	
	/** Hasieran laukitxoak balore bat zeukan ezartzeko
	 * 
	 * @param hasierakoa balorea ezartzeko
	 */
	public void setHasierako(boolean hasierakoa) {
		flagHasierako = hasierakoa;
	}
	
	/** Begiratzen du hasieran laukitxoak balorea bat ote zeukan
	 * 
	 * @return <code>true</code> sudoku-arazoaren datu bat bazen
	 */
	public boolean isHasierakoa() {
		return flagHasierako; 
	}
}
