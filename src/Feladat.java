public class Feladat {
	
	public final char nev;
	public final boolean magasPrioritas;
	public final int kezdes;
	public int hatralevoIdo;
	private int ennyitVarakozot = 0;
	
	public Feladat(char nev, boolean prio, int kezedes, int tartam) {
		this.nev = nev;
		this.magasPrioritas = prio;
		this.kezdes = kezedes;
		this.hatralevoIdo = tartam;
	}
	
	public void var() {
		ennyitVarakozot++;}
	
	public int getEnnyitVarakozot() {
		return ennyitVarakozot;
	}
	
	public void fut() {
		hatralevoIdo--;}
}
