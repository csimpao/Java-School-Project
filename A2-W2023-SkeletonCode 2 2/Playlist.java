import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
// Name: Carlos Simpao, ID: 501165939
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		// Loops trhough the contents arraylist
		for (int i = 0; i < contents.size(); i++) {
			// Starts at Chapter 1, or index 0, and it prints the corresponding content
			System.out.print((i+1) + ". ");
			contents.get(i).printInfo();
			// Space in between
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		// Loops through the contents list
		for (int i = 0; i < contents.size(); i++) {
			// Simply plays all the contents
			contents.get(i).play();
			// Space in between
			System.out.println();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		// Checks the valid range of the index
		if (index >= 1 && index <= contents.size()) {
			// Prints the content based of that index
			contents.get(index-1).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist otherTitle = (Playlist) other;
		return otherTitle.getTitle().equals(title);

	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		// Uses the contains method
		// True = remove content; False = return
		if (!contains(index)) return;
		contents.remove(index-1);
	}
}
