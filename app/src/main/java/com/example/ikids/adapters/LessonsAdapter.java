package com.example.ikids.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ikids.R;
import com.example.ikids.models.Lessons;

import java.util.ArrayList;

public class LessonsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Lessons> lessonsArrayList;
    int LEFT = 0;
    int RIGHT = 1;
    public LessonsAdapter(Context context, ArrayList<Lessons> lessonsArrayList) {
        this.context = context;
        this.lessonsArrayList = lessonsArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LEFT) {
            return new LeftViewHolder(LayoutInflater.from(context).inflate(R.layout.row_levels_left, parent, false));
        } else{
            return new RightViewHolder(LayoutInflater.from(context).inflate(R.layout.row_levels_right, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Lessons lessons = lessonsArrayList.get(position);
        if (holder.getItemViewType() == LEFT) {
            LeftViewHolder leftViewHolder = (LeftViewHolder) holder;
            if (lessons.getOpen()) {
                leftViewHolder.imageNotLocked.setVisibility(View.VISIBLE);
                leftViewHolder.imageLocked.setVisibility(View.GONE);
            } else {
                leftViewHolder.imageNotLocked.setVisibility(View.GONE);
                leftViewHolder.imageLocked.setVisibility(View.VISIBLE);
            }
        } else  {
            RightViewHolder rightViewHolder = (RightViewHolder) holder;
            if (lessons.getOpen()) {
                rightViewHolder.imageNotLocked.setVisibility(View.VISIBLE);
                rightViewHolder.imageLocked.setVisibility(View.GONE);
            } else {
                rightViewHolder.imageNotLocked.setVisibility(View.GONE);
                rightViewHolder.imageLocked.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return LEFT;
        } else {
            return RIGHT;
        }
    }


    @Override
    public int getItemCount() {
        return lessonsArrayList.size();
    }
    static class LeftViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNotLocked ,imageLocked;
        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNotLocked = itemView.findViewById(R.id.imageNotLock);
            imageLocked= itemView.findViewById(R.id.imageLocked);
        }
    }

    static class RightViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNotLocked ,imageLocked;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNotLocked = itemView.findViewById(R.id.imageNotLock);
            imageLocked= itemView.findViewById(R.id.imageLocked);
        }
    }

}
