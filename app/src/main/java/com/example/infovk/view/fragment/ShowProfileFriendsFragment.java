package com.example.infovk.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infovk.R;
import com.example.infovk.model.Profile;
import com.example.infovk.presenter.MyFriendsProfilePresenter;
import com.example.infovk.view.MyFriendsProfilesView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShowProfileFriendsFragment extends MvpFragment<MyFriendsProfilesView, MyFriendsProfilePresenter>
        implements MyFriendsProfilesView {

    private static final String ARG_FRIEND_ID = "friends_id";

    private Profile profile;
    int friendId;
    private TextView textTitle, fullNameTitle, bDateTitle, sexTitle, cityTitle, relationTitle, universityNameTitle, phoneTitle
            , interestsTitle, relativesTitle;
    private TextView online, fullName, bDate, sex, city, relation, universityName, phone, interests;
    private ImageView photo;
    private TextView list;

    public static ShowProfileFriendsFragment newInstance(int id){
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_FRIEND_ID, id);
        ShowProfileFriendsFragment showProfileFragment = new ShowProfileFriendsFragment();
        showProfileFragment.setArguments(arguments);
        return showProfileFragment;
    }

    @Override
    public MyFriendsProfilePresenter createPresenter() {
        return new MyFriendsProfilePresenter();
    }

    @Override
    public void showProfilesFriends(ArrayList<Profile> friends) {
        for (int i = 0; i < friends.size(); i++) {
            if(friends.get(i).getId() == friendId){
                profile = friends.get(i);
                setWigets(profile);
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendId = getArguments().getInt(ARG_FRIEND_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_profile, container, false);

        list = (TextView) view.findViewById(R.id.list_profile);

        textTitle = (TextView) view.findViewById(R.id.text_profile_title);
        online = (TextView) view.findViewById(R.id.text_profile_online);
        fullNameTitle = (TextView) view.findViewById(R.id.text_profile_fullName_title);
        bDateTitle = (TextView) view.findViewById(R.id.text_profile_bDate_title);
        sexTitle = (TextView) view.findViewById(R.id.text_profile_sex_title);
        cityTitle = (TextView) view.findViewById(R.id.text_profile_city_title);
        relationTitle = (TextView) view.findViewById(R.id.text_profile_relation_title);
        universityNameTitle = (TextView) view.findViewById(R.id.text_profile_university_title);
        phoneTitle = (TextView) view.findViewById(R.id.text_profile_phone_title);
        interestsTitle = (TextView) view.findViewById(R.id.text_profile_interests_title);
        relativesTitle = (TextView) view.findViewById(R.id.text_profile_relatives_title);

        fullName = (TextView) view.findViewById(R.id.text_profile_fullName);
        bDate = (TextView) view.findViewById(R.id.text_profile_bDate);
        sex = (TextView) view.findViewById(R.id.text_profile_sex);
        city = (TextView) view.findViewById(R.id.text_profile_city);
        relation = (TextView) view.findViewById(R.id.text_profile_relation);
        universityName = (TextView) view.findViewById(R.id.text_profile_university);
        phone = (TextView) view.findViewById(R.id.text_profile_phone);
        interests = (TextView) view.findViewById(R.id.text_profile_interests);
        photo = (ImageView) view.findViewById(R.id.profile_photo);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.showMyFriends();
    }

    public void setWigets(Profile profile){

        textTitle.setText(profile.getFirst_name());

        fullNameTitle.setText(R.string.text_profile_fullName_title);
        bDateTitle.setText(R.string.text_profile_bDate_title);
        sexTitle.setText(R.string.text_profile_sex_title);
        cityTitle.setText(R.string.text_profile_city_title);
        relationTitle.setText(R.string.text_profile_relation_title);
        universityNameTitle.setText(R.string.text_profile_university_title);
        phoneTitle.setText(R.string.text_profile_phone_title);
        interestsTitle.setText(R.string.text_profile_interests_title);
        relativesTitle.setText(R.string.text_profile_relatives_title);

        Picasso.with(getContext()).load(profile.getPhoto_max_orig()).into(photo);
        fullName.setText(profile.getFirst_name() + " " + profile.getLast_name());
        bDate.setText(profile.getDate());
        switch (profile.getSex()){
            case 0:
                sex.setText(R.string.unknown);
                break;
            case 1:
                sex.setText(R.string.sex_female);
                break;
            case 2:
                sex.setText(R.string.sex_male);
                break;
        }
        if((profile.getCity() != null)){
            city.setText(profile.getCity().getCityName());
        }
        int condition = profile.getSex();
        switch (profile.getRelation()){
            case 0:
                relation.setText(R.string.unknown);
                break;
            case 1:
                if(condition == 1){
                    relation.setText(R.string.relation_noMarried_f);
                }else {relation.setText(R.string.relation_noMarried_m);}
                break;
            case 2:
                if(condition == 1){
                    relation.setText(R.string.relation_isFriend_f);
                }else {relation.setText(R.string.relation_isFriend_m);}
                break;
            case 3:
                if(condition == 1){
                    relation.setText(R.string.relation_engagement_f);
                }else {relation.setText(R.string.relation_engagement_m);}
                break;
            case 4:
                if(condition == 1){
                    relation.setText(R.string.relation_married_f);
                }else {relation.setText(R.string.relation_married_m);}
                break;
            case 5:
                relation.setText(R.string.relation_hard);
                break;
            case 6:
                relation.setText(R.string.relation_activeSearch);
                break;
            case 7:
                if(condition == 1){
                    relation.setText(R.string.relation_love_f);
                }else {relation.setText(R.string.relation_love_m);}
                break;
        }
        universityName.setText(profile.getUniversity_name());
        phone.setText(profile.getMobile_phone());
        if(profile.getOnline()){
            online.setText(R.string.text_profile_online);
        }else{online.setText(R.string.text_profile_offline);}
        interests.setText(profile.getInterests());
        ArrayList<String> relatives = new ArrayList<>();
        String textRelatives = "";
        if(profile.getRelatives() != null){
            for (int i = 0; i < profile.getRelatives().size(); i++) {
                String name = profile.getRelatives().get(i).getName();
                String type = profile.getRelatives().get(i).getType();
                if (name == null) {
                    name = String.valueOf(profile.getRelatives().get(i).getId());
                }
                if (i == profile.getRelatives().size() - 1) {
                    relatives.add(type + ": " + name + ".");
                } else {
                    relatives.add(type + ": " + name + ",  ");
                }
                textRelatives += relatives.get(i);
            }
            list.setText(textRelatives);
        }else {return;}
    }


}
