package com.example.felker.icshop;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Felker on 10/11/2015.
 */
public class MainCategoryAdapter extends ArrayAdapter<MainRetailCategory> {
    private Context context;
    private List<MainRetailCategory> mainCatList;

    public MainCategoryAdapter(Context context, int textViewResourceId,
                       List<MainRetailCategory> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.mainCatList = values;
    }

    public int getCount(){
        return mainCatList.size();
    }

    public MainRetailCategory getItem(int position){
        return mainCatList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText(mainCatList.get(position).getDesc());

        return view;
    }

    //View of Spinner on dropdown Popping

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setText(mainCatList.get(position).getDesc());
        view.setHeight(60);

        return view;
    }

}
