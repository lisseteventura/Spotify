package com.example.spotify3.repository;

import com.example.spotify3.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

}
