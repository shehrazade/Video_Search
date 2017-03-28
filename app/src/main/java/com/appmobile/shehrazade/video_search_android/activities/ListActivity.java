package com.appmobile.shehrazade.video_search_android.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appmobile.shehrazade.video_search_android.Constants;
import com.appmobile.shehrazade.video_search_android.R;
import com.appmobile.shehrazade.video_search_android.adapters.ListAdapter;
import com.appmobile.shehrazade.video_search_android.interfaces.OnItemSelectedListener;
import com.appmobile.shehrazade.video_search_android.models.Example;
import com.appmobile.shehrazade.video_search_android.models.Item;
import com.google.gson.Gson;

import java.util.List;

public class ListActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String SEARCH = "SEARCH"; //q
    private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet";

    private String researchName;
    private RecyclerView recyclerView;
    private ImageView imageView;

    public static void start(Context context, String researchName) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(SEARCH, researchName);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        researchName = getIntent().getStringExtra(SEARCH);
        imageView = (ImageView) findViewById(R.id.image);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSearch();
    }

    private void getSearch() {
        StringRequest searchsRequest = new StringRequest(SEARCH_URL + "&q="+ researchName + "&maxResults=33&type=video&key=" + Constants.API_KEY, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                //    parse data from webservice to get Research as Java object
               Example example = new Gson().fromJson(response, Example.class);
                setAdapter(example.getItems());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Research", "Error");
            }
        });

        Volley.newRequestQueue(this).add(searchsRequest);
    }

    private void setAdapter(List<Item> items) {
        ListAdapter adapter = new ListAdapter(items);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(Item video) {
       VideoActivity.start(this, video.getId().getVideoId());
    }


}
