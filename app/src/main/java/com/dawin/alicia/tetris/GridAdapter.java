package com.dawin.alicia.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> mBitmap;

    public GridAdapter(Context c, ArrayList<Bitmap> list) {
        mContext = c;
        mBitmap = list;
    }

    public int getCount() {
        return mBitmap.size();
    }

    public Object getItem(int position) {
        return mBitmap.get(position);
    }

    public long getItemId(int position) {
        return mBitmap.indexOf(position);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mBitmap.get(position));
        return imageView;
    }
}
