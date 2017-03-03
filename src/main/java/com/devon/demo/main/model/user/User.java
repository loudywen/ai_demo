package com.devon.demo.main.model.user;

import java.io.Serializable;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
/**
 * Created by Devon on 3/2/2017.
 */
@Table
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @PrimaryKey
  private final String user;
  private final int pin;

  public User(String user, int pin) {
    this.user = user;
    this.pin = pin;
  }



  public String getUser() {
    return user;
  }

  public int getPin() {
    return pin;
  }

  @Override
  public String toString() {
    return "User{" +
        "user='" + user + '\'' +
        ", pin=" + pin +
        '}';
  }
}
