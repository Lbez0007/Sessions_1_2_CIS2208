package com.example.introapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

//Class hosting ALL fragments found within the activity CMSPortalActivity
public class CMSTabFragment extends Fragment {

    private static final String ARG_COUNT = "param1";
    private Integer counter;

    //color map for fragments' background
    private int COLOR_MAP = R.color.colorOnPrimary;

    public CMSTabFragment() {
    }

    //creating a new instance of fragment
    public static CMSTabFragment newInstance(Integer counter) {
        CMSTabFragment fragment = new CMSTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    //responsible for creating view of fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cmstab, container, false);
    }

    //method fired after fragment's view is set up
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setting background colour to that specified in color map
        view.setBackgroundColor(ContextCompat.getColor(getContext(), COLOR_MAP));
        int fragNo = counter;
        //loading of grid view component in the respective fragment
        GridView gridList = view.findViewById(R.id.cmsGridView);

        //getting posts from database for the particular fragment section (ex. World)
        List<Post> cmsList = getPosts(fragNo);
        ArrayList<Post> cmsArrayList = ((ArrayList<Post>)cmsList);

        //passing array list of posts to Grid Adapter in order for posts to be hosted in a grid view
        GridAdapter gridAdapter = new GridAdapter(getActivity(), R.layout.activity_gridview, cmsArrayList);
        gridList.setAdapter(gridAdapter); //setting the grid view's input to the gridAdapter initialised

    }

    //returning all posts from DB using ActiveAndroid
    public static List<Post> getPosts(int section) {
            return new Select()
                    .from(Post.class)
                    //filtering returns where section of post is the equivalent of current fragment position
                    .where("Section = ?", section)
                    .orderBy("Title ASC")
                    .execute();
    }
}