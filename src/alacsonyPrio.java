import java.util.ArrayList;

public class alacsonyPrio extends Prio {

	private boolean futhat = true;

	private final ArrayList<Feladat> varakozok = new ArrayList<>();
	private Feladat aktiv = null;
	private boolean cserelheto;

	public alacsonyPrio() { }

	public void futhat() {
		futhat = true;
		cserelheto = true;
	}

	public void alljonmeg() {
		futhat = false;
		if (aktiv != null){
			varakozok.add(aktiv);
		}
		aktiv = null;
	}
	
	public void ujfeladat(Feladat feladat) {
		varakozok.add(feladat);
		cserelheto = true;
	}

	public void tick() {
		if(futhat == false) {
			for(int i = 0; i < varakozok.size(); i++){
				Feladat feladat = varakozok.get(i);
				feladat.var();
			}
			return;
		}
		if (cserelheto && varakozok.size() > 0) {
			int minidx = -1; int minhatralevo = Integer.MAX_VALUE;
			if(aktiv != null){
				minhatralevo = aktiv.hatralevoIdo;
			}

			for(int i = 0; i < varakozok.size(); i++) {
				if (varakozok.get(i).hatralevoIdo < minhatralevo) {minhatralevo = varakozok.get(i).hatralevoIdo; minidx = i;}
			}
			if(aktiv != null){
				if(aktiv.hatralevoIdo > minhatralevo){
					Feladat temp = aktiv;
					aktiv = varakozok.get(minidx);
					varakozok.remove(minidx);
					if(temp != null) varakozok.add(temp);
				}
			}
			else{
				Feladat temp = aktiv;
				aktiv = varakozok.get(minidx);
				varakozok.remove(minidx);
				if(temp != null) varakozok.add(temp);
			}

			Main.ujFeladatbelep(aktiv);

			cserelheto = false;
		}

		for(int i = 0; i < varakozok.size(); i++){
			Feladat feladat = varakozok.get(i);
			feladat.var();
		}
		if(aktiv !=null) {
			aktiv.fut();
			if(aktiv.hatralevoIdo == 0){
				aktiv = null;
			}
		}
	}
	
	public boolean ures() {
		if(aktiv ==null && varakozok.size()==0){
			return true;
		}
		return false;
	}
	
}
