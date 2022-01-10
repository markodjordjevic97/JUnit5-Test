package radnici;

import java.util.LinkedList;

public class Zaposleni {
	public static LinkedList<Radnik> radnik = new LinkedList<Radnik>();

	public static void dodajRadnik(Radnik a) {
		if (a == null)
			throw new NullPointerException("Radnik ne sme biti null");
		if (radnik.contains(a))
			throw new RuntimeException("Radnik vec postoji");
		radnik.add(a);
	}

	public static LinkedList<Radnik> pronadjiRadnik(String ime) {
		if (ime == null)
			return null;
		LinkedList<Radnik> novaLista = new LinkedList<Radnik>();
		for (int i = 0; i < radnik.size(); i++)
			if (radnik.get(i).getIme().equals(ime))
				novaLista.add(radnik.get(i));
		return novaLista;
	}
}

