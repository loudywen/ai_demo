package com.devon.demo.main.dao;

import com.devon.demo.main.model.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Devon on 3/2/2017.
 */
public interface UserRepository extends CrudRepository<User, String> {

  User findByUser(String user);
}
