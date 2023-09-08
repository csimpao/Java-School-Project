import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)
// Name: Carlos Simpao, ID: 501165939
public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the range of indices of the contents
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				// Initialize fromIndex and toIndex variables
				int fromIndex = 0;
				int toIndex = 0;

				// Have the user type in a beginning content number
				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt()) {
					fromIndex = scanner.nextInt();
					scanner.nextLine();
				}

				// Have the user type in an ending content number
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt()) {
					toIndex = scanner.nextInt();
					scanner.nextLine();
				}

				// Loops from the fromIndex variable, to the toIndex variable (inclusive)
				for (int i = fromIndex; i <= toIndex; i++) {
					// Gets all the content from the range
					AudioContent content = store.getContent(i);
					try {
						// Downloads the content based on the index
						mylibrary.download(content);
						// Prints what's been downloaded from the index range
						System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
					} catch (ContentException contE) {
						// Prints an error message if the content is already downloaded
						System.out.println(contE.getMessage());
					} catch (NullPointerException nu) {
						// Prints an error message if they're invalid indices (similar to the old version below)
						System.out.println("Content Not Found in Store");
					}
				}
			

				// int index = 0;
				
				// System.out.print("Store Content #: ");
				// if (scanner.hasNextInt())
				// {
				// 	index = scanner.nextInt();
				// 	scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				// }
				// AudioContent content = store.getContent(index);
				// if (content == null)
				// 	System.out.println("Content Not Found in Store");
				// // else if (!mylibrary.download(content))
				// // 		System.out.println(mylibrary.getErrorMessage());

				
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				// Print error message if the song doesn't exist in the library
				
				// Have the user type in a song number
				System.out.print("Song Number: ");
				int index = 0;
				if (scanner.hasNextInt()) {
					index = scanner.nextInt();
					scanner.nextLine();
				}
				
				// Try and Catch on playSong method
				try {
					mylibrary.playSong(index);
				} catch (SongException songE) {
					System.out.println(songE.getMessage());
				}
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				// Print error message if the book doesn't exist in the library
				System.out.print("Audio Book Number: ");
				// Have the user type in a book number
				int index = 0;
				if (scanner.hasNextInt()) {
					index = scanner.nextInt();
					scanner.nextLine();
				}

				// Try and Catch on printing the bookTOC
				try {
					mylibrary.printAudioBookTOC(index);
				} catch (BookException bookE) {
					System.out.println(bookE.getMessage());
				}

			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				// Have the user type in a book number
				System.out.print("Audio Book Number: ");
				int index = 0;
				if (scanner.hasNextInt()) {
					index = scanner.nextInt();
					scanner.nextLine();
				}

				// Have the user also type in a chapter number
				System.out.print("Chapter: ");
				int chapter = 0;
				if (scanner.hasNextInt()) {
					chapter = scanner.nextInt();
					scanner.nextLine();
				}

				// Try and Catch on playing an audiobook
				try {
					mylibrary.playAudioBook(index, chapter);
				} catch (BookException bookE) {
					System.out.println(bookE.getMessage());
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}

				// Try and Catch on playing the whole playlist
				try {
					mylibrary.playPlaylist(plTitle);
				} catch (PlaylistException playE) {
					System.out.println(playE.getMessage());
				}

			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}

				/// Have the user type in the content number
				System.out.print("Content Number: ");
				int contentNum = 0;
				if (scanner.hasNextInt()) {
					contentNum = scanner.nextInt();
					scanner.nextLine();
				}

				// Try and Catch on playing specific content in a playlist
				try {
					mylibrary.playPlaylist(plTitle, contentNum);
				} catch (ContentException con) {
					System.out.println(con.getMessage());
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				// Have the user type in a song number
				System.out.print("Library Song #: ");
				int contentNum = 0;
				if (scanner.hasNextInt()) {
					contentNum = scanner.nextInt();
					scanner.nextLine();
				}

				// Try and Catch on deleteSong method
				try {
					mylibrary.deleteSong(contentNum);
				} catch (SongException songE) {
					System.out.println(songE.getMessage());
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}
				
				// Try and Catch on making the playlist
				try {
					mylibrary.makePlaylist(plTitle);
				} catch (PlaylistException playE) {
					System.out.println(playE.getMessage());
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}

				// Try and Catch on printing a playlist
				try {
					mylibrary.printPlaylist(plTitle);
				} catch (PlaylistException playE) {
					System.out.println(playE.getMessage());
				}
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}

				// Have the user type in the type of content
				System.out.print("Content Type [SONG, AUDIOBOOK]: ");
				String contentType = " ";
				if (scanner.hasNextLine()) {
					contentType = scanner.nextLine();
				}

				// Have the user type in the content number
				System.out.print("Library Content #: ");
				int plIndex = 0;
				if (scanner.hasNextInt()) {
					plIndex = scanner.nextInt();
					scanner.nextLine();
				}

				// Try and Catch on adding content to a playlist
				try {
					mylibrary.addContentToPlaylist(contentType, plIndex, plTitle);
				} catch (PlaylistException playE) {
					System.out.println(playE.getMessage());
				}

			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				// Have the user type in a playlist
				System.out.print("Playlist Title: ");
				String plTitle = " ";
				if (scanner.hasNextLine()) {
					plTitle = scanner.nextLine();
				}

				// Have the user type in the playlist content number
				System.out.print("Playlist Content #: ");
				int plIndex = 0;
				if (scanner.hasNextInt()) {
					plIndex = scanner.nextInt();
					scanner.nextLine();
				}
				
				// Try and Catch on deleting content from a playlist
				try {
					mylibrary.delContentFromPlaylist(plIndex, plTitle);
				} catch (ContentException con) {
					System.out.println(con.getMessage());
				}
			}
			// Search the content store and have it print the correct content
			// based on the user's input of the title (Song/AudioBook)
			else if (action.equalsIgnoreCase("SEARCH")) 
			{
				// Have the user type in title
				System.out.print("Title: ");
				String title = " ";
				if (scanner.hasNextLine()) {
					title = scanner.nextLine();
				}

				// Try and Catch on search method
				try {
					store.search(title);
				} catch (ContentException con) {
					System.out.println(con.getMessage());
				}
			}

			// Search the content store and have it print the correct content
			// based on the user's input of the artist (Song/AudioBook also)
			else if (action.equalsIgnoreCase("SEARCHA")) 
			{
				// Have the user type in an artist/author
				System.out.print("Artist: ");
				String artist = " ";
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();
				}

				// Try and Catch on searchA method
				try {
					store.searchA(artist);
				} catch (ContentException con) {
					System.out.println(con.getMessage());
				}
			}

			// Search the content store and have it print the correct content
			// based on the user's input of the genre
			else if (action.equalsIgnoreCase("SEARCHG")) 
			{
				// Have the user type in a genre
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				String genre = " ";
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();
				}

				// Try and Catch on searchG method
				try {
					store.searchG(genre);
				} catch (SongException song) {
					System.out.println(song.getMessage());
				}
			}
			
			// Download correct content from the content store
			// based on the user's input of the artist (Song/AudioBook also)
			else if (action.equalsIgnoreCase("DOWNLOADA")) 
			{
				// Have the user type in an artist/author
				System.out.print("Artist Name: ");
				String artist = " ";
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();
				}

				// Try and Catch to catch the error of not giving a valid artist to make the arraylist in only downloadA
				try {
					// Create an audiocontent arraylist using downloadA method with the artist
					ArrayList<AudioContent> art = store.downloadA(artist);
					// Loops through the arraylist
					for (int i = 0; i < art.size(); i++) {
						// Try and Catch on download method
						try {
							// Downloads all the content with the same artist/author
							mylibrary.download(art.get(i));
							// Prints what content is added to the library based on the artist/author
							System.out.println(art.get(i).getType() + " " + art.get(i).getTitle() + " Added to Library");
						// Prints the error from download method
						} catch (ContentException con) {
							System.out.println(con.getMessage());
						}
					}
				// Prints the error from downloadA method	
				} catch (ContentException con) {
					System.out.println(con.getMessage());
				}
			}

			// Download correct content from the content store
			// based on the user's input of the genre
			else if (action.equalsIgnoreCase("DOWNLOADG")) 
			{
				// Have the user type in a genre
				System.out.print("Genre: ");
				String genre = " ";
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();
				}

				// Try and Catch to catch the error of not giving a valid genre to make the arraylist in only downloadG
				try {
					// Create an audiocontent arraylist using downloadG method with the artist
					ArrayList<AudioContent> gen = store.downloadG(genre);
					// Loops through the arraylist
					for (int i = 0; i < gen.size(); i++) {
						// Try and Catch for download method
						try {
							// Downloads all the content with the same genre
							mylibrary.download(gen.get(i));
							// Prints what song is added to the library based on the genre
							System.out.println(gen.get(i).getType() + " " + gen.get(i).getTitle() + " Added to Library");
						// Prints the error from download method
						} catch (ContentException con) {
							System.out.println(con.getMessage());
						}
					}
				// Prints the error from downloadG method
				} catch (SongException song) {
					System.out.println(song.getMessage());
				}
			}

			// Search the content store and have it print all the correct content
			// based on the user's input of the target string (or the substring)
			// Bonus
			else if (action.equalsIgnoreCase("SEARCHP"))
			{
				// Have the user type in a target string
				System.out.print("Target String: ");
				String target = " ";
				if (scanner.hasNextLine()) {
					target = scanner.nextLine();
				}

				// Calling method searchP
				store.searchP(target);
			}

			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}
