package progettoSettimana02;

public class Rivista extends ElementiCatalogo {

	private Periodicita periodicita;
	
	public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "titolo=" + titolo + ", isbn=" + isbn + ", periodicita='" + getPeriodicita();
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}
	

}
