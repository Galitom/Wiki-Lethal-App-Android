package com.example.lethalcompany.ui.store;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lethalcompany.ui.store.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.lethalcompany.databinding.FragmentStore2Binding;
import com.example.lethalcompany.ui.store.placeholder.StoreItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<StoreItem> mValues;

    public MyItemRecyclerViewAdapter(List<StoreItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentStore2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName().toString());
        holder.mIdView.setOnClickListener(v -> {
            StoreItem storeItem = mValues.get(position);
            Intent intent = new Intent(holder.mIdView.getContext(),StoreDettaglioActivity.class);
            intent.putExtra("storeItem", storeItem);

            holder.mIdView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public StoreItem mItem;

        public ViewHolder(FragmentStore2Binding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
        }
    }
}