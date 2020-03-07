package com.cts.list.model.repository;


import com.cts.list.model.data.ProductListing;
import com.cts.list.util.constant.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RetrofitAPIService {


    @GET(Constant.WEBSERVICE.VELMM_IMAGES)
    Call<List<ProductListing>> getProductListWithImage();


}
