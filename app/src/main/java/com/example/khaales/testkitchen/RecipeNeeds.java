package com.example.khaales.testkitchen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeNeeds extends AppCompatActivity {
    private static final String TAG = "MyRecipes";
    private ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_needs);
        mylist = (ListView) findViewById(R.id.listView5);

        Intent recipeinfo = getIntent();
        Bundle data = getIntent().getExtras();
        final String value = data.getString("key");
        Log.d(TAG, "The value is: " + value);



        // reference database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/MyRecipes");


        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Map<String, ArrayList<String>> map = (Map<String, ArrayList<String>>) dataSnapshot.getValue();
                Log.d(TAG, "recipe items: " + map.get(value));

                final ArrayAdapter adapter = new ArrayAdapter(RecipeNeeds.this, android.R.layout.simple_list_item_1, map.get(value));
                mylist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            };

        });

    }
}
