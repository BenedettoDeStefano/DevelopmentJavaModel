package progettoSettimana02;

public class Libro extends ElementiCatalogo {
	private String autore;
	private String genere;


	public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String genere,
			String autore) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "titolo=" + titolo + ", isbn=" + isbn + ", autore='" + getAutore() + ", genere='"
				+ getGenere();
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

}
