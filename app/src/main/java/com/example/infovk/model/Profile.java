package com.example.infovk.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Profile {

    @JsonView
    private String first_name, last_name, bdate, university_name, mobile_phone, interests, photo_100;
    private int relation, sex;
    private ArrayList<Relatives> relatives;
    private City city;

    public Profile(){}

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }
    public void setDate(String date){
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
    public void setPhoto_100(String photo_100){
        this.photo_100 = photo_100;
    }
    public void setSex (int sex){
        this.sex = sex;
    }

    public Profile(ArrayList<Relatives> relatives, String first_name, String last_name, String bdate, int relation, City city,
            String university_name, String mobile_phone, String interests, String photo_100, int sex){
        this.first_name = first_name;
        this.last_name = last_name;
        this.bdate = bdate;
        this.relation = relation;
        this.relatives = relatives;
        this.city = city;
        this.university_name = university_name;
        this.mobile_phone = mobile_phone;
        this.interests = interests;
        this.photo_100 = photo_100;
        this.sex = sex;
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
    public String getPhoto_100(){
        return photo_100;
    }
    public int getSex(){
        return sex;
    }

}
