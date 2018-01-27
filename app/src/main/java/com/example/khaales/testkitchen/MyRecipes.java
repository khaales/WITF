package com.example.khaales.testkitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;
import static com.example.khaales.testkitchen.R.id.parent;

public class MyRecipes extends AppCompatActivity {
    private static final String TAG = "MyRecipes";
    private ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        mylist = (ListView) findViewById(R.id.listView4);

        Intent intent = getIntent();

        // reference database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/MyRecipes");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) dataSnapshot.getValue();
                final List<String> keys = new ArrayList<String>();
                final List<String> recipes = new ArrayList<String>();
                for (String key : map.keySet()) {
                    keys.add(key);
                }
                Log.d(TAG, "recipes: " + keys);

                final ArrayAdapter adapter = new ArrayAdapter(MyRecipes.this, android.R.layout.simple_list_item_1, keys);
                mylist.setAdapter(adapter);

                // ListView on item selected listener.
                mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view,
                                            int position, long arg) {
                        String selected = (String) mylist.getItemAtPosition(position);
                        //Log.d(TAG, "the selected thing is: " + selected);
                        Intent recipeinfo = new Intent(MyRecipes.this, RecipeNeeds.class);
                        recipeinfo.putExtra("key", selected);
                        startActivity(recipeinfo);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
/*
            public void onDataChange() {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("testing-b89c3");

                myRef.setValue("Hello, World!");
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
            */

            ;
        });
    }
}
