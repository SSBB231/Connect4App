package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 12/13/17.
 */

import android.view.View;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.content.Context;
import android.view.ViewGroup;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void addPiece(int position,String color){
        if(color == "RED"){
            mThumbIds[position] = R.drawable.red;
        }
        else{
            mThumbIds[position] = R.drawable.yellow;
        }
        notifyDataSetChanged(); // add this method
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,R.drawable.cell,
    };
}