package com.example.loltierlist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PokeRestApi {

        @GET("pokemon")
        Call<PokeRespons> getListPokemon();
    }

