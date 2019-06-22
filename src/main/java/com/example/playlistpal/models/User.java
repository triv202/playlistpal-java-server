package com.example.playlistpal.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private String zipCode;
  @Column
  private String favoriteArtist;
  @Column
  private String spotifyAuthToken;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Playlist> playlists;

  public User() {

  }

  public User(String username, String password, String zipCode, String favoriteArtist, String spotifyAuthToken) {
    this.username = username;
    this.password = password;
    this.zipCode = zipCode;
    this.favoriteArtist = favoriteArtist;
    this.spotifyAuthToken = spotifyAuthToken;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getFavoriteArtist() {
    return favoriteArtist;
  }

  public void setFavoriteArtist(String favoriteArtist) {
    this.favoriteArtist = favoriteArtist;
  }

  public String getSpotifyAuthToken() {
    return spotifyAuthToken;
  }

  public void setSpotifyAuthToken(String spotifyAuthToken) {
    this.spotifyAuthToken = spotifyAuthToken;
  }

  public List<Playlist> getPlaylists() {
    return playlists;
  }

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }

  public void ownsPlaylist(Playlist playlist) {
    this.playlists.add(playlist);
    if(playlist.getUser() != this) {
      playlist.setUser(this);
    }
  }

  public void deletePlaylist(Playlist playlist) {
    this.playlists.remove(playlist);
    if (playlist.getUser() == this) {
      playlist.setUser(null);
    }
  }
}
