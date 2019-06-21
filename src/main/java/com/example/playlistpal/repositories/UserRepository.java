package com.example.playlistpal.repositories;

import com.example.playlistpal.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
