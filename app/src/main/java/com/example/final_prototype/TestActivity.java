package com.example.final_prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    Button logout;
    FirebaseAuth mFirebaseAuth;
    TextView mtextView;
    TextView mtextView1;
    private Firebase mRef;
    ImageView image1;
    ImageView image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        mtextView1 = findViewById(R.id.textViewMy);

        image1=findViewById(R.id.imagebutton1);
        image2=findViewById(R.id.imagebutton2);

        mtextView1 = findViewById(R.id.textView1);
        mtextView = findViewById(R.id.textView);


        mRef = new Firebase("https://iot-distance-sensor.firebaseio.com/message");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status1 = dataSnapshot.getValue(String.class);

                if(status1.equals("Seat Not Occupied")){
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                    mtextView1.setText(status1);
                }else if (status1.equals("Seat Occupied")){
                    image2.setVisibility(View.VISIBLE);
                    image1.setVisibility(View.INVISIBLE);
                    mtextView.setText(status1);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}