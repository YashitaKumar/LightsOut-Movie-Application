package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Collections;
import java.util.List;

public class MiddleSliderAdapter extends FirebaseRecyclerAdapter<MiddleModel,MiddleSliderAdapter.MyViewHolder> {

    public void setViewPager2(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
    }

    private ViewPager2 viewPager2;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MiddleSliderAdapter(@NonNull FirebaseRecyclerOptions<MiddleModel> options) {
        super(options);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull MiddleModel model) {
        Glide.with(holder.middle.getContext()).load(model.getPoster()).into(holder.middle);

    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView middle;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            middle = itemView.findViewById(R.id.MiddleSlide);
        }
    }
}
