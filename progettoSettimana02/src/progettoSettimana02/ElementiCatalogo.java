package progettoSettimana02;

public abstract class ElementiCatalogo {

	protected String isbn;
	protected String titolo;
	protected int annoPubblicazione;
	protected int numeroPagine;

	public ElementiCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

}
