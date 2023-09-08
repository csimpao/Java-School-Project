// Name: Carlos Simpao, ID: 501165939
// Created an exception to check the playlists
public class PlaylistException extends RuntimeException {
    public PlaylistException(String playlistError) {
		super(playlistError);
	}
}
