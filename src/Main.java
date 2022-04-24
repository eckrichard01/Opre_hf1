import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static final ArrayList<Feladat> feladatok = new ArrayList<>();
	private static final ArrayList<Feladat> futoFeladatok = new ArrayList<>();

	private static final magasPrio magasPrio = new magasPrio();
	private static final alacsonyPrio alacsonyPrio = new alacsonyPrio();
	private static String feladatoksorrendje = "";

	static boolean UtemezoUres = true;

	public static void beolvas(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while (scanner.hasNextLine() && feladatok.size() <= 10) {
			if(!input.equals("")){
				String[] sor = input.split(",");
				Feladat beolvastottFeladat = new Feladat(sor[0].charAt(0), sor[1].equals("1"), Integer.parseInt(sor[2]), Integer.parseInt(sor[3]));

				if (feladatok.size() == 0) {
					feladatok.add(beolvastottFeladat);}
				else {
					int i=0;
					while(feladatok.size() > i && feladatok.get(i).kezdes <= beolvastottFeladat.kezdes) i++;
					feladatok.add(i, beolvastottFeladat);
				}
			}
			input = scanner.nextLine();
		}
		if(!input.equals("")){
			String[] lineContent = input.split(",");
			Feladat readFeladat = new Feladat(lineContent[0].charAt(0), lineContent[1].equals("1"), Integer.parseInt(lineContent[2]), Integer.parseInt(lineContent[3]));

			if (feladatok.size() == 0) {
				feladatok.add(readFeladat);}
			else {
				int i=0;
				while(feladatok.size() > i && feladatok.get(i).kezdes <= readFeladat.kezdes) i++;
				feladatok.add(i, readFeladat);
			}
		}
	}

	public static void kiir(){
		System.out.println(feladatoksorrendje);
		for(int i = 0; i< futoFeladatok.size(); i++) {
			if(i == 0){
				System.out.print(futoFeladatok.get(i).nev + ":" + futoFeladatok.get(i).getEnnyitVarakozot());
			}else{
				System.out.print("," + futoFeladatok.get(i).nev + ":" + futoFeladatok.get(i).getEnnyitVarakozot());
			}
		}
	}

	public static void addFeladat(Feladat feladat) {
		Main.UtemezoUres =false;

		if (feladat.magasPrioritas) {
			magasPrio.ujfeladat(feladat);
		} else {
			alacsonyPrio.ujfeladat(feladat);
		}
	}

	public static void tick() {
		if(magasPrio.ures() && alacsonyPrio.ures())
		{
			Main.UtemezoUres = true;
			return;
		}
		else if(!magasPrio.ures() && !alacsonyPrio.ures()){
			alacsonyPrio.alljonmeg();
		}
		else if(magasPrio.ures() && !alacsonyPrio.ures()){
			alacsonyPrio.futhat();
		}
		else if(!magasPrio.ures() && alacsonyPrio.ures()){
			alacsonyPrio.alljonmeg();
		}

		magasPrio.tick();
		alacsonyPrio.tick();
	}

	public static void ujFeladatbelep(Feladat feladat) {
		if(feladatoksorrendje.length() > 0 && feladatoksorrendje.charAt(feladatoksorrendje.length() - 1) == feladat.nev){
			feladatoksorrendje += "";
		}
		else {
			feladatoksorrendje += feladat.nev;
		}
	}

	public static void main(String[] args) {
		beolvas();

		if(feladatok.size()==0) {
			System.out.println("Nincs feladat");
			return;
		}

		for(int utem = 0; feladatok.size() > 0 || !UtemezoUres; utem++) {
			for(int i = 0; i< feladatok.size(); i++) {
				if(feladatok.get(i).kezdes == utem) {
					addFeladat(feladatok.get(i));
					futoFeladatok.add(feladatok.get(i));
					feladatok.remove(i);
					i--;
				}
			}
			tick();
		}

		kiir();
	}
}
