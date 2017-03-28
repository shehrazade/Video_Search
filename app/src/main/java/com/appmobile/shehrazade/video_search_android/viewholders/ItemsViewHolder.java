package com.appmobile.shehrazade.video_search_android.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appmobile.shehrazade.video_search_android.R;
import com.appmobile.shehrazade.video_search_android.activities.VideoActivity;
import com.appmobile.shehrazade.video_search_android.interfaces.OnItemSelectedListener;
import com.appmobile.shehrazade.video_search_android.models.Item;
import com.squareup.picasso.Picasso;

/**
 * Created by Shehrazade on 22/03/2017.
 */

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView title, channelTitle;
    private ImageView image;
    private Context context;
    private OnItemSelectedListener onItemSelectedListener;


    public ItemsViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        channelTitle = (TextView) itemView.findViewById(R.id.channelTitle);
        image = (ImageView) itemView.findViewById(R.id.image);
        context = itemView.getContext();
    }

    public void bind(final Item item) {
        title.setText( item.getSnippet().getTitle());
        channelTitle.setText( item.getSnippet().getChannelTitle());
//        item.getId().getVideoId();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener == null) {
                 return;
                }
                VideoActivity.start(context, item.getId().getVideoId());
                onItemSelectedListener.onItemSelected(item);
            }
        });
        Picasso.with(context)
                .load(item.getSnippet().getThumbnails().getMedium().getUrl())
                .resize(item.getSnippet().getThumbnails().getMedium().getWidth(),item.getSnippet().getThumbnails().getMedium().getHeight())
                .into(image);

    }


    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
