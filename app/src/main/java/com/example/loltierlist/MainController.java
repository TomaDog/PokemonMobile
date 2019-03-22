package com.example.loltierlist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private final MainActivity mainActivity;

    private static MainController instance = null;

    //Exemple Singleton
    public static MainController getInstance(MainActivity mainActivity){
        if(instance == null){
            instance = new MainController(mainActivity, MainActivity.class);
        }
        return instance;

    }
    public MainController(MainActivity mainActivity, Class<MainActivity> mainActivityClass) {
        this.mainActivity = mainActivity;
    }

    public void onCreate() {
        mainActivity.showLoader();



        //La crÃ©ation de ces objets, ce serait bien de ne pas
        // les rÃ©instancier plusieurs fois.
        //--> Voir le cours de GÃ©nie Logiciel : Singleton()
        //Pour ceux qui veulent aller plus loin -> Injection de DÃ©pendances
        //On crÃ©e un objet Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crÃ©e un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crÃ©e notre interface PokemonRestApi
        PokeRestApi pokemonRestApi = retrofit.create(PokeRestApi.class);

        //On rÃ©cupÃ©re un objet call.
        Call<PokeRespons> call = pokemonRestApi.getListPokemon();

        call.enqueue(new Callback<PokeRespons>() {
            @Override
            public void onResponse(Call<PokeRespons> call, Response<PokeRespons> response) {
                PokeRespons restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                MainActivity.showList(listPokemon);
                MainActivity.hideLoader();
            }

            @Override
            public void onFailure(Call<PokeRespons> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
            }
        });
    }

    public void onItemClicked(Pokemon itemClicked){

    }
}
