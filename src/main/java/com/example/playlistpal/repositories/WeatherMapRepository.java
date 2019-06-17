package com.example.playlistpal.repositories;

import com.example.playlistpal.models.WeatherMap;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherMapRepository extends
        CrudRepository<WeatherMap, Integer> {
  @Query(value="SELECT weatherMap FROM WeatherMap weatherMap WHERE weatherMap.main LIKE :main% AND " +
          "weatherMap.description LIKE :desc%")
  public WeatherMap findByInputs(@Param("main") String main, @Param("desc") String desc);

}

