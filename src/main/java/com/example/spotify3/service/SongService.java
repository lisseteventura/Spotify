package com.example.spotify3.service;

import com.example.spotify3.models.Song;

public interface SongService {
   public Song createSong(Song song);

    public Iterable<Song> listSongs();

}
