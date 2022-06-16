package com.synergy.jsonrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import com.synergy.jsonrecyclerview.network.ApiClient;
import com.synergy.jsonrecyclerview.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NewsResponse.Article> recyclerDataList;
    NewsAdapter recyclerViewAdapter;
    SearchView editsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = findViewById( R.id.additional__recycler );

        recyclerDataList = new ArrayList<>();

        // Locate the EditText in listview_main.xml
        //change by saalim
        editsearch=findViewById( R.id.src_searchView );
        editsearch.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String charText = newText;
                recyclerViewAdapter.getFilter().filter(charText);
                return false;
            }
        } );

        getAllData();

    }

    private void getAllData() {


        ApiInterface apiInterface = ApiClient.getClientCI().create( ApiInterface.class );
        apiInterface.getBasic().enqueue( new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {


                  // below line is to add our data from api to our array list.
                   recyclerDataList = response.body().getArticles();

                    recyclerViewAdapter = new NewsAdapter( recyclerDataList, NewsActivity.this );

                    // below line is to set layout manager for our recycler view.
                    LinearLayoutManager manager = new LinearLayoutManager( NewsActivity.this );

                    // setting layout manager for our recycler view.
                    recyclerView.setLayoutManager( manager );

                    // below line is to set adapter to our recycler view.
                    recyclerView.setAdapter( recyclerViewAdapter );


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }


        } );


    }}


