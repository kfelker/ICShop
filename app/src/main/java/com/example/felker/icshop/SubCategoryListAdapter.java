package com.example.felker.icshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felker on 10/12/2015.
 */
public class SubCategoryListAdapter  extends  ArrayAdapter<SubRetailCategory> {


    private Context mContext;
    private List<SubRetailCategory> mSubCat = new ArrayList<SubRetailCategory>();

    public SubCategoryListAdapter(Context context, List<SubRetailCategory> SubCat) {
        super(context, R.layout.subcategory_list, SubCat);

        mContext = context;
        mSubCat = SubCat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subcategory_list, null);
        }
        else {
            view = convertView;
        }

        TextView NameView = (TextView) view.findViewById(R.id.subCatName);

        NameView.setText( mSubCat.get(position).getDesc() );

        return view;
    }
}
