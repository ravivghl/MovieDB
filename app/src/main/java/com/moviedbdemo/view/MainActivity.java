package com.moviedbdemo.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moviedbdemo.R;
import com.moviedbdemo.adapter.MovieAdapter;
import com.moviedbdemo.databinding.ActivityMainBinding;
import com.moviedbdemo.model.CommonResponse;
import com.moviedbdemo.model.Configuration;
import com.moviedbdemo.model.Images;
import com.moviedbdemo.model.MovieModel;
import com.moviedbdemo.utils.Const;
import com.moviedbdemo.webservice.RestCallBack;
import com.moviedbdemo.webservice.WSException;
import com.moviedbdemo.webservice.WebService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Images images;
    private ArrayList<MovieModel> arrayList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getAPIConfiguration();
    }

    private void getAPIConfiguration() {
        binding.progressBar.setVisibility(View.VISIBLE);
        WebService.getInstance().getConfiguration(new RestCallBack<Configuration>() {
            @Override
            public void onResponse(Configuration response) {
                images = response.getImages();
                getListOfMovies();
            }

            @Override
            public void onFailure(WSException wse) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getListOfMovies() {
        WebService.getInstance().getListOfMovies(Const.ASCENDING, new RestCallBack<CommonResponse>() {
            @Override
            public void onResponse(CommonResponse response) {
                binding.progressBar.setVisibility(View.GONE);
                arrayList = response.getResults();
                setUpRecyclerView();
            }

            @Override
            public void onFailure(WSException wse) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setUpRecyclerView() {
        binding.recyclerViewMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        binding.recyclerViewMovie.setAdapter(new MovieAdapter(MainActivity.this, arrayList, images));
    }
}