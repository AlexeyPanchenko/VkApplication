package com.example.infovk.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleUser {
    private String first_name;
    private String last_name;
    private int id;
    public SimpleUser(){}
    public SimpleUser(int id ,String first_name, String last_name){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public int getId(){
        return id;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

}
