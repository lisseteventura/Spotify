package com.example.spotify3.controller;

import com.example.spotify3.models.Song;
import com.example.spotify3.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    SongService songService;
    //create songs
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }
    //get list of songs
    @GetMapping("/list")
    public Iterable<Song> listSongs(){
        return songService.listSongs();
    }
}
