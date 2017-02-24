package com.devon.demo.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diwenlao on 2/23/17.
 */
@Service
public class DummyDBImpl implements DummyDB {

    private Map<Integer, String> usersDB = new HashMap<>();
    private Map<String, Integer> pinDB = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(DummyDBImpl.class);

    public DummyDBImpl() {
        usersDB.put(1, "ADMIN");
        usersDB.put(2, "testUser");
        pinDB.put(usersDB.get(1), 1234);
        pinDB.put(usersDB.get(2), 5678);
    }

    @Override
    public boolean findUserID(String userId) {

        return (usersDB.containsValue(userId)) ? true : false;
    }

    @Override
    public boolean findPin(String userId, int pin) {
        boolean isValid = false;
        logger.debug("user id: {}", userId);
        logger.debug("get user id: {}", pinDB.get(userId));
        logger.debug("received pin: {}", pin);
        if (pinDB.containsKey(userId)) {

            if (pinDB.get(userId) == pin) {
                isValid = true;
            }

        }
        return isValid;
    }
}
