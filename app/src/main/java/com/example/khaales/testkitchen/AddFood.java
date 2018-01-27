package com.example.khaales.testkitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {

    private Button add_fridge;
    private Button add_need;
    private TextView header;
    private EditText name;
    private EditText expire_date;
    private EditText quantity;

    // reference database
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference food_list = database.getReference("/MyFood");

    DatabaseReference need_list = database.getReference("/MyNeed");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        add_fridge = (Button)findViewById(R.id.add_fridge);
        add_need = (Button)findViewById(R.id.add_need);

        header = (TextView) findViewById(R.id.header);
        name =  (EditText) findViewById(R.id.name);
        expire_date = (EditText) findViewById(R.id.expire_date);
        quantity = (EditText) findViewById(R.id.quantity);


        add_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food item = new  Food(expire_date.getText().toString(), quantity.getText().toString(),1);
                food_list.child(name.getText().toString()).setValue(item);

            }
        });

        add_fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food item = new  Food(expire_date.getText().toString(), quantity.getText().toString(),0);
                need_list.child(name.getText().toString()).setValue(item);

            }
        });
    }
}
