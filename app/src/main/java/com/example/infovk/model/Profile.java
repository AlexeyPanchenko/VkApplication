package com.example.infovk.model;


import java.util.ArrayList;

public class Profile {

    private String firstName,lastName,date,city,education,phone,hobbies,photo;
    private int relation, sex;
    private ArrayList<Relatives> relatives;


    public Profile(){

    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setRelation (int relation){
        this.relation = relation;
    }
    public void setRelatives(ArrayList<Relatives> relatives){
        this.relatives = relatives;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setEducation(String education){
        this.education = education;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setHobbies(String hobbies){
        this.hobbies = hobbies;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setSex (int sex){
        this.sex = sex;
    }

    public Profile(ArrayList<Relatives> relatives, String firstName, String lastName, String userDate, int userRelation, String userCity,
            String userEducation, String userPhone, String userHobbies, String userPhoto, int userSex){
        this.firstName = firstName;
        this.lastName = lastName;
        date = userDate;
        relation = userRelation;
        this.relatives = relatives;
        city = userCity;
        education = userEducation;
        phone = userPhone;
        hobbies = userHobbies;
        photo = userPhoto;
        sex = userSex;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getDate(){
        return date;
    }
    public int getRelation(){
        return relation;
    }
    public ArrayList<Relatives> getRelatives(){
        return relatives;
    }
    public String getCity(){
        return city;
    }
    public String getEducation(){
        return education;
    }
    public String getPhone(){
        return phone;
    }
    public String getHobbies(){
        return hobbies;
    }
    public String getPhoto(){
        return photo;
    }
    public int getSex(){
        return sex;
    }

}
