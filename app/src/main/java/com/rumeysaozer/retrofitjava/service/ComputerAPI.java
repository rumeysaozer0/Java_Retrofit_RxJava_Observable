package com.rumeysaozer.retrofitjava.service;

import com.rumeysaozer.retrofitjava.model.Computer;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ComputerAPI {
    @GET("computer/random_computer?size=20")
    Observable<List<Computer>> getData();
}
