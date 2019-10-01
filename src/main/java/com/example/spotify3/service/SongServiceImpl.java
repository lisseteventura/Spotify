package com.example.spotify3.service;

import com.example.spotify3.models.Song;
import com.example.spotify3.models.User;
import com.example.spotify3.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Iterable<Song> listSongs(){
        return songRepository.findAll();
    }

}
