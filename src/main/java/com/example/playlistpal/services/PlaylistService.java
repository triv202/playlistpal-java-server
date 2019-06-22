package com.example.playlistpal.services;

import com.example.playlistpal.models.Playlist;
import com.example.playlistpal.models.Song;
import com.example.playlistpal.models.User;
import com.example.playlistpal.repositories.PlaylistRepository;
import com.example.playlistpal.repositories.SongRepository;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;


@RestController
@CrossOrigin(origins="*")
public class PlaylistService {
  @Autowired
  PlaylistRepository playlistRepository;
  @Autowired
  SongRepository songRepository;

  @GetMapping("api/playlists")
  public Iterable<Playlist> findAllPlaylists(){
    return playlistRepository.findAll();
  }

  @GetMapping("api/playlists/{playlistId}")
  public Optional<Playlist> findWeatherMapById(@PathVariable("playlistId") Integer id) {
    return playlistRepository.findById(id);
  }

  @GetMapping("api/playlists/{playlistId}/songs")
  public List<Song> findSongs(@PathVariable("playlistId") Integer id) {
    Optional<Playlist> playlistOptional = playlistRepository.findById(id);
    if(playlistOptional.isPresent()) {
      Playlist playlist = playlistOptional.get();
      return playlist.getSongs();
    }
    return null;
  }

  @PostMapping("api/playlists/{playlistId}/songs/{songId}")
  public Playlist addSong(@PathVariable("playlistId") Integer plId,
                      @PathVariable("songId") Integer sId) {
    Optional<Song> songOptional = songRepository.findById(sId);
    Optional<Playlist> playlistOptional = playlistRepository.findById(plId);
    if(playlistOptional.isPresent()) {
      if (songOptional.isPresent()) {
        Playlist playlist = playlistOptional.get();
        Song song = songOptional.get();
        playlist.ownsSong(song);
        return playlistRepository.save(playlist);
      }
    }
    return null;

  }

  @PostMapping("/api/playlists")
  public Playlist createPlaylist(@RequestBody Playlist playlist) {
    return playlistRepository.save(playlist);
  }

  @DeleteMapping("api/playlists/{playlistId}")
  public void deletePlaylist(@PathVariable("playlistId") Integer id) {
    Optional<Playlist> playlistOptional = playlistRepository.findById(id);
    if(playlistOptional.isPresent()) {
      Playlist playlist = playlistOptional.get();
      playlistRepository.delete(playlist);
    }
  }
}
