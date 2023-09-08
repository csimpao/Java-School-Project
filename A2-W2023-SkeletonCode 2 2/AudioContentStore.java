import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library
// Name: Carlos Simpao, ID: 501165939
public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		// Initialize the three private maps
		private Map<String, ArrayList<Integer>> artistLocations;
		private Map<String, ArrayList<Integer>> genreLocations;
		private Map<String, Integer> titleLocations;

		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			// Puts the three maps into the constructor
			artistLocations = new HashMap<String, ArrayList<Integer>>();
			genreLocations = new HashMap<String, ArrayList<Integer>>();
			titleLocations = new HashMap<String, Integer>();
			// Try and Catch to read from store.txt with catching an IOException
			try {
				// Contents will be the arraylist that was made from the readStore() method
				contents = readStore();
			} catch (IOException io) {
				// Prints the error message and exits
				System.out.println(io.getMessage());
				System.exit(1);
			}
		  // Create some songs audiobooks and podcasts and to store
			// String file = "Yesterday, all my troubles";
			// contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
			
			// file = "I'm sorry if I seem uninterested\r\n"
			// 		+ "Or I'm not listenin' or I'm indifferent\r\n"
			// 		+ "Truly, I ain't got no business here\r\n"
			// 		+ "But since my friends are here, I just came to kick it\r\n"
			// 		+ "But really I would rather be at home all by myself not in this room\r\n"
			// 		+ "With people who don't even care about my well being";
			// contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			// file = "Yo, Big Shaq, the one and only\r\n"
			// 		+ "Man's not hot, never hot\r\n"
			// 		+ "Skrrat (GottiOnEm), skidi-kat-kat\r\n"
			// 		+ "Boom\r\n"
			// 		+ "Two plus two is four\r\n"
			// 		+ "Minus one that's three, quick maths\r\n"
			// 		+ "Everyday man's on the block\r\n"
			// 		+ "Smoke trees (Ah)";
			// contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah", Song.Genre.RAP, file));
			
			// file = "The world was on fire and no one could save me but you\r\n"
			// 		+ "It's strange what desire will make foolish people do\r\n"
			// 		+ "I never dreamed that I'd meet somebody like you\r\n"
			// 		+ "And I never dreamed that I'd lose somebody like you";
			// contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak", Song.Genre.ROCK, file));
			
			// file = "The lights go out and I can't be saved\r\n"
			// 		+ "Tides that I tried to swim against\r\n"
			// 		+ "Have brought me down upon my knees\r\n"
			// 		+ "Oh, I beg, I beg and plead\r\n"
			// 		+ "Singin' come out of things un said";
			// contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin", Song.Genre.ROCK, file));
			
			// file = "I'm waking up to ash and dust\r\n"
			// 		+ "I wipe my brow and I sweat my rust\r\n"
			// 		+ "I'm breathing in the chemicals";
			// contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons", "Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));
			
			// file = "Birds flying high\r\n"
			// 		+ "You know how I feel\r\n"
			// 		+ "Sun in the sky\r\n"
			// 		+ "You know how I feel\r\n"
			// 		+ "Breeze driftin' on by\r\n"
			// 		+ "You know how I feel\r\n"
			// 		+ "It's a new dawn\r\n"
			// 		+ "It's a new day\r\n"
			// 		+ "It's a new life\r\n"
			// 		+ "For me";
			// contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone", 
			// 		"Anthony Newley, Leslie Bricusse",Song.Genre.JAZZ, file));
			
			// file = "Find table spaces, say your social graces\n"
			// 		+ "Bow your head, they're pious here\n"
			// 		+ "But you and I, we're pioneers, we make our own rules\n"
			// 		+ "Our own room, no bias here";
			// contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			// AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
			// 		"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
			// contents.add(book);
			
			// book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME,  "", 1422,
			// 		"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
			// contents.add(book);
			
			// book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME,  "", 3213,
			// 		"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
			// contents.add(book);
			
			// Create a podcast object if you are doing the bonus see the makeSeasons() method below
			// It is currently commented out. It makes use of a class Season you may want to also create
			// or change it to something else

			// A method to make all the three maps
			makeMap();
		}
		
		// Method to populate the contents arraylist using the file with IOException
		private ArrayList<AudioContent> readStore() throws IOException {
			// Make a new arraylist to store all the file object contents
			ArrayList<AudioContent> readStore = new ArrayList<AudioContent>();
			// Creates a new scanner to scan the store text file
			Scanner sc = new Scanner(new File("fixedstore.txt"));
			// Reads the file
			while (sc.hasNextLine()) {
				// Reads each line
				String type = sc.nextLine();
				// Checks if that line is a content type and determines if it's a song
				if (type.equals(Song.TYPENAME)) {
					// Extracts all the necessary data for a song based on the formatting
					String id = sc.nextLine();
					String title = sc.nextLine();
					int year = sc.nextInt();
					int length = sc.nextInt();
					sc.nextLine();
					String artist = sc.nextLine();
					String composer = sc.nextLine();
					// valueOf() is a method of getting the value from the enum
					Song.Genre genre = Song.Genre.valueOf(sc.nextLine());
					int fileLength = sc.nextInt();
					sc.nextLine();
					// Extracting all the lyrics
					String audiofile = "";
					for (int i = 0; i < fileLength; i++) {
						audiofile += sc.nextLine() + "\n";
					}
					System.out.println("Loading " + Song.TYPENAME);
					// Adds the new song object with that extracted data
					readStore.add(new Song(title, year, id, type, audiofile, length, artist, composer, genre, audiofile));

				// Checks if that line is a content type and determines if it's a book
				} else if (type.equals(AudioBook.TYPENAME)) {
					// Extracts all the necessary data for a book based on the formatting
					String id = sc.nextLine();
					String title = sc.nextLine();
					int year = sc.nextInt();
					int length = sc.nextInt();
					sc.nextLine();
					String author = sc.nextLine();
					String narrator = sc.nextLine();
					int fileLength = sc.nextInt();
					sc.nextLine();
					ArrayList<String> chapterTitles = new ArrayList<String>();
					ArrayList<String> chapters = new ArrayList<String>();
					for (int i = 0; i < fileLength; i++) {
						chapterTitles.add(sc.nextLine());
					}
					// Extracting all the content of the chapters
					for (int i = 0; i < chapterTitles.size(); i++) {
						int chapterLength = sc.nextInt();
						sc.nextLine();
						// Extracting all the contents of each chapter
						String audiofile = "";
						for (int j = 0; j < chapterLength; j++) {
							audiofile += sc.nextLine() + "\n";
						}
						chapters.add(audiofile);
					}
					
					System.out.println("Loading " + AudioBook.TYPENAME);
					// Adds the new book object with that extracted data with audiofile set to empty string
					readStore.add(new AudioBook(title, year, id, type, "", length, author, narrator, chapterTitles, chapters));
				}
			}
			// Returns that list
			return readStore;
		}

		// Method to create those three maps
		public void makeMap() {
			// Loops through the contents list
			for (int i = 0; i < contents.size(); i++) {
				// Initialize the content type
				String contentType = contents.get(i).getType();
				// Checks if the contentType is "SONG"
				if (contentType.equals(Song.TYPENAME)) {
					Song songs = (Song) contents.get(i);
					String songGenres = songs.getGenre().name();

					// Puts their corresponding title integer into the map (1-indexed)
					titleLocations.put(songs.getTitle(), i+1);
					// Checks if the artist key doesn't exist
					if (artistLocations.get(songs.getArtist()) == null) {
						// Creates a new integer arraylist if it doesn't exist
						artistLocations.put(songs.getArtist(), new ArrayList<Integer>());
					} 
					// Else it adds their corresponding index, starting at 1
					artistLocations.get(songs.getArtist()).add(i+1);
					
					// Checks if the genre key doesn't exist
					if (genreLocations.get(songGenres) == null) {
						// Creates a new integer arraylist if it doesn't exist
						genreLocations.put(songGenres, new ArrayList<Integer>());
					}
					// Else it'll add their corresponding genre index (1-indexed)
					genreLocations.get(songGenres).add(i+1);

				// Also checks if the contentType is "AUDIOBOOK"
				} else if (contentType.equals(AudioBook.TYPENAME)){
					AudioBook books = (AudioBook) contents.get(i);
					// Puts the index for their corresponding book title
					titleLocations.put(books.getTitle(), i+1);
					// Checks if that author key has nothing in arraylist
					if (artistLocations.get(books.getAuthor()) == null) {
						// Creates a new arraylist for the author key
						artistLocations.put(books.getAuthor(), new ArrayList<Integer>());
					}
					// Else it'll add the index for their author, starting at 1
					artistLocations.get(books.getAuthor()).add(i+1);
				}
			}
		}

		// Method to search for the title (song/audiobook) and throws a content exception
		public void search(String title) throws ContentException {
			// Throws an exception if the value of the title key is null
			if (titleLocations.get(title) == null) {
				throw new ContentException("No matches for " + title);
			}
			// Else it'll print the info based on the title key
			int titleIndex = titleLocations.get(title);
			System.out.print(titleIndex + ". ");
			contents.get(titleIndex-1).printInfo();
		}

		// Method to search for the artist/author and also throws a content exception
		public void searchA(String artist) throws ContentException {
			// Throws an exception if the value of the artist key is null
			if (artistLocations.get(artist) == null) {
				throw new ContentException("No matches for " + artist);
			}

			// Loops through the arraylist of the corresponding artist key
			for (int i = 0; i < artistLocations.get(artist).size(); i++) {
				// Gets all the indexes of the artist key
				int artistIndex = artistLocations.get(artist).get(i);
				// Prints all the info based on the artist
				System.out.print(artistIndex + ". ");
				contents.get(artistIndex-1).printInfo();
				System.out.println();
			} 
		}

		// Method to search for the songs with the genre input and also throws a song exception
		public void searchG(String genre) throws SongException {
			// Throws an exception if the value of the artist key is null
			if (genreLocations.get(genre) == null) {
				throw new SongException("No matches for " + genre);
			}

			// Loops through the arraylist of the corresponding genre key
			for (int i = 0; i < genreLocations.get(genre).size(); i++) {
				// Gets all the indexes of the genre key
				int genreIndex = genreLocations.get(genre).get(i);
				// Prints all the info based on the genre
				System.out.print(genreIndex + ". ");
				contents.get(genreIndex-1).printInfo();
				System.out.println();
			} 
		}
		
		// Method to search for all the content with the substring as the target
		public void searchP(String target) {
			// Loops through each object in contents list
			for (int i = 0; i < contents.size(); i++) {
				// Setting the target content and the type
				AudioContent targetContent = contents.get(i);
				String contentType = targetContent.getType();
				// Checks if the content type is a Song
				if (contentType.equals(Song.TYPENAME)) {
					Song song = (Song) contents.get(i);
					// Converting Integers into Strings to help with the search of strings only
					String songYear = Integer.toString(song.getYear());
					String songLength = Integer.toString(song.getLength());
					// Checks if the target string is in any of all song properties (i.e - name() helps to convert an enum to a string)
					if (song.getArtist().contains(target) || song.getAudioFile().contains(target) || song.getComposer().contains(target) || song.getGenre().name().contains(target) || song.getLyrics().contains(target) || song.getTitle().contains(target) || song.getType().contains(target) || songYear.contains(target) || songLength.contains(target) || song.getId().contains(target)) {
						// Prints that info
						System.out.print(i+1 + ". ");
						targetContent.printInfo();
						System.out.println();
						// Continue to the next iteration
						continue;
					} 
				
				// Checks if the content type is an AudioBook
				} else if (contentType.equals(AudioBook.TYPENAME)){
					AudioBook book = (AudioBook) contents.get(i);
					// Converting Integers into Strings to help with the search of strings only
					String bookYear = Integer.toString(book.getYear());
					String bookLength = Integer.toString(book.getLength());
					// Checks if the target string is in any of all book properties
					if (book.getAudioFile().contains(target) || book.getAuthor().contains(target) || book.getNarrator().contains(target) || book.getTitle().contains(target) || book.getType().contains(target) || bookYear.contains(target) || bookLength.contains(target) || book.getId().contains(target)) {
						// Prints that info
						System.out.print(i+1 + ". ");
						targetContent.printInfo();
						System.out.println();
						// Continue to the next iteration
						continue;
					} 

					// Loops through all the chapter titles
					for (int j = 0; j < book.getChapterTitles().size(); j++) {
						// Checks if the target string is in any of the chapter titles
						if (book.getChapterTitles().get(j).contains(target)) {
							// Prints that info
							System.out.print(i+1 + ". ");
							targetContent.printInfo();
							System.out.println();
							// Continue to the next iteration
							continue;
						} 
					}

					// Loops through each chapter content
					for (int j = 0; j < book.getChapters().size(); j++) {
						// Checks if the target string is in any of the chapter contents
						if (book.getChapters().get(j).contains(target)) {
							// Prints that info
							System.out.print(i+1 + ". ");
							targetContent.printInfo();
							System.out.println();
							// Continue to the next iteration
							continue;
						} 
					}
				} 
			}
		}

		// Method to download all the songs/books with the artist input and also throws a content exception
		public ArrayList<AudioContent> downloadA(String artist) throws ContentException {
			// Initialize an artist song arraylist
			ArrayList<AudioContent> getArtists = new ArrayList<AudioContent>();
			// Throws an exception if the value of the artist/author key is null
			if (artistLocations.get(artist) == null) {
				throw new ContentException("No matches for " + artist);
			}

			// Loops through the arraylist of the corresponding artist key
			for (int i = 0; i < artistLocations.get(artist).size(); i++) {
				// Gets all the indexes of the artist key
				int artistIndex = artistLocations.get(artist).get(i);
				// Adds all the contents to the arraylist based on the artist/author
				getArtists.add(contents.get(artistIndex-1));
			} 

			// This arraylist will be used for the download method in Library class
			return getArtists;
		}

		// Method to download all the songs with the genre input and also throws a song exception
		public ArrayList<AudioContent> downloadG(String genre) throws SongException {
			// Initialize an artist genre arraylist
			ArrayList<AudioContent> genreContent = new ArrayList<AudioContent>();
			// Throws an exception if the value of the genre key is null
			if (genreLocations.get(genre) == null) {
				throw new SongException("No matches for " + genre);
			}

			// Loops through the arraylist of the corresponding genre key
			for (int i = 0; i < genreLocations.get(genre).size(); i++) {
				// Gets all the indexes of the genre key
				int genreIndex = genreLocations.get(genre).get(i);
				// Adds all the contents to the arraylist based on the genre
				genreContent.add(contents.get(genreIndex-1));
			}

			// This arraylist will be used for the download method in Library class
			return genreContent;
		}

		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		// private ArrayList<String> makeHPChapterTitles()
		// {
		// 	ArrayList<String> titles = new ArrayList<String>();
		// 	titles.add("The Riddle House");
		// 	titles.add("The Scar");
		// 	titles.add("The Invitation");
		// 	titles.add("Back to The Burrow");
		// 	return titles;
		// }

		// private ArrayList<String> makeHPChapters()
		// {
		// 	ArrayList<String> chapters = new ArrayList<String>();
		// 	chapters.add("In which we learn of the mysterious murders\r\n"
		// 			+ " in the Riddle House fifty years ago, \r\n"
		// 			+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
		// 			+ "and how the Riddle House fell into disrepair. ");
		// 	chapters.add("In which Harry awakens from a bad dream, \r\n"
		// 			+ "his scar burning, we recap Harry's previous adventures, \r\n"
		// 			+ "and he writes a letter to his godfather.");
		// 	chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
		// 			+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
		// 			+ " with her family and attend the World Quidditch Cup finals.");
		// 	chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
		// 			+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
		// 			+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
		// 			+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
		// 			+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
		// 	return chapters;
		// }
		
		// private ArrayList<String> makeMDChapterTitles()
		// {
		// 	ArrayList<String> titles = new ArrayList<String>();
		// 	titles.add("Loomings.");
		// 	titles.add("The Carpet-Bag.");
		// 	titles.add("The Spouter-Inn.");
		// 	return titles;
		// }
		// private ArrayList<String> makeMDChapters()
		// {
		// 	ArrayList<String> chapters = new ArrayList<String>();
		// 	chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
		// 			+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
		// 			+ " I thought I would sail about a little and see the watery part of the world.");
		// 	chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
		// 			+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
		// 			+ "I duly arrived in New Bedford. It was a Saturday night in December.");
		// 	chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
		// 			+ "low, straggling entry with old-fashioned wainscots, \r\n"
		// 			+ "reminding one of the bulwarks of some condemned old craft.");
		// 	return chapters;
		// }
		
		// private ArrayList<String> makeSHChapterTitles()
		// {
		// 	ArrayList<String> titles = new ArrayList<String>();
		// 	titles.add("Prologue");
		// 	titles.add("Chapter 1");
		// 	titles.add("Chapter 2");
		// 	titles.add("Chapter 3");
		// 	return titles;
		// }
		
		// private ArrayList<String> makeSHChapters()
		// {
		// 	ArrayList<String> chapters = new ArrayList<String>();
		// 	chapters.add("The gale tore at him and he felt its bite deep within\r\n"
		// 			+ "and he knew that if they did not make landfall in three days they would all be dead");
		// 	chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
		// 			+ "because he was ashore and the room unbelieveable");
		// 	chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
		// 			+ "where you come from, how ou got here, and what acts of piracy you have committed.");
		// 	chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
		// 	return chapters;
		// }
		
		// Podcast Seasons
		/* 
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.getEpisodeTitle().add("Bay Blanket");
		  s1.getEpisodeTitle().add("You Don't Want to Sleep Here");
		  s1.getEpisodeTitle().add("The Gold Rush");
		  s1.getEpisodeFile().add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.getEpisodeFile().add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.getEpisodeFile().add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.getEpisodeLength().add(31);
		  s1.getEpisodeLength().add(32);
		  s1.getEpisodeLength().add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.getEpisodeTitle().add("Toronto vs Everyone");
		  s2.getEpisodeTitle().add("Water");
		  s2.getEpisodeFile().add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.getEpisodeFile().add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.getEpisodeLength().add(45);
		  s2.getEpisodeLength().add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/
}
