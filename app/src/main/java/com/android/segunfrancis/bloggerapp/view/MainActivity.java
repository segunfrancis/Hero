package com.android.segunfrancis.bloggerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.segunfrancis.bloggerapp.R;
import com.android.segunfrancis.bloggerapp.adapter.HeroesAdapter;
import com.android.segunfrancis.bloggerapp.model.Hero;
import com.android.segunfrancis.bloggerapp.viewModel.HeroViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    HeroesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ShimmerFrameLayout shimmer1 = findViewById(R.id.shimmer1);
        final ShimmerFrameLayout shimmer2 = findViewById(R.id.shimmer2);
        final ShimmerFrameLayout shimmer3 = findViewById(R.id.shimmer3);

        shimmer1.startShimmer();
        shimmer2.startShimmer();
        shimmer3.startShimmer();

        HeroViewModel model = ViewModelProviders.of(this).get(HeroViewModel.class);
        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                mAdapter = new HeroesAdapter(MainActivity.this, heroes);
                mRecyclerView.setAdapter(mAdapter);

                shimmer1.stopShimmer();
                shimmer2.stopShimmer();
                shimmer3.stopShimmer();

                shimmer1.setVisibility(View.GONE);
                shimmer2.setVisibility(View.GONE);
                shimmer3.setVisibility(View.GONE);
            }
        });
    }
}
