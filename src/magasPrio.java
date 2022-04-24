import java.util.ArrayList;

public class magasPrio extends Prio {

	private static final int maxUtem = 2;
	private int utemEddig = 0;

	private final ArrayList<Feladat> varakozok = new ArrayList<>();
	private Feladat aktiv = null;

	public magasPrio() { }

	public void ujfeladat(Feladat feladat) {
		varakozok.add(feladat);
	}

	public void tick() {
		if(aktiv == null && varakozok.size() == 0){
			return;
		}
		utemEddig++;

		if(varakozok.size() > 0 && (utemEddig == maxUtem || aktiv == null)) {

			if (aktiv != null) varakozok.add(aktiv);
			aktiv = varakozok.get(0);
			varakozok.remove(0);

			Main.ujFeladatbelep(aktiv);

			utemEddig = 0;
		}

		if (utemEddig == maxUtem){
			utemEddig = 0;
		}

		for(int i = 0; i < varakozok.size(); i++){
			Feladat feladat = varakozok.get(i);
			feladat.var();
		}
		if(aktiv!=null) {
			aktiv.fut();
			if(aktiv.hatralevoIdo == 0) aktiv = null;
		}

	}
	
	public boolean ures() {
		if(aktiv == null && varakozok.size()==0){
			return true;
		}
		return false;
	}
}
