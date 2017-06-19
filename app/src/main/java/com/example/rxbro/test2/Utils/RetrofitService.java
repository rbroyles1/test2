package com.example.rxbro.test2.Utils;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rxbro on 6/19/2017.
 */

public interface RetrofitService {
    @GET("api/?results")
    Call<RandomAPI> getRandomizer();
}
