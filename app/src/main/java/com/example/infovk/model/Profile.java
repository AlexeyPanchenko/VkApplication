package com.example.infovk.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Profile {


    private String first_name, last_name, bdate, university_name, mobile_phone, interests, photo_max_orig;
    private int relation, sex, id;
    private ArrayList<Relatives> relatives;
    private City city;
    private Counters counters;
    private boolean online;

    public Profile(){}

    public void setId(int id){
        this.id = id;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }
    public void setBdate(String date){
        this.bdate = date;
    }
    public void setRelation (int relation){
        this.relation = relation;
    }
    public void setRelatives(ArrayList<Relatives> relatives){
        this.relatives = relatives;
    }
    public void setCity(City city){
        this.city = city;
    }
    public void setUniversity_name(String university_name){
        this.university_name = university_name;
    }
    public void setMobile_phone(String mobile_phone){
        this.mobile_phone = mobile_phone;
    }
    public void setInterests(String interests){
        this.interests = interests;
    }
    public void setPhoto_max_orig(String photo_max_orig){
        this.photo_max_orig = photo_max_orig;
    }
    public void setSex (int sex){
        this.sex = sex;
    }
    public void setCounters (Counters counters){
        this.counters = counters;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }

    public Profile(ArrayList<Relatives> relatives, int id, String first_name, String last_name, String bdate, int relation, City city,
            String university_name, String mobile_phone, String interests, String photo_100, int sex, Counters counters, boolean online){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.bdate = bdate;
        this.relation = relation;
        this.relatives = relatives;
        this.city = city;
        this.university_name = university_name;
        this.mobile_phone = mobile_phone;
        this.interests = interests;
        this.photo_max_orig = photo_100;
        this.sex = sex;
        this.counters = counters;
        this.online = online;
    }

    public int getId(){
        return id;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getDate(){
        return bdate;
    }
    public int getRelation(){
        return relation;
    }
    public ArrayList<Relatives> getRelatives(){
        return relatives;
    }
    public City getCity(){
        return city;
    }
    public String getUniversity_name(){
        return university_name;
    }
    public String getMobile_phone(){
        return mobile_phone;
    }
    public String getInterests(){
        return interests;
    }
    public String getPhoto_max_orig(){
        return photo_max_orig;
    }
    public int getSex(){
        return sex;
    }
    public Counters getCounters(){
        return counters;
    }
    public boolean getOnline(){
        return online;
    }


}
