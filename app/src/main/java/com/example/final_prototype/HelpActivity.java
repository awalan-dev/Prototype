package com.example.final_prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class HelpActivity extends AppCompatActivity {

    TextView help;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        help = findViewById(R.id.helpId);
        mRef = new Firebase("https://iot-distance-sensor.firebaseio.com/message");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status1 = dataSnapshot.getValue(String.class);

                if(status1.equals("Assistance Needed")){
                    help.setText("Seat 1 Need Assistance");
//                    mtextView1.setTextColor(Color.GREEN);
//                    image1.setVisibility(View.VISIBLE);
//                    image2.setVisibility(View.INVISIBLE);

                }  else if(status1.equals("Seat Not Occupied But Button Pressed")){
                    help.setText(status1);

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
}
