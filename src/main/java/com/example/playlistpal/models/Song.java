package com.example.playlistpal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Song {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String spotifyId;
  @ManyToOne
  @JsonIgnore
  @JoinColumn
  private Playlist playlist;

  public Song () {

  }

  public Song(String spotifyId, Playlist playlist) {
    this.spotifyId = spotifyId;
    this.playlist = playlist;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSpotifyId() {
    return spotifyId;
  }

  public void setSpotifyId(String spotifyId) {
    this.spotifyId = spotifyId;
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
  }
}
