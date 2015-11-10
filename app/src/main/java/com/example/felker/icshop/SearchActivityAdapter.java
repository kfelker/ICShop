package com.example.felker.icshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Felker on 11/10/2015.
 */
public class SearchActivityAdapter<T> extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<T> mObjects;

    public SearchActivityAdapter(Context context, List<T> objects) {
        mInflater = LayoutInflater.from(context);
        mObjects = objects;
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.stores_list, null);
        }
        else {
            view = convertView;
        }

        TextView NameView = (TextView) view.findViewById(R.id.Name);
        TextView phoneNumberView = (TextView) view.findViewById(R.id.Phone);

        NameView.setText( mStores.get(position).getName() );
        phoneNumberView.setText( mStores.get(position).getPhone() );

        return view;
    }
}