package com.example.introapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//Adapter to define data displayed within each fragment's GridView
public class GridAdapter extends ArrayAdapter {

    ArrayList<Post> cmsArrayList;

    public GridAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        cmsArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.activity_gridview, null);

        //initialising textview and image for every post to be shown in grid
        TextView textView = v.findViewById(R.id.textView);
        ImageView imageView = v.findViewById(R.id.imgViewGrid);

        //showing title and image of each post in grid
        textView.setText(cmsArrayList.get(position).title);
        //setImageURI uses the uri of image (parsed from the string in db in the Model Class Post),
        // and outputs the image from the specified URI in the imageView
        imageView.setImageURI(cmsArrayList.get(position).getImage());
        return v;

    }
}