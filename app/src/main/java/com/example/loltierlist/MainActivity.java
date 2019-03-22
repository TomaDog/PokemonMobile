package com.example.loltierlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static ProgressBar loader;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        loader = findViewById(R.id.loader_main_activity);

        controller = new MainController(this, MainActivity.class);
        controller.onCreate();
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
    }

    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
    }

    public static void hideLoader() {
        loader.setVisibility(View.GONE);
    }

    public static void showList(List<Pokemon> listPokemon) {

    }
}