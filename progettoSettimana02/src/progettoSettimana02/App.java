package progettoSettimana02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class App {

	public static void main(String[] args) {

		List<ElementiCatalogo> catalogo = new ArrayList<>();


		Libro libro1 = new Libro("11231", "Harry Potter", 2021, 80, "Romanzo", "J. K. Rowling");
		Libro libro2 = new Libro("22344", "Il Codice Da Vinci", 1997, 350, "Mistero", "Dan Brown");
		Libro libro3 = new Libro("44566", "Orgoglio e pregiudizio", 2021, 416, "Romanzo", "Jane Austen");
		Libro libro4 = new Libro("66788", "Il signore degli anelli", 2000, 1200, "Fantasy",
				"J.R.R. Tolkien");
		Libro libro5 = new Libro("77899", "Il piccolo principe", 1830, 100, "Fiaba",
				"Antoine de Saint-Exupéry");
		Libro libro6 = new Libro("88900", "Moby Dick", 2023, 600, "Romanzo", "Herman Melville");

		Rivista rivista1 = new Rivista("98765", "National Geographic", 1988, 50, Periodicita.MENSILE);
		Rivista rivista2 = new Rivista("12345", "Time Magazine", 1996, 60, Periodicita.SETTIMANALE);
		Rivista rivista3 = new Rivista("23456", "Vogue", 2018, 100, Periodicita.MENSILE);
		Rivista rivista4 = new Rivista("34567", "National Geographic Kids", 2001, 40, Periodicita.MENSILE);
		Rivista rivista5 = new Rivista("45678", "Science", 1099, 80, Periodicita.MENSILE);
		Rivista rivista6 = new Rivista("56789", "Sports", 1998, 72, Periodicita.SEMESTRALE);
		
		catalogo.add(libro1);
		catalogo.add(libro2);
		catalogo.add(libro3);
		catalogo.add(libro4);
		catalogo.add(libro5);
		catalogo.add(libro6);

        catalogo.add(rivista1);
		catalogo.add(rivista2);
		catalogo.add(rivista3);
		catalogo.add(rivista4);
		catalogo.add(rivista5);
		catalogo.add(rivista6);

		// -------------------------- Elementi -------------------------------

		System.err.println("Lista elementi");
		catalogo.forEach(elemento -> {
			System.out.println("Titolo: " + elemento.titolo);
			System.out.println("ISBN: " + elemento.isbn);
			if (elemento instanceof Libro) {
				Libro libro = (Libro) elemento;
				System.out.println("Autore: " + libro.getAutore());
				System.out.println("Genere: " + libro.getGenere());
			} else if (elemento instanceof Rivista) {
				Rivista rivista = (Rivista) elemento;
				System.out.println("Periodicità: " + rivista.getPeriodicita());
			}
			System.out.println("--------------------");
		});

		// -------------------------- Rimuovi Elementi -------------------------------

		rimuoviElementoPerIsbn(catalogo, "98765"); // Rimozione prima rivista
		rimuoviElementoPerIsbn(catalogo, "11231"); // Rimozione primo libro

		System.err.println("Lista priva di elementi rimossi");
		catalogo.forEach(elemento -> {
			System.out.println("Titolo: " + elemento.titolo);
			System.out.println("ISBN: " + elemento.isbn);
			if (elemento instanceof Libro) {
				Libro libro = (Libro) elemento;
				System.out.println("Autore: " + libro.getAutore());
				System.out.println("Genere: " + libro.getGenere());
			} else if (elemento instanceof Rivista) {
				Rivista rivista = (Rivista) elemento;
				System.out.println("Periodicità: " + rivista.getPeriodicita());
			}
			System.out.println("--------------------");
		});

		// ----------------------- Cerca Elementi per Isbn ---------------------------
		ElementiCatalogo elementoTrovatoIsbn = cercaElementoPerISBN(catalogo, "45678");
		System.err.println("Elemento trovato tramite per isbn");
		System.out.println(elementoTrovatoIsbn);

		// ----------------------- Cerca Elementi per titolo ---------------------------
		List<ElementiCatalogo> elementoTrovatoAnno = cercaElementiPerAnnoPubblicazione(catalogo, 1997);
		System.err.println("Elementi trovati per anno di pubblicazione:");
		for (ElementiCatalogo elemento : elementoTrovatoAnno) {
			System.out.println(elemento);
		}
		// ----------------------- Cerca Elementi per autore ---------------------------
		List<ElementiCatalogo> elementiTrovati = cercaElementiPerAutore(catalogo, "Jane Austen");
		System.err.println("Elementi trovati per autore:");
		for (ElementiCatalogo elemento : elementiTrovati) {
			System.out.println(elemento);
		}
		// ----------------------- Salva ---------------------------
		System.err.println("✚ Salvastaggio avvenuto ✚");
		salvaDisco(catalogo);

		// ----------------------- Leggi ---------------------------
		System.err.println("lettura file");
		try {
			leggiDisco();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	// -------------------------- Metodi -------------------------------

	public static void rimuoviElementoPerIsbn(List<ElementiCatalogo> catalogo, String isbn) {
		catalogo.removeIf(elemento -> elemento.isbn == isbn);
	}

	private static ElementiCatalogo cercaElementoPerISBN(List<ElementiCatalogo> catalogo, String isbn) {
		return catalogo.stream().filter(elemento -> elemento.isbn == isbn).findFirst().orElse(null);
	}

	private static List<ElementiCatalogo> cercaElementiPerAnnoPubblicazione(List<ElementiCatalogo> catalogo,
			int annoPubblicazione) {
		return catalogo.stream().filter(elemento -> elemento.annoPubblicazione == annoPubblicazione)
				.collect(Collectors.toList());
	}

	private static List<ElementiCatalogo> cercaElementiPerAutore(List<ElementiCatalogo> catalogo, String autore) {
		return catalogo.stream().filter(
				elemento -> elemento instanceof Libro && ((Libro) elemento).getAutore().equalsIgnoreCase(autore))
				.collect(Collectors.toList());
	}


	private static File file = new File("file.txt");
	public static void salvaDisco(List<ElementiCatalogo> lista) {
		try {
			List<String> lines = new ArrayList<>();
			for (ElementiCatalogo elemento : lista) {
				lines.add(elemento.toString());
			}
			FileUtils.writeLines(file, "UTF-8", lines, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String leggiDisco() throws IOException {
		if (file.exists()) {
			String contenuto = FileUtils.readFileToString(file, "UTF-8");
			System.out.println(contenuto);
			return contenuto;
		} else {
			System.out.println("File non trovato");
			return "";
		}
	}
}



