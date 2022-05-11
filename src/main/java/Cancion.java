import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

/**
 * @author Victor
 * @since 09/05/2022 - 12:55
 */
public class Cancion {
	private final Mp3File ficheroMp3;

	public Cancion(Mp3File ficheroMp3) {
		this.ficheroMp3 = ficheroMp3;
	}

	/**
	 * Imprime la información de los metadatos.
	 *
	 * @see #getTagsComunes(ID3v1)
	 * @see <a href="https://github.com/mpatric/mp3agic/blob/master/readme.md">Mp3agic Docs.</a>
	 */
	public void getTags() {
		if (ficheroMp3.hasId3v1Tag()) {
			ID3v1 id3v1Tag = ficheroMp3.getId3v1Tag();
			getTagsComunes(id3v1Tag);
		}
		/* Etiquetas exclusivas de Id3v2 */
		if (ficheroMp3.hasId3v2Tag()) {
			ID3v2 id3v2Tag = ficheroMp3.getId3v2Tag();
			getTagsComunes(id3v2Tag);
			System.out.println("Lyrics: " + id3v2Tag.getLyrics());
			System.out.println("Composer: " + id3v2Tag.getComposer());
			System.out.println("Publisher: " + id3v2Tag.getPublisher());
			System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
			System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
			System.out.println("Copyright: " + id3v2Tag.getCopyright());
			System.out.println("URL: " + id3v2Tag.getUrl());
			System.out.println("Encoder: " + id3v2Tag.getEncoder());
			byte[] albumImageData = id3v2Tag.getAlbumImage();
			if (albumImageData != null) {
				System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
				System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
			}
		}
	}

	/** Metadatos comunes en Id3v1 e Id3v2 */
	private void getTagsComunes(ID3v1 id3v1Tag) {
		/* Etiquetas comunes en todos los archivos */
		System.out.println("Track: " + id3v1Tag.getTrack());
		System.out.println("Artist: " + id3v1Tag.getArtist());
		System.out.println("Title: " + id3v1Tag.getTitle());
		System.out.println("Album: " + id3v1Tag.getAlbum());
		System.out.println("Year: " + id3v1Tag.getYear());
		System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
		System.out.println("Comment: " + id3v1Tag.getComment());
	}
}