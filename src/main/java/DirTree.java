import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Victor
 * @since 11/5/22 - 16:56
 */
enum TipoDirTree {
	AR_AL_TI, AL_TI, AR_TI
}

public abstract class DirTree {
	private static TipoDirTree tipoDirTree = null;

	/**
	 * Establece el directory tree.
	 *
	 * @return Si el directory tree escogido es válido.
	 */
	public static boolean set(int i) {
		/* Si el valor de 'i' no es el índice de ningún tipo de árbol */
		if (!(i > 0 && i <= TipoDirTree.values().length)) {
			return false;
		}

		tipoDirTree = TipoDirTree.values()[i - 1];
		return true;
	}

	/**
	 * Copia las canciones en distintas estructuras de árboles de directorios.
	 * <ul>
	 *     <li><code>AR_AL_TI = Artist / Album / Title.mp3</code></li>
	 *     <li><code>AR_TI    = Artist / Title.mp3</code></li>
	 *     <li><code>AL_TI    = Album / Title.mp3</code></li>
	 * </ul>
	 *
	 * @throws Exception Si {@link #tipoDirTree} no esta definido.
	 */
	public static void create(Cancion[] canciones) throws Exception {
		Path ruta = null;
		Path directorios = null;

		if (tipoDirTree == null) {
			throw new Exception("No se ha establecido el árbol de directorios");
		}

		for (Cancion cancion : canciones) {
			switch (tipoDirTree) {
				case AR_AL_TI -> {
					directorios = Paths.get(normalizar(cancion.getArtist()), normalizar(cancion.getAlbum()));
					ruta = Paths.get(String.valueOf(directorios), normalizar(cancion.getTitle() + ".mp3"));
				}
				case AR_TI -> {
					directorios = Paths.get(normalizar(cancion.getArtist()));
					ruta = Paths.get(String.valueOf(directorios), normalizar(cancion.getTitle() + ".mp3"));
				}
				case AL_TI -> {
					directorios = Paths.get(normalizar(cancion.getAlbum()));
					ruta = Paths.get(String.valueOf(directorios), normalizar(cancion.getTitle() + ".mp3"));
				}
			}

			if (!new File(String.valueOf(directorios)).exists()) {
				if (new File(String.valueOf(directorios)).mkdirs()) {
					System.out.printf("Creando directorios %s...\n", directorios);
				}
			}

			try {
				Files.copy(cancion.getRuta(), ruta);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** Remplaza caracteres especiales (con la excepción de acentos, dieresis, comas,puntos y paréntesis) por '-' */
	public static String normalizar(String nombre) {
		return nombre.replaceAll("[^A-Za-zÀ-ÿ\\d\s+,.()]", "-");
	}
}