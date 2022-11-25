package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class MiddleSliderAdapter extends RecyclerView.Adapter<MiddleSliderAdapter.MyViewHolder>{
    private List<MiddleModel> lists;
    private ViewPager2 viewPager2;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            lists.addAll(lists);
            notifyDataSetChanged();
        }
    };

    public MiddleSliderAdapter(List<MiddleModel> lists, ViewPager2 viewPager2) {
        this.lists = lists;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MiddleModel itemss = lists.get(position);
        holder.setImage(itemss);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView middle;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            middle = itemView.findViewById(R.id.MiddleSlide);
        }
         void setImage(MiddleModel sliderItems){
//use glide or picasso in case you get image from internet
             middle.setImageResource(sliderItems.getPoster());
         }
    }
}
