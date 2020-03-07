package com.cts.list.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cts.list.R;
import com.cts.list.databinding.ItemsListBinding;
import com.cts.list.model.data.ProductListing;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.CustomViewHolder> {

    private static final String TAG = "ShoppingCartAdapter";
    private Context context;
    private List<ProductListing> productList;
    private ItemsListBinding viewDataBinding;
    private boolean operationPending;

    public ProductListingAdapter(List<ProductListing> productList, Context context) {
        this.context = context;
        this.productList = productList;
    }


    @NotNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        ItemsListBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }


}
