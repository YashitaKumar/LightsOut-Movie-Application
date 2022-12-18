package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotiMovieAdapter notiMovieAdapter;
    FirebaseAuth mAuth;
    String location;
    ImageView backBtn;
    TextView txt;
    String temp;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        backBtn = findViewById(R.id.bckBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, DashboardActivity.class));
            }
        });


        recyclerView = findViewById(R.id.Notification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth= FirebaseAuth.getInstance();

        FirebaseRecyclerOptions<NotiMovieModel> options = new FirebaseRecyclerOptions.Builder<NotiMovieModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("/Users/"+mAuth.getCurrentUser().getUid()+"/notifications"),NotiMovieModel.class).build();
        notiMovieAdapter = new NotiMovieAdapter(options);
        recyclerView.setAdapter(notiMovieAdapter);






    }
    @Override
    protected void onStart() {
        super.onStart();
        notiMovieAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notiMovieAdapter.stopListening();
    }
}