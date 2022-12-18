package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class movie_description extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
   ArrayList<movie>list;
    String parent;
    String def = "nothing";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        parent = getIntent().getStringExtra("parent");

        recyclerView=findViewById(R.id.movierecycle);
        database = FirebaseDatabase.getInstance().getReference().child("/movieDesc");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        list=new ArrayList<>();

        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    movie Movie =dataSnapshot.getValue(movie.class);
                    if(parent.equalsIgnoreCase(Movie.getParent())) {
                        list.add(Movie);
                    }

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}