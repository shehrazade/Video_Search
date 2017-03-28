package com.appmobile.shehrazade.video_search_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appmobile.shehrazade.video_search_android.R;
import com.appmobile.shehrazade.video_search_android.interfaces.OnItemSelectedListener;
import com.appmobile.shehrazade.video_search_android.models.Item;
import com.appmobile.shehrazade.video_search_android.viewholders.ItemsViewHolder;

import java.util.List;

/**
 * Created by Shehrazade on 22/03/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private final List<Item> items;
    private OnItemSelectedListener onItemSelectedListener;


    public ListAdapter(List<Item> items) {
        this.items = items;
    }


    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
       holder.setOnItemSelectedListener(onItemSelectedListener);
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
