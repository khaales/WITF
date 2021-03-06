package com.example.khaales.testkitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;

public class MyFood extends AppCompatActivity {
    private static final String TAG = "MyFood";
    private ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_food);
        mylist = (ListView) findViewById(R.id.listView2);

        Intent intent = getIntent();
        String message = "hello";


        // reference database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/MyFood");


        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) dataSnapshot.getValue();
                final List<String> keys = new ArrayList<String>();
                final List<String> presentfoods = new ArrayList<String>();
                final List<String> neededfoods = new ArrayList<String>();
                for (String key : map.keySet()) {
                    //Log.d("HELLO HERE ", key);
                    keys.add(key);
                    //Log.d(TAG, "present value: " + map.get(key).get("Present"));

                    if (map.get(key).get("Present").equals(1)) {
                        //Log.d(TAG, "present value: " + map.get(key).get("Present"));
                        presentfoods.add(key);
                    } else {
                        neededfoods.add(key);
                    }
                    //Log.d(TAG, "foods: " + presentfoods);

                }
                Log.d(TAG, "presentfoods: " + presentfoods);
                Log.d(TAG, "neededfoods: " + neededfoods);

                String key1 = keys.get(0);
                String PB = map.get(key1).get("Present");
                Log.d(TAG, "PB map: " + PB);
                //Log.d(TAG, keys.get(0));

                //String x = map.get("Peanut Butter").get("Present");
                //Log.d(TAG, "map: " + map);
                //Log.d(TAG, x);

                final ArrayAdapter adapter = new ArrayAdapter(MyFood.this, android.R.layout.simple_list_item_1, presentfoods);
                mylist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

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

            ;
        });
    }


}
