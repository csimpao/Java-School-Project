import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Name: Carlos Simpao, ID: 501165939
/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	// private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	// String errorMsg = "";
	
	// public String getErrorMessage()
	// {
	// 	return errorMsg;
	// }

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	//   podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	// Added a content exception
	public void download(AudioContent content) throws ContentException
	{
		// Checks if content = SONG and that song is not in the songs list
		if (content.getType().equals(Song.TYPENAME) && (!songs.contains(content))) {
			// It'll add that song to songs list and return true
			songs.add((Song) content);
			// return true;
		} else if (content.getType().equals(Song.TYPENAME) && songs.contains(content)) {
			// It'll print this error message if the song is already in there
			throw new ContentException(Song.TYPENAME + " " + content.getTitle() + " already downloaded");
		} 
		
		// Checks if content = AUDIOBOOK and that audiobook is not in the audiobooks list
		if (content.getType().equals(AudioBook.TYPENAME) && (!audiobooks.contains(content))) {
			// It'll add that book to audiobooks list and return true
			audiobooks.add((AudioBook) content);
			// return true;
		} else if (content.getType().equals(AudioBook.TYPENAME) && (audiobooks.contains(content))) {
			// It'll print this error message if the audiobook is already in there
			throw new ContentException(AudioBook.TYPENAME + " " + content.getTitle() + " already downloaded");
		} 
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		// Loops through the audiobooks list and prints each info of the book
		for (int i = 0; i < audiobooks.size(); i++) {
			// Lists always start at 1, hence index (i+1)
			System.out.print("" + (i+1) + ". ");
			audiobooks.get(i).printInfo();
			// Space in between
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		// Loops through the playlists list and prints each info of the playlist
		for (int i = 0; i < playlists.size(); i++) {
			// Lists always start at 1, hence index (i+1)
			System.out.println("" + (i+1) + ". " + playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		// New list called songsAlready
		ArrayList<String> artistsAlready = new ArrayList<String>();
		// Loops through the songs list
		for (int i = 0; i < songs.size(); i++) {
			// Checks to see if the artist is not in the new list
			if (!artistsAlready.contains(songs.get(i).getArtist())) {
				// If it's not, then it'll add that artist to the new list
				artistsAlready.add(songs.get(i).getArtist());
			}
		}

		// Prints out all the current artists
		for (int j = 0; j < artistsAlready.size(); j++) {
			System.out.println((j+1) + ". " + artistsAlready.get(j));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	// Added a Song exception
	public void deleteSong(int index) throws SongException
	{
		// It'll remove the song based on the index of songs list (index-1)
		if (index >= 1 && index <= songs.size()) {
			// Saves the song that's being deleted in a Song variable, so that it won't be immediately deleted from songs list
			Song savedSong = songs.get(index-1);

			// Immediately removes that song (not the saved song)
			songs.remove(index-1);
			
			// Loops through the playlists list
			for (int i = 0; i < playlists.size(); i++) {
				// Checks if the saved song is in a playlist 
				if (playlists.get(i).getContent().contains(savedSong)) {
					// Removes that specific song (savedSong) from the playlist
					playlists.get(i).getContent().remove(savedSong);
				} 
			}
		// It'll print this error message if it's not a valid song index
		} else {
			throw new SongException("Song Not Found");
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 

		// Uses the SongYearComparator class in the sort method
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		// A method to compare 2 song objects based on their year
		public int compare(Song year1, Song year2) {
			// If this song year is bigger than the other year
			if (year1.getYear() > year2.getYear()) {
				return 1;
			}
			// If this song year is less than the other year
			if (year1.getYear() < year2.getYear()) {
				return -1;
			}
			// Both of their years are equal
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 

	 // Uses the SongLengthComparator class in the sort method
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song len1, Song len2) {
			// If this length is bigger than the other length
			if (len1.getLength() > len2.getLength()) {
				return 1;
			}
			// If this length is less than the other length
			if (len1.getLength() < len2.getLength()) {
				return -1;
			}
			// Both of their lengths are equal
			return 0;
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code

		// Uses the sort method, which at default would use the compareTo() method to organize the titles alphabetically
		Collections.sort(songs);
	}
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	// Added a Song exception
	public void playSong(int index) throws SongException
	{
		if (index < 1 || index > songs.size())
		{
			throw new SongException("Song Not Found");
		}
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	// Added a Book exception
	public void playAudioBook(int index, int chapter) throws BookException
	{
		// Checks for an invalid audiobook number
		if (index < 1 || index > audiobooks.size()) {
			// Prints an error message if a book is not found (invalid index)
			throw new BookException("AudioBook Not Found");
		}

		// Checks for an invalid chapter number
		if (chapter < 1 || chapter > audiobooks.get(index-1).getNumberOfChapters()) {
			// Prints an error message when a chapter is not found
			throw new BookException("Chapter Not Found");
		}

		// Gets the book, selects the chapter, and plays it
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();	
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	// Added a Book exception
	public void printAudioBookTOC(int index) throws BookException
	{
		// Checks for an invalid audiobook number
		if (index < 1 || index > audiobooks.size()) {
			// Prints an error message if a book is not found (invalid index)
			throw new BookException("AudioBook Not Found");
		}
		// Prints the table of contents of that book
		audiobooks.get(index-1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	// Added a Playlist exception
	public void makePlaylist(String title) throws PlaylistException
	{
		// Make a new Playlist object
		Playlist pl = new Playlist(title);
		// Loop through the playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// Checks to see if that title input is equal to any of the playlist titles
			if (pl.getTitle().equals(playlists.get(i).getTitle())) {
				// If it is, the object is already in arraylist (prints an error message that the playlists exists)
				throw new PlaylistException("Playlist " + title + " already Exists");
			}
		}
		// Else, that object needs to be added to arraylist and return true
		playlists.add(pl);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	// Added a Playlist exception
	public void printPlaylist(String title) throws PlaylistException
	{
		// Loops through the playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// Checks to see if that title input is equal to any of the playlist titles
			if (title.equals(playlists.get(i).getTitle())) {
				// It'll print all the contents of that specific playlist
				playlists.get(i).printContents();
			} else {
				// It'll print this error message if the playlist is invalid
				throw new PlaylistException("Playlist Not Found");
			}
		}
	}
	
	// Play all content in a playlist
	// Added a Playlist exception
	public void playPlaylist(String playlistTitle) throws PlaylistException
	{
		// Loops through the playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// Checks to see if that title input is equal to any of the playlist titles
			if (playlistTitle.equals(playlists.get(i).getTitle())) {
				// It'll print all of the contents of the playlist
				playlists.get(i).playAll();
			} else {
				// It'll print this error message if the playlist is invalid
				throw new PlaylistException("Playlist Not Found");
			}
		}
	}
	
	// Play a specific song/audiobook in a playlist
	// Added a Playlist exception
	public void playPlaylist(String playlistTitle, int indexInPL) throws ContentException
	{
		// Loops through the playlists list
		for (int i = 0; i < playlists.size(); i++) {
			// Checks to see if that title input is equal to any of the playlist titles and it'll also use the contains method for the valid index
			if (playlistTitle.equals(playlists.get(i).getTitle()) && playlists.get(i).contains(indexInPL)) {
				// It'll play that specific content in the playlist
				playlists.get(i).getContent().get(indexInPL-1).play();
			} else {
				// It'll print this error message if it gives an invalid playlist title and/or index
				throw new ContentException("Content Not Found");
			}
		}
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	// Added a Playlist exception
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws PlaylistException
	{
		// Checks if type = AUDIOBOOK
		if (type.equalsIgnoreCase(AudioBook.TYPENAME)) {
			// Loops through the playlists
			for (int i = 0; i < playlists.size(); i++) {
				// Checks if the playlist title is in the playlists and checks the range of the index of audiobooks list
				if (playlistTitle.equals(playlists.get(i).getTitle()) && index >= 1 && index <= audiobooks.size()) {
					// Adds the audiobook to the specific playlist
					playlists.get(i).addContent(audiobooks.get(index-1));
				} 
			}
		// Checks if type = SONG
		} else if (type.equalsIgnoreCase(Song.TYPENAME)) {
			// Loops through the playlists
			for (int i = 0; i < playlists.size(); i++) {
				// Checks if the playlist title is in the playlists and checks the range of the index of songs list
				if (playlistTitle.equals(playlists.get(i).getTitle()) && index >= 1 && index <= songs.size()) {
					// Adds the song to the specific playlist
					playlists.get(i).addContent(songs.get(index-1));
				} 
			}
		} else {
			// It'll print this error message if they give an invalid type, index, and/or playlist title
			throw new PlaylistException("Playlist " + playlistTitle + " Not Found/Exists and/or Invalid Type and/or Invalid Content Number");
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	// Added a Content exception
	public void delContentFromPlaylist(int index, String title) throws ContentException
	{
		// Loops through the playlists
		for (int i = 0; i < playlists.size(); i++) {
			// Checks if the input title is in the playlists and checks the range of the index by using the contains method from class Playlist
			if (title.equals(playlists.get(i).getTitle()) && playlists.get(i).contains(index)) {
				// Removes that content from the specific playlist
				playlists.get(i).deleteContent(index);
			} else {
				// It'll print this error message if they give invalid playlist title and/or index
				throw new ContentException("Content Not Found");
			}
		}
	}
}


// // Created an exception to check the content
// class ContentNotFoundException extends RuntimeException {
// 	public ContentNotFoundException(String errorMsg) {
// 		super(errorMsg);
// 	}
// }

// // Created an exception to check the songs
// class SongNotFoundException extends RuntimeException {
// 	public SongNotFoundException(String songError) {
// 		super(songError);
// 	}
// }

// // Created an exception to check the books
// class BookNotFoundException extends RuntimeException {
// 	public BookNotFoundException(String bookError) {
// 		super(bookError);
// 	}
// }

// // Created an exception to check the playlists
// class PlaylistException extends RuntimeException {
// 	public PlaylistException(String playlistError) {
// 		super(playlistError);
// 	}
// }

