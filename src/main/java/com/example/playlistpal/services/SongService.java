package com.example.playlistpal.services;

import com.example.playlistpal.models.Playlist;
import com.example.playlistpal.models.Song;
import com.example.playlistpal.repositories.PlaylistRepository;
import com.example.playlistpal.repositories.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins="*",  allowCredentials = "true")
public class SongService {
  @Autowired
  SongRepository songRepository;

  @GetMapping("api/songs")
  public Iterable<Song> findAllSongs(){
    return songRepository.findAll();
  }

  @GetMapping("api/songs/{songId}")
  public Optional<Song> findSongById(@PathVariable("songId") Integer id) {
    return songRepository.findById(id);
  }

  @PostMapping("/api/songs")
  public Song createSong(@RequestBody Song song) {
    return songRepository.save(song);
  }
}
