package com.example.playlistpal.services;

import com.example.playlistpal.models.User;
import com.example.playlistpal.models.Playlist;
import com.example.playlistpal.repositories.UserRepository;
import com.example.playlistpal.repositories.PlaylistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins="*", allowCredentials = "true")
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  PlaylistRepository playlistRepository;

  @GetMapping("api/users")
  public Iterable<User> findAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/api/profile")
  public User profile(HttpSession session) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    return currentUser;
  }

  @GetMapping("api/profile/playlists")
  public List<Playlist> getPlaylists(HttpSession session) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    return currentUser.getPlaylists();
  }

  @PostMapping("api/profile/playlists")
  public User addPlaylist(HttpSession session, @RequestBody Playlist pl) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    Optional<Playlist> playlistOptional = playlistRepository.findById(pl.getId());
    if(playlistOptional.isPresent()) {
      Playlist playlist = playlistOptional.get();
      currentUser.ownsPlaylist(playlist);
      session.setAttribute("currentUser", currentUser);
      return userRepository.save(currentUser);
    }
    return currentUser;

  }

  @PostMapping("api/profile/playlists/delete")
  public User deletePlaylist(HttpSession session, @RequestBody Playlist pl) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    Optional<Playlist> playlistOptional = playlistRepository.findById(pl.getId());
    if(playlistOptional.isPresent()) {
      Playlist playlist = playlistOptional.get();
      System.out.println("User:" + currentUser);
      System.out.println("Playlist:" + playlist);
      System.out.println("Session:" + session);
      currentUser.deletePlaylist(playlist);
      session.setAttribute("currentUser", currentUser);
      return userRepository.save(currentUser);
    }
    System.out.println("Didn't find playlist");
    return currentUser;
  }

  @PostMapping("/api/profile/{token}")
  public User setToken(HttpSession session,
                       @PathVariable("token") String token) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    currentUser.setSpotifyAuthToken(token);
    session.setAttribute("currentUser", currentUser);
    return userRepository.save(currentUser);
  }

  @PostMapping("/api/profile")
  public User updateUser(HttpSession session,
                       @RequestBody User user) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    System.out.print(currentUser);
    if (user.getZipCode() != null) {
      currentUser.setZipCode(user.getZipCode());
    }
    if(user.getFavoriteArtist() != null) {
      currentUser.setFavoriteArtist(user.getFavoriteArtist());
    }
    if(user.getPassword() != null) {
      currentUser.setUsername(user.getPassword());
    }
    session.setAttribute("currentUser", currentUser);
    return userRepository.save(currentUser);
  }

  @PostMapping("/api/register")
  public User register(@RequestBody User user,
                       HttpSession session) {
    System.out.print(user);
    session.setAttribute("currentUser", user);
    return userRepository.save(user);
  }

  @PostMapping("/api/login")
  public User login(@RequestBody User credentials,
                      HttpSession session) {
    Iterable<User> users = userRepository.findAll();
    for (User user : users) {
      if( user.getUsername().equals(credentials.getUsername())
              && user.getPassword().equals(credentials.getPassword())) {

        session.setAttribute("currentUser", user);
        return user;
      }
    }
    System.out.print("Unsuccessful login");
    return null;
  }

  @PostMapping("/api/logout")
  public void logout
          (HttpSession session) {
    session.invalidate();
  }

}
