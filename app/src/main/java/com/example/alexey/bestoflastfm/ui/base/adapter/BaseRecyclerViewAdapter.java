package com.example.alexey.bestoflastfm.ui.base.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.alexey.bestoflastfm.ui.list_artists.OnClick;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends BaseViewHolder<T>>
        extends RecyclerView.Adapter<VH> {

    protected OnClick<T> onClick;

    protected List<T> items;

    public BaseRecyclerViewAdapter(List<T> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(items.get(position));
    }

    public void updateDataSet(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClick(OnClick<T> onItemClick) {
        this.onClick = onItemClick;
    }

}