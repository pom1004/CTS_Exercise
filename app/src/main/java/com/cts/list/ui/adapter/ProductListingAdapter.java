package com.cts.list.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.cts.list.R;
import com.cts.list.databinding.ItemsListBinding;
import com.cts.list.model.data.ProductListing;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.CustomViewHolder> {

    private static final String TAG = "ShoppingCartAdapter";
    Context context;
    LayoutInflater inflater;
    List<ProductListing> productList;
    ItemsListBinding viewDataBinding;
    private boolean operationPending;

    public ProductListingAdapter(List<ProductListing> productList, Context context) {
        this.context = context;
        this.productList = productList;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater != null ? inflater.inflate(R.layout.items_list, parent, false) : null;
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setSetList(productList.get(holder.getAdapterPosition()));
        viewDataBinding.executePendingBindings();

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }











    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private ItemsListBinding mViewDataBinding;

        CustomViewHolder(View v) {
            super(v);
            mViewDataBinding = DataBindingUtil.bind(v);
        }

        public ItemsListBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }


}
