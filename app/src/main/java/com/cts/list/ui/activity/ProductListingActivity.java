package com.cts.list.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cts.list.R;
import com.cts.list.databinding.ActivityListingBinding;
import com.cts.list.model.data.ProductListing;
import com.cts.list.ui.adapter.ProductListingAdapter;
import com.cts.list.viewmodel.ProductListingViewModel;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ProductListingActivity extends BaseActivity {

    private static final String TAG = "ListingActivity";
    ActivityListingBinding binding;
    private ProductListingViewModel viewModel;
    private List<ProductListing> productListingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listing);
        initToolBar();

        viewModel =
                new ViewModelProvider(this).get(ProductListingViewModel.class);

        observeViewModel(true);


    }

    // Update the list when the data changes
    private void observeViewModel(boolean showLoading) {
        if (showLoading)
            binding.includeMain.spinnerLoading.setVisibility(View.VISIBLE);
        else
            Toast.makeText(this, getResources().getString(R.string.refreshing), Toast.LENGTH_SHORT).show();

        viewModel.getProductListObservable()
                .observe(this, responseBodyResponse -> {
                    binding.includeMain.spinnerLoading.setVisibility(View.GONE);
                    if (responseBodyResponse != null) {
                        Log.e(TAG, "observeViewModel: ");

                        if (responseBodyResponse.size() != 0) {
                            productListingList = responseBodyResponse;
                            setList();
                        } else {
                            binding.includeMain.noProduct.setVisibility(View.VISIBLE);
                            Toast.makeText(this, getResources().getString(R.string.noproduct), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    /**
     * Initializing toolbar
     */
    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setList() {
        ProductListingAdapter productListingAdapter = new ProductListingAdapter(productListingList, getBaseContext());
        binding.includeMain.list.setAdapter(productListingAdapter);
        binding.includeMain.list.setLayoutManager(new LinearLayoutManager(this));
        binding.includeMain.list.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        int position = parent.getChildAdapterPosition(view);
                        // hide the divider for the last child
                        if (position == Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1) {
                            outRect.setEmpty();
                        } else {
                            super.getItemOffsets(outRect, view, parent, state);
                        }
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.e(TAG, "onOptionsItemSelected: ");
        int id = item.getItemId();
        if (id == R.id.refreshMenu) {
            observeViewModel(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
