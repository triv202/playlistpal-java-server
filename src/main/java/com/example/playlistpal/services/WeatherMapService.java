package com.example.playlistpal.services;

import com.example.playlistpal.models.WeatherMap;
import com.example.playlistpal.repositories.WeatherMapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
public class WeatherMapService {
  @Autowired
  WeatherMapRepository weatherMapRepository;
  @GetMapping("api/weather")
  public Iterable<WeatherMap> findAllWeatherMaps(){
    return weatherMapRepository.findAll();
  }

  @GetMapping("api/weather/{weatherId}")
  public Optional<WeatherMap> findWeatherMapById(@PathVariable("weatherId") Integer id) {
    return weatherMapRepository.findById(id);
  }

  @GetMapping("api/weather/{main}/{desc}")
  public WeatherMap findWeatherMapByInputs(@PathVariable("main") String main,
                                           @PathVariable("desc") String description) {
    return weatherMapRepository.findByInputs(main, description);
  }

  @PostMapping("/api/weather")
  public WeatherMap createWeatherMap(@RequestBody WeatherMap weatherMap) {
    return weatherMapRepository.save(weatherMap);
  }

}
