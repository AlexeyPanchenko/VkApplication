package com.example.infovk.model;

public class Relatives {
    private int id;
    private String names;
    private String type;


    public Relatives(int i, String n, String t){
        id = i;
        names = n;
        type = t;
    }

    public int getId(){
        return id;
    }

    public String getNames(){
        return names;
    }
    public String getType(){
        return type;
    }

}
