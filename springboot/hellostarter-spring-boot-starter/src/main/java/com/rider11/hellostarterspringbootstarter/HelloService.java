package com.rider11.hellostarterspringbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    private HelloProperties properties;
    public String say() {
        return this.properties.getSayWhat() 
        + "！ " + this.properties.getToWho();
    }
}
