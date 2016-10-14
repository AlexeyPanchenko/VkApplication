package com.example.infovk;


import com.example.infovk.model.Friend;
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
        relatives.add(new Relatives(123, "Вася", "Дед"));
        relatives.add(new Relatives(1112, "Галя", "дочь"));
        String firstName = "Иван";
        String lastName = "Иванов";
        String date = "25.12.1989";
        int userRelation = 1;
        String city = "Владивосток";
        String education = "ВГУЭС";
        String phone = "+79242222222";
        String hobbies = "Гулять играть";
        String foto = "url.0";
        int sex = 1;

        Profile ptester = new Profile(relatives, firstName, lastName, date, userRelation, city, education, phone, hobbies, foto, sex);
        assertEquals(relatives, ptester.getRelatives());
        assertEquals(firstName, ptester.getFirstName());
        assertEquals(lastName, ptester.getLastName());
        assertEquals(date, ptester.getDate());
        assertEquals(userRelation, ptester.getRelation());
        assertEquals(city, ptester.getCity());
        assertEquals(education, ptester.getEducation());
        assertEquals(phone, ptester.getPhone());
        assertEquals(hobbies, ptester.getHobbies());
        assertEquals(foto, ptester.getPhoto());
        assertEquals(sex, ptester.getSex());

        String firstName1 = "Иван1";
        String lastName1 = "Иванов1";
        String date1 = "25.12.19189";
        int userRelation1 = 2;
        String city1 = "Владивост1ок";
        String education1 = "ВГУЭ1С";
        String phone1 = "+792422212222";
        String hobbies1 = "Гулят1ь играть";
        String foto1 = "url.1";
        int sex1 = 2;
        ArrayList<Relatives> relatives1 = new ArrayList<>();
        relatives.add(new Relatives(1231, "Вас1", "Дед1"));
        relatives.add(new Relatives(11121, "Галя1", "дочь1"));

        Profile ptester1 = new Profile(relatives1, firstName1, lastName1, date1, userRelation1, city1, education1, phone1, hobbies1, foto1, sex1);
        assertNotEquals(ptester1.getFirstName(), ptester.getFirstName());
        assertNotEquals(ptester1.getLastName(), ptester.getLastName());
        assertNotEquals(ptester1.getDate(), ptester.getDate());
        assertNotEquals(ptester1.getRelation(), ptester.getRelation());
        assertNotEquals(ptester1.getRelatives(), ptester.getRelatives());
        assertNotEquals(ptester1.getCity(), ptester.getCity());
        assertNotEquals(ptester1.getEducation(), ptester.getEducation());
        assertNotEquals(ptester1.getPhone(), ptester.getPhone());
        assertNotEquals(ptester1.getHobbies(), ptester.getHobbies());
        assertNotEquals(ptester1.getPhoto(), ptester.getPhoto());

        int friendId = 12345;
        int friendsCount = 90;
        boolean online = true;
        Friend friend = new Friend(friendId, friendsCount, online, ptester);

        assertEquals(friendId, friend.getID());
        assertEquals(friendsCount, friend.getFriendsCount());
        assertEquals(online, friend.getOnline());
        assertEquals(ptester, friend.getProfile());

        int friendId1 = 43245;
        int friendsCount1 = 902;
        boolean online1 = false;
        Friend friend1 = new Friend(friendId1, friendsCount1, online1, ptester1);

        assertNotEquals(friend.getID(), friend1.getID());
        assertNotEquals(friend.getFriendsCount(), friend1.getFriendsCount());
        assertNotEquals(friend.getOnline(), friend1.getOnline());
        assertNotEquals(friend.getProfile(), friend1.getProfile());

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend);
        friends.add(friend1);
        int id = 123456789;
        User tester = new User(id, friends, ptester);

        assertEquals(id, tester.getID());
        assertEquals(friends, tester.getFriends());
        assertEquals(ptester, tester.getProfile());

        ArrayList<Friend> friends1 = new ArrayList<>();
        friends.add(friend);


        int id1 = 123;
        User tester1 = new User(id1, friends1, ptester1);
        assertNotEquals(tester.getID(), tester1.getID());
        assertNotEquals(tester.getFriends(), tester1.getFriends());
        assertNotEquals(tester.getProfile(), tester1.getProfile());

    }


}