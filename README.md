# Mp3 Organizer

Organize your music using metadata.

## Installation

1) Download the .jar file from the [releases](https://github.com/VictoRPiles/mp3-organizer/releases).
2) Install [Java Runtime 18](https://jdk.java.net/18/) or greater.

If your Linux distro uses apt package manager, install via apt:

```bash
sudo apt install openjdk-18-jre
```

3) Go to the extracted folder and run:

```bash
java -jar mp3-organizer.jar
```

## Usage

```bash
===== MP3 ORGANIZER =====
Introduce la ruta de los archivos de música -> PATH_TO_YOUR_MUSIC
Es un directorio, iniciando búsqueda recursiva...
Se han encontrado 1143 canciones.
1) Artista
   └── Album
       └── Cancion
2) Album
   └── Cancion
3) Artista
   └── Cancion
Elige un árbol de directorios -> CHOOSE_DIRECTORY_TREE
Creando directorios...
```

NOTE: If a path to a directory has been given, the program will recursively search for the music files inside the folder and
sub folders.

Please note that the files have to be .mp3 and have to contain metadata. To download songs with proper metadata I recommend [spotdl](https://github.com/spotDL/spotify-downloader).

That simple!!! Now the program will copy the songs with the selected directory tree structure.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[GPL 3](https://www.gnu.org/licenses/gpl-3.0.html)
