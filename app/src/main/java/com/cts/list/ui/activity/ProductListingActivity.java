package com.cts.list.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cts.list.R;
import com.cts.list.databinding.ActivityListingBinding;
import com.cts.list.model.data.ProductListing;
import com.cts.list.ui.adapter.ProductListingAdapter;
import com.cts.list.viewmodel.ProductListingViewModel;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ProductListingActivity extends BaseActivity {

    private static final String TAG = "ListingActivity";
    ActivityListingBinding binding;
    private ProductListingViewModel viewModel;
    private ProductListingAdapter productListingAdapter;
    private List<ProductListing> productListingList;
   /* ActivityListingBi
    ActivityShoppingCartBinding binding;
    private ShoppingCartAdapter shoppingCartAdapter;
    private ShoppingCart shoppingCart;
    private DeleteCartProduct deleteCartProduct;
    private int quatity = 1;
    private String type = "";
    private AddedToCart addedToCart;
    private String totalamount = "";
    private List<ShoppingCart.ListingBean> listingBeans;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listing);
        initToolBar();


        viewModel =
                ViewModelProviders.of(this).get(ProductListingViewModel.class);

        observeViewModel();


    }

    private void observeViewModel() {
        // Update the list when the data changes
        showProgress();

        viewModel.getProductListObservable()
                .observe(this, responseBodyResponse -> {
                    hideProgress();
                    if (responseBodyResponse != null) {
                        Log.e(TAG, "observeViewModel: ");

                        if (responseBodyResponse.size() != 0) {
                            productListingList = responseBodyResponse;
                            setList();
                        } else {

                        }

                     /*   if (responseBodyResponse.getAck().equalsIgnoreCase("success")) {
                            this.shoppingCart = responseBodyResponse;
                            if (shoppingCart.getListing().size() != 0) {
                                binding.includeMainCart.noProductTxt.setVisibility(View.GONE);
                                binding.includeMainCart.nestedScrollView.setVisibility(View.VISIBLE);
                                binding.includeMainCart.checkoutBtn.setVisibility(View.VISIBLE);
                                JanaticsApplication.getInstance().saveNonLoginCartCount(String.valueOf(shoppingCart.getListing().size()));
                                JanaticsApplication.getInstance().saveCheckoutProduct(shoppingCart.getListing());
                                totalamount = shoppingCart.getTotal_amount();
                                setAmountValue();
                                listingBeans = responseBodyResponse.getListing();

                            } else {
                                noProduct();
                            }
                        } else {
                            noProduct();
                        }*/
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    /*private void noProduct() {
        JanaticsApplication.getInstance().saveNonLoginCartCount("00");
        binding.includeMainCart.nestedScrollView.setVisibility(View.GONE);
        binding.includeMainCart.checkoutBtn.setVisibility(View.GONE);
        binding.includeMainCart.noProductTxt.setVisibility(View.VISIBLE);
        binding.includeMainCart.noProductTxt.setText("No product available!");
    }*/


    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setList() {
        if (binding.includeMain.list != null) {
            productListingAdapter = new ProductListingAdapter(productListingList, getBaseContext());
            binding.includeMain.list.setAdapter(productListingAdapter);
            binding.includeMain.list.setLayoutManager(new LinearLayoutManager(this));
            binding.includeMain.list.addItemDecoration(
                    new DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
                        @Override
                        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                            int position = parent.getChildAdapterPosition(view);
                            // hide the divider for the last child
                            if (position == parent.getAdapter().getItemCount() - 1) {
                                outRect.setEmpty();
                            } else {
                                super.getItemOffsets(outRect, view, parent, state);
                            }
                        }
                    }
            );


        }

    }

}
