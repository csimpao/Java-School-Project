/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
// Name: Carlos Simpao, ID: 501165939
public class Song extends AudioContent implements Comparable<Song>  // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		
		// Use of the super constructor to inherit AudioContent variables
		super(title, year, id, type, audioFile, length);
		// Initializing other Song properties
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		// Prints the info from AudioContent, and then it prints additional info like artist, composer, and genre
		super.printInfo();
		System.out.println("Artist: " + this.artist + " Composer: " + this.composer + " Genre: " + this.genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		// Sets the AudioFile to the lyrics, and calls play() method from superclass
		setAudioFile(lyrics);
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		// Created an object, and then casting other to the Song class
		Song otherSong = (Song) other;
		// Checks if the composers and artists are equal, and also checks if the AudioContent objects (their info) are equal
		return composer.equals(otherSong.composer) && artist.equals(otherSong.artist) && super.equals(otherSong);
			
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		// Returns 1 if the title is after the other, -1 if the title is before the other, and 0 if they're equal	
		return this.getTitle().compareTo(other.getTitle());
		// return 0;
	}
}
