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


public class HomeActivity extends AppCompatActivity {

        Button logout;
        FirebaseAuth mFirebaseAuth;
        TextView mtextView;
        TextView mtextView1;
        private Firebase mRef;
        ImageView image1;
        ImageView image2;
        private TextView seat;
        private TextView chat;
        private TextView help;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mtextView1 = findViewById(R.id.textView1);
        logout = findViewById(R.id.logout);
        image1 = findViewById(R.id.imagebutton1);
        image2 = findViewById(R.id.imagebutton2);

        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);

        seat = findViewById(R.id.seatID);
        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeat();
            }

            public void openSeat(){
                Intent intent = new Intent(HomeActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        chat = findViewById(R.id.chatId);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChat();
            }

            public void openChat(){
                Intent intent = new Intent(HomeActivity.this, chatActivity.class);
                startActivity(intent);
            }
        });

        help = findViewById(R.id.helpsId);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelp();
            }

            public void openHelp(){
                Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        mRef = new Firebase("https://iot-distance-sensor.firebaseio.com/message");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status1 = dataSnapshot.getValue(String.class);

                if(status1.equals("Assistance Needed")){
                    mtextView1.setText("Seat 1 Need Assistance");
//                    mtextView1.setTextColor(Color.GREEN);
//                    image1.setVisibility(View.VISIBLE);
//                    image2.setVisibility(View.INVISIBLE);

                }  else if(status1.equals("Seat Not Occupied But Button Pressed")){
                    mtextView1.setText(status1);

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });



    }
}
