package com.android.segunfrancis.bloggerapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.segunfrancis.bloggerapp.R;
import com.android.segunfrancis.bloggerapp.model.Hero;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> {

    private static final String TAG = "HeroesAdapter";

    Context mContext;
    List<Hero> mHeroList;

    public HeroesAdapter(Context context, List<Hero> heroList) {
        mContext = context;
        mHeroList = heroList;
    }

    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new HeroesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesViewHolder holder, int position) {
        Hero hero = mHeroList.get(position);
        holder.mTextView.setText(hero.getName());
        Glide.with(mContext)
                .load(hero.getImageurl())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mHeroList.size();
    }

    class HeroesViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        private HeroesViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }
}
