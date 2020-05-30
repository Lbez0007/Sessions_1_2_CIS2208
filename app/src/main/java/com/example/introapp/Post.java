package com.example.introapp;

import android.net.Uri;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Posts")

//This is the model class used to be mapped as a table in our database
public class Post extends Model {

    //Attributes in our table
    @Column(name = "Title")
    public String title;

    @Column(name = "Content")
    public String content;

    @Column(name = "Section")
    public int section;

    @Column(name = "ImageUrl")
    public String imageurl;

    //Default constructor
    public Post() {
        super();
    }

    //Model's constructor
    public Post(String title, String content, int section, String imageurl) {
        super();
        this.title = title;
        this.content = content;
        this.section = section;
        this.imageurl = imageurl;
    }

    //Getter method for post's title
    public String getTitle()
    {
        return title;
    }

    //Getter method for post's image as a URI
    public Uri getImage()
    {
        Uri image = Uri.parse(imageurl); //parsing from db's string column to a URI
        return image;
    }


}