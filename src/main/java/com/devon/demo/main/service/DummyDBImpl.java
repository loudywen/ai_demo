package com.devon.demo.main.service;

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

    public DummyDBImpl() {
        usersDB.put(1, "dilao1");
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
        if(pinDB.containsKey(userId)){
            if(pinDB.get(userId) == pin){
                isValid=true;
            }
        }
        return isValid;
    }
}
