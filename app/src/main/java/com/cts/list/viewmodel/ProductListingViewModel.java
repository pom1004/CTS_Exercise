package com.cts.list.viewmodel;

import android.app.Application;

import com.cts.list.model.data.ProductListing;
import com.cts.list.model.repository.ProjectRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class ProductListingViewModel extends AndroidViewModel {

    private static final String TAG = "ShoppingCartViewModel";
    private LiveData<List<ProductListing>> mObservableProducts;
    private ProjectRepository projectRepository;


    public ProductListingViewModel(@NonNull Application application) {
        super(application);
        projectRepository = ProjectRepository.getInstance();
        mObservableProducts = projectRepository.getProductList();
    }


    public LiveData<List<ProductListing>> getProductListObservable() {
        return mObservableProducts;
    }



}
