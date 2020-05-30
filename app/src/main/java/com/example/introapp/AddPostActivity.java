package com.example.introapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

//This is the activity concerning post creation
public class AddPostActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    int position;
    ImageButton imgBtn;
    String imageurl;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        Intent intent = getIntent(); //getting intent of activity calling AddPostActivity

        //extracting data from intent - to be used to input section (ex.World, Business,..) no. in db
        position = intent.getIntExtra("POSITION", 1);

        imgBtn = (ImageButton) findViewById(R.id.imageButton); //component used to choose image from gallery
        //text input components
        final EditText titleField = findViewById(R.id.editTitle);
        final EditText descriptionField = findViewById(R.id.editDescription);

        //assigning event listener should ImageButton be clicked (to input an image)
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });

        Button button = findViewById(R.id.buttonSubmit); //button component to submit post
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //event handler when clicking buttonSubmit
                if (imageurl == null) { //no image uploaded
                    Toast.makeText(AddPostActivity.this, "Upload an image first", Toast.LENGTH_LONG).show();

                } else {
                    //getting data from text components
                    final String title = titleField.getText().toString();
                    final String description = descriptionField.getText().toString();

                    //creating a new post of type Post (model class)
                    Post post = new Post();
                    post.title = title;
                    post.content = description;
                    post.section = position;
                    post.imageurl = imageurl; //from method onActivityResult()
                    post.save(); //saving instance of Post to database

                    //notifying the grid adapter hosting a grid view of posts that data has changed
                    gridAdapter.notifyDataSetChanged();
                    finish(); //finishing activity and returning to activity intent has been passed from
                }
            }
        });
    }

    private void getImage() {
        Intent intent = new Intent(); //creating a new instance of intent
        intent.setType("image/*"); //specific intent to open photos gallery
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        //intent which will trigger onActivityResult method
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    //method triggered when returning from image gallery to AddPostActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            //obtaining path for image (data) selected
            Uri imageUri = data.getData();

            imageurl = imageUri.toString(); //converting to string to store URI in database

            //try-catch block to catch errors when uploading image
            try {
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                //obtaining bitmap image from image' stream of data
                Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                //setting the ImageButton to our image uploaded - showing image chosen instead of default drawable
                imgBtn.setImageBitmap(imageBitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace(); //logging stack trace to identify why exception occurred
                Toast.makeText(AddPostActivity.this, "Error when uploading image!",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(AddPostActivity.this, "No image chosen",Toast.LENGTH_LONG).show();
        }
    }

}