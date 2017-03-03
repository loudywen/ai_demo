package com.devon.demo.main.service;

import com.devon.demo.main.dao.UserRepository;
import com.devon.demo.main.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GetRoleDetailsAsyncTaskImpl implements TaskService {

  private static final Logger logger = LoggerFactory.getLogger(GetRoleDetailsAsyncTaskImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public User buildResponse(String userid) {

    return userRepository.findByUser(userid);

  }
}
