package com.android.segunfrancis.bloggerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.segunfrancis.bloggerapp.R;
import com.android.segunfrancis.bloggerapp.adapter.HeroesAdapter;
import com.android.segunfrancis.bloggerapp.model.Hero;
import com.android.segunfrancis.bloggerapp.viewModel.HeroViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    HeroesAdapter mAdapter;
    //List<Hero> mHeroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeroViewModel model = ViewModelProviders.of(this).get(HeroViewModel.class);
        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                mAdapter = new HeroesAdapter(MainActivity.this, heroes);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
