package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    Spinner loc;
    String location;
    private RecyclerView parent;
    private FirebaseViewModel firebaseViewModel;
    private ParentAdapterBottomSlider parentAdapterBottomSlider;
    ImageSlider topSlider;
    private ViewPager2 viewPager2;
    ImageView notBtn;
    ImageView profBtn;
    TextView txt;
    String temp;
    MiddleSliderAdapter middleSliderAdapter;
    ImageView MovBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Views on the layout
        loc = findViewById(R.id.loc);
        txt = findViewById(R.id.temp);

        //Location spinner functioning
        ArrayAdapter<CharSequence> adapter  = ArrayAdapter.createFromResource(this,R.array.locations,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loc.setAdapter(adapter);
        loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location = (String) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //top slider
        topSlider = findViewById(R.id.top_image_slider);
        final List<SlideModel> images = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("/Dashboard/TopSliders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren())
                {
                    images.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                }
                topSlider.setImageList(images,ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Middle Sliders
        viewPager2 = findViewById(R.id.MiddleSlider);
        FirebaseRecyclerOptions<MiddleModel> options = new FirebaseRecyclerOptions.Builder<MiddleModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Dashboard/MiddleSliders"),MiddleModel.class).build();
        middleSliderAdapter = new MiddleSliderAdapter(options);
        middleSliderAdapter.setViewPager2(viewPager2);
        viewPager2.setAdapter(middleSliderAdapter);









        //Bottom Sliders
        parent = findViewById(R.id.parent_bottom_slider);
        parent.setHasFixedSize(true);
        parent.setLayoutManager(new LinearLayoutManager(this));
        parentAdapterBottomSlider = new ParentAdapterBottomSlider();
        parentAdapterBottomSlider.setContext(getApplicationContext());
        parent.setAdapter(parentAdapterBottomSlider);


        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);
        firebaseViewModel.getAllData();
        firebaseViewModel.getParentItemMutableLiveData().observe(this, new Observer<List<ParentModelBottomSlider>>() {
            @Override
            public void onChanged(List<ParentModelBottomSlider> parentModelBottomSliders) {
                parentAdapterBottomSlider.setList(parentModelBottomSliders);
                parentAdapterBottomSlider.notifyDataSetChanged();

            }
        });
        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(this, new Observer<DatabaseError>() {
            @Override
            public void onChanged(DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this,"Error",Toast.LENGTH_SHORT);
            }
        });

        //Buttons for activity connectivity
        notBtn = findViewById(R.id.noti);
        notBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,NotificationActivity.class));
            }
        });

        profBtn = findViewById(R.id.profile);
        profBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,Profile.class));
            }
        });

        MovBtn = findViewById(R.id.MoviesBtn);
        MovBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,movie_description.class));
            }
        });

    }
}