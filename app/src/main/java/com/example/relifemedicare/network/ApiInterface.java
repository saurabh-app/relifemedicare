package com.example.relifemedicare.network;

import com.example.relifemedicare.model.MasterResponceMadel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/?key=24883422-68924c694df0d16d557d4da33")
    Call<MasterResponceMadel> getAlllists(@Query("q")String q,@Query("image_type") String image_type, @Query("pretty") String pretty);
}
