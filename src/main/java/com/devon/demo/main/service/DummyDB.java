package com.devon.demo.main.service;

/**
 * Created by diwenlao on 2/23/17.
 */
public interface DummyDB {

    boolean findUserID(String userId);
    boolean findPin(String userId, int pin);
}
