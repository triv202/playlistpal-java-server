package com.example.playlistpal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherMap {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String main;
  @Column
  private String description;
  @Column
  private String message;
  @Column
  private Float valence;
  @Column
  private Float energy;
  @Column
  private Float danceability;

  public WeatherMap() {

  }

  public WeatherMap(String main, String description, String message, Float valence, Float energy, Float danceability) {
    this.main = main;
    this.description = description;
    this.message = message;
    this.valence = valence;
    this.energy = energy;
    this.danceability = danceability;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Float getValence() {
    return valence;
  }

  public void setValence(Float valence) {
    this.valence = valence;
  }

  public Float getEnergy() {
    return energy;
  }

  public void setEnergy(Float energy) {
    this.energy = energy;
  }

  public Float getDanceability() {
    return danceability;
  }

  public void setDanceability(Float danceability) {
    this.danceability = danceability;
  }
}
