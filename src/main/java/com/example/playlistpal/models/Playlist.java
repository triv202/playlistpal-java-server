package com.example.playlistpal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Playlist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String name;

  @ManyToOne
  @JsonIgnore
  @JoinColumn
  private User user;

  @OneToMany(mappedBy = "playlist", cascade = CascadeType.MERGE)
  private List<Song> songs;

  public Playlist() {

  }

  public Playlist(String name, User user) {
    this.name = name;
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Song> getSongs() {
    return songs;
  }

  public void setSongs(List<Song> songs) {
    this.songs = songs;
  }

  public void ownsSong(Song song) {
    this.songs.add(song);
    if(song.getPlaylist() != this) {
      song.setPlaylist(this);
    }
  }

  public void deleteSong(Song song) {
    this.songs.remove(song);
    if (song.getPlaylist() == this) {
      song.setPlaylist(null);
    }
  }
  
}
