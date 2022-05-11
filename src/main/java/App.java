import java.util.Scanner;

/**
 * @author Victor
 * @since 09/05/2022 - 12:17
 */
public class App {
	public static void main(String[] args) {
		Cancion[] canciones;
		Scanner scanner = new Scanner(System.in);

		System.out.println("===== MP3 ORGANIZER =====");

		do {
			System.out.print("Introduce la ruta de los archivos de música -> ");
		} while ((canciones = CancionUtils.getCanciones(scanner.nextLine())) == null);

		do {
			/* NOTA: Se ha utilizado el comando de Linux tree para imprimir los árboles de directorios */
			System.out.println("""
					1) Artista
					   └── Album
					       └── Cancion""");
			System.out.println("""
					2) Album
					   └── Cancion""");
			System.out.println("""
					3) Artista
					   └── Cancion""");
			System.out.print("Elige un árbol de directorios -> ");
		} while (!DirTree.set(scanner.nextInt()));

		try {
			DirTree.create(canciones);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}