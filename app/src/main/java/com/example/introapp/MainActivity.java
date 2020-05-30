package com.example.introapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

//This class refers to the Initial Activity launched when opening app
public class MainActivity extends AppCompatActivity {
    // Array of strings for our list
    String[] mainActArray = {"Portal","Settings","Help","About"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data source for our list (mainActArray)
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mainActArray); //containing TextView layout for our array elements

        ListView listView = (ListView) findViewById(R.id.mainAct_list); //ListView id being mainAct_list
        listView.setAdapter(adapter); //setting adapter created as data source for list

        // Item click listener for ListView which handles List on clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) { //if Portal is chosen from list
                    Intent portalIntent = new Intent(getApplicationContext(), CMSPortalActivity.class);
                    startActivity(portalIntent); //passing intent to class CMSPortalActivity and starting activity
                }
            }
        });

    }
}
