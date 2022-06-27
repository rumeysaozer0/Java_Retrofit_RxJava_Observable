package com.rumeysaozer.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rumeysaozer.retrofitjava.adapter.ComputerAdapter;
import com.rumeysaozer.retrofitjava.databinding.ActivityMainBinding;
import com.rumeysaozer.retrofitjava.model.Computer;
import com.rumeysaozer.retrofitjava.service.ComputerAPI;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Computer> computer;
    private String BASE_URL = "https://random-data-api.com/api/";
    Retrofit retrofit;
    CompositeDisposable disposable;
    ComputerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }
    private void loadData(){
        final ComputerAPI computerAPI = retrofit.create(ComputerAPI.class);
        disposable = new CompositeDisposable();
        disposable.add(computerAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        );
    }
    private void handleResponse(List<Computer> computerList){
        computer = new ArrayList<>(computerList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new ComputerAdapter(computer);
        binding.recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
