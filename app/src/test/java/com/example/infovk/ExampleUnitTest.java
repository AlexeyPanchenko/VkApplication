package com.example.infovk;


import com.example.infovk.model.City;
import com.example.infovk.model.Counters;
import com.example.infovk.model.Profile;
import com.example.infovk.model.Relatives;
import com.example.infovk.model.User;

import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {


    @Test
    public void createTest() throws Exception {
        ArrayList<Relatives> relatives = new ArrayList<>();
        relatives.add(new Relatives("Дед", 123, "Вася"));
        relatives.add(new Relatives("дочь", 1112, "Галя"));
        String firstName = "Иван";
        String lastName = "Иванов";
        String date = "25.12.1989";
        int id = 12214;
        int userRelation = 1;
        City city = new City(12, "dsf");
        String education = "ВГУЭС";
        String phone = "+79242222222";
        String hobbies = "Гулять играть";
        String foto = "url.0";
        int sex = 1;
        boolean online = true;
        Counters counters = new Counters(45);

        Profile ptester = new Profile(relatives, id, firstName, lastName, date, userRelation, city, education, phone, hobbies, foto, sex, counters, online);
        assertEquals(relatives, ptester.getRelatives());
        assertEquals(id, ptester.getId());
        assertEquals(firstName, ptester.getFirst_name());
        assertEquals(lastName, ptester.getLast_name());
        assertEquals(date, ptester.getDate());
        assertEquals(userRelation, ptester.getRelation());
        assertEquals(city, ptester.getCity());
        assertEquals(education, ptester.getUniversity_name());
        assertEquals(phone, ptester.getMobile_phone());
        assertEquals(hobbies, ptester.getInterests());
        assertEquals(foto, ptester.getPhoto_max_orig());
        assertEquals(sex, ptester.getSex());

        String firstName1 = "Иван1";
        String lastName1 = "Иванов1";
        String date1 = "25.12.19189";
        int userRelation1 = 2;
        int id1 = 12214;
        City city1 = new City(122, "ds23rf");
        String education1 = "ВГУЭ1С";
        String phone1 = "+792422212222";
        String hobbies1 = "Гулят1ь играть";
        String foto1 = "url.1";
        int sex1 = 2;
        ArrayList<Relatives> relatives1 = new ArrayList<>();
        relatives.add(new Relatives("Дед1", 1231, "Вас1"));
        relatives.add(new Relatives("дочь1", 11121, "Галя1"));
        boolean online1 = true;
        Counters counters1 = new Counters(25);

        Profile ptester1 = new Profile(relatives1, id1, firstName1, lastName1, date1, userRelation1, city1, education1, phone1, hobbies1, foto1, sex1, counters1, online1);
        assertNotEquals(ptester1.getFirst_name(), ptester.getFirst_name());
        assertNotEquals(ptester1.getLast_name(), ptester.getLast_name());
        assertNotEquals(ptester1.getDate(), ptester.getDate());
        assertNotEquals(ptester1.getRelation(), ptester.getRelation());
        assertNotEquals(ptester1.getRelatives(), ptester.getRelatives());
        assertNotEquals(ptester1.getCity(), ptester.getCity());
        assertNotEquals(ptester1.getUniversity_name(), ptester.getUniversity_name());
        assertNotEquals(ptester1.getMobile_phone(), ptester.getMobile_phone());
        assertNotEquals(ptester1.getInterests(), ptester.getInterests());
        assertNotEquals(ptester1.getPhoto_max_orig(), ptester.getPhoto_max_orig());


        ArrayList<Profile> friends = new ArrayList<>();
        friends.add(ptester);
        friends.add(ptester1);
        User tester = new User(friends, ptester);

        assertEquals(friends, tester.getFriends());
        assertEquals(ptester, tester.getProfile());

        ArrayList<Profile> friends1 = new ArrayList<>();
        friends.add(ptester1);


        User tester1 = new User(friends1, ptester1);
        assertNotEquals(tester.getFriends(), tester1.getFriends());
        assertNotEquals(tester.getProfile(), tester1.getProfile());

    }


}