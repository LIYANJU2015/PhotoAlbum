package com.zhihu.matisse.internal.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liyanju on 2018/8/4.
 */

public class PreviewRecyclerViewAdapter extends RecyclerView.Adapter<PreviewRecyclerViewAdapter.Holder> {

    private Context mContext;
    private Set<Item> mSet;

    private PreviewClickListener mListener;

    public PreviewRecyclerViewAdapter(Context context, Set<Item> set) {
        mContext = context;
        mSet = set;

        if (mSet.size() > 0) {
            Item item = mSet.iterator().next();
            if (item != null) {
                item.isPageSelected = true;
            }
        }
    }

    public void setItemClickListener(PreviewClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.preview_item, null));
    }

    private Item getSetItem(int postion) {
        Iterator<Item> itemIterator = mSet.iterator();
        int index = 0;
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (index == postion) {
                return item;
            }
            index++;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final Item item = getSetItem(position);
        if (item == null) {
            return;
        }

        if (item.isPageSelected) {
            holder.previewImageView.setSelected(true);
        } else {
            holder.previewImageView.setSelected(false);
        }

        SelectionSpec.getInstance().imageEngine.loadThumbnail(mContext, 52,
                ContextCompat.getDrawable(mContext, R.drawable.sc_photo_default),
                holder.previewImageView, item.getContentUri());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickListener(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSet.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView previewImageView;

        public Holder(View itemView) {
            super(itemView);
            previewImageView = itemView.findViewById(R.id.sc_preview_iv);
        }
    }

    public interface PreviewClickListener {

        void onClickListener(Item item);
    }
}
