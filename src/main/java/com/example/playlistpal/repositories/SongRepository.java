package com.example.playlistpal.repositories;

import com.example.playlistpal.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {

}
