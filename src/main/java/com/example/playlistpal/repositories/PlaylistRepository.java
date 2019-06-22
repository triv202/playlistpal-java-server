package com.example.playlistpal.repositories;

import com.example.playlistpal.models.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

}
