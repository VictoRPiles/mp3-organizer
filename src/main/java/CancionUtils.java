import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Victor
 * @since 11/5/22 - 13:49
 */
public abstract class CancionUtils {
	/**
	 * @return Array con las {@link Cancion} encontradas en una ruta.
	 * @see #getCancionesRecursivo(File, ArrayList)
	 */
	public static Cancion[] getCanciones(String ruta) {
		ArrayList<Cancion> listaCanciones = new ArrayList<>();
		File fichero = new File(ruta);

		if (!fichero.exists()) {
			System.out.printf("ERROR: %s no existe.\n", ruta);
			return null;
		}

		if (!fichero.isDirectory()) {
			if (!fichero.getPath().endsWith(".mp3")) {
				System.out.printf("ERROR: %s no es un fichero mp3.\n", ruta);
				return null;
			}

			Mp3File ficheroMp3;
			try {
				ficheroMp3 = new Mp3File(fichero);
			} catch (InvalidDataException | UnsupportedTagException | IOException e) {
				throw new RuntimeException(e);
			}

			/* Si no es un directorio devuelve un Array con una sola canción */
			return new Cancion[]{new Cancion(ficheroMp3)};
		}

		System.out.printf("%s es un directorio, iniciando búsqueda recursiva...\n", ruta);
		getCancionesRecursivo(fichero, listaCanciones);

		if (listaCanciones.isEmpty()) {
			System.out.println("No se han encontrado canciones.");
			return null;
		}

		System.out.printf("Se han encontrado %d canciones.\n", listaCanciones.size());
		return listaCanciones.toArray(new Cancion[0]);
	}

	/**
	 * Busca en cada directorio de forma recursiva.
	 * <p>
	 * Si el fichero no es un directorio y tiene extensión .mp3 lo añade a la lista de canciones.
	 */
	private static void getCancionesRecursivo(File root, ArrayList<Cancion> listaCanciones) {
		if (root.isDirectory()) {
			for (File archivo : Objects.requireNonNull(root.listFiles())) {
				getCancionesRecursivo(archivo, listaCanciones);
			}
		}

		if (root.getPath().endsWith(".mp3")) {
			Mp3File ficheroMp3;
			try {
				ficheroMp3 = new Mp3File(root);
			} catch (InvalidDataException | UnsupportedTagException | IOException e) {
				throw new RuntimeException(e);
			}
			listaCanciones.add(new Cancion(ficheroMp3));
		}
	}
}