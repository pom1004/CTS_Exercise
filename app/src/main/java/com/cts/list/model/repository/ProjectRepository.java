package com.cts.list.model.repository;


import android.util.Base64;
import android.util.Log;

import com.cts.list.model.data.ProductListing;
import com.cts.list.util.constant.Constant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    private static final String TAG = "ProjectRepository";
    private static ProjectRepository projectRepository;
    private RetrofitAPIService retrofitAPIService;

    private ProjectRepository() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_DOMAIN)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitAPIService = retrofit.create(RetrofitAPIService.class);
    }

    public synchronized static ProjectRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }



    public LiveData<List<ProductListing>> getProductList() {
        final MutableLiveData<List<ProductListing>> data = new MutableLiveData<>();

        retrofitAPIService.getProductListWithImage().enqueue(new Callback<List<ProductListing>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductListing>> call, @NonNull Response<List<ProductListing>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductListing>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });


        return data;
    }




}
