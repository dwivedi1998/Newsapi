package com.synergy.jsonrecyclerview.network;


import com.synergy.jsonrecyclerview.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("top-headlines?sources=techcrunch&apiKey=e48cbd4739294a7a87ab528d040b41d7")
    Call<NewsResponse> getBasic();

}
