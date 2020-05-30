package com.example.introapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CMSPortalActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    int fragPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmsportal);

        //loading viewPager components and tabs in activity
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        //Toolbar initialisation
        Toolbar toolbar;
        toolbar = findViewById(R.id.cmsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //setting up of tabs in activity
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        fragPosition = position;

                        //specifying tabs for ViewPager2 to host in screen
                        if (position == 0){
                            tab.setText("WORLD");
                        }
                        else if (position == 1){
                            tab.setText("BUSINESS");
                        }
                        else if (position == 2){
                            tab.setText("TECHNOLOGY");
                        }
                        else if (position == 3){
                            tab.setText("SCIENCE");
                        }
                        else if (position == 4){
                            tab.setText("SPORTS");
                        }
                    }
                }).attach();
    }

    //inflating menu with option to Add Post in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cms, menu); //inflating menu to toolbar
        return true;
    }

    //when selection from menu in toolbar is made
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int position = viewPager.getCurrentItem(); //getting tab position (fragment number)

        //if Add Neww Post is selected from toolbar menu
        if (item.getItemId() == R.id.nav_add){
            //crating new intent to AddPostActivity
            Intent createPostIntent = new Intent(CMSPortalActivity.this, AddPostActivity.class);
            createPostIntent.putExtra("POSITION", position); //passing position in intent to store in db
            startActivity(createPostIntent);
        }
        return true;
    }

    private ViewPager2Adapter createCardAdapter() {
        //initialising ViewPager2 adapter to allow for tab selection of fragments
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        return adapter;
    }
}

