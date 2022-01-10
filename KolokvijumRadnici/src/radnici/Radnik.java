package radnici;

public class Radnik {
	private String ime= null;
	private int cenaSata= 0;
	
	public int getCenaSata() {
		return cenaSata;
	}

	public int getBrSatiRada() {
		return brSatiRada;
	}


	private int brSatiRada= 0;

	public Radnik(String ime, int cenaSata, int brSatiRada) {
		super();
		this.ime= ime;
		this.cenaSata= cenaSata;
		this.brSatiRada= brSatiRada;
	}

	public void setCenaSata(int cenaSata) {
		if (cenaSata< 0)
			throw new RuntimeException("Cena satane sme biti manja od 0");
		this.cenaSata= cenaSata;
	}

	public void setBrSatiRada (int brSatiRada) {
		if (brSatiRada< 0  || brSatiRada >300)
			throw new RuntimeException("Broj sati mora biti veca od 0 i manji od 300");
		this.brSatiRada= brSatiRada;
	}


	public void setIme(String ime) {
		if (ime== null)
			throw new RuntimeException("Morate uneti ime ");
		this.ime= ime;
	}

	public String getIme() {
		return ime;
	}
	public double izracunajPlatu() {
		return (this.brSatiRada* this.cenaSata + 20000);
	}

	public boolean bolovanje() {
		if (this. izracunajPlatu() <= 20000)
			return true;
		else
			return false;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Radnik other = (Radnik) obj;
		if (ime == null) {
			if (other.ime!= null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (cenaSata!= other.cenaSata)
			return false;
		if (brSatiRada!= other.brSatiRada)
			return false;
		return true;
	}

}

