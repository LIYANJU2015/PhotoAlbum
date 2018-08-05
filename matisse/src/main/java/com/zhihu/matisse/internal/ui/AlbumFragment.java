package com.zhihu.matisse.internal.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.ui.adapter.AlbumsAdapter;

/**
 * Created by liyanju on 2018/8/4.
 */

public class AlbumFragment extends Fragment {

    private ListView listView;

    private AlbumsAdapter mAlbumsAdapter;

    private Cursor mCursor;

    private AdapterView.OnItemClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.actvity_album, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listview);
        mAlbumsAdapter = new AlbumsAdapter(getContext(), mCursor,
                false);
        listView.setAdapter(mAlbumsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onItemClick(parent, view, position, id);
            }
        });
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }

    public void setAlbumCursor(Cursor cursor) {
        if (mAlbumsAdapter != null) {
            mAlbumsAdapter.swapCursor(cursor);
        } else {
            mCursor = cursor;
        }
    }

    public void swapCursor(Cursor cursor) {
        if (mAlbumsAdapter != null) {
            mAlbumsAdapter.swapCursor(cursor);
        }
    }

    public Cursor getAlbumCursor() {
        if (mAlbumsAdapter == null) {
            return mCursor;
        }
        return mAlbumsAdapter.getCursor() != null ? mAlbumsAdapter.getCursor() : mCursor;
    }
}
