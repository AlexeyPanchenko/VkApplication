package com.example.infovk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/** Only for my profile, else NullPointException*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Counters {
    private int friends;

    public Counters(){}
    public Counters(int friends){
        this.friends = friends;
    }
    public int getFriends(){
        return friends;
    }
}
