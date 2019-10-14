package com.android.segunfrancis.bloggerapp.viewModel;

import com.android.segunfrancis.bloggerapp.data.Api;
import com.android.segunfrancis.bloggerapp.model.Hero;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroViewModel extends ViewModel {

    // this is the data we will fetch asynchronously
    private MutableLiveData<List<Hero>> heroList;

    // call this method to get the data
    public LiveData<List<Hero>> getHeroes() {
        if (heroList == null) {
            heroList = new MutableLiveData<List<Hero>>();
            // Load asynchronously from the server
            loadHeroes();
        }
        return heroList;
    }

    // Use Retrofit to get JSON data from URL
    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }
}
