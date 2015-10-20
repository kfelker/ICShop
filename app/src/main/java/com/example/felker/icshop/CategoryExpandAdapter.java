package com.example.felker.icshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Felker on 10/15/2015.
 */
public class CategoryExpandAdapter extends BaseExpandableListAdapter {

        private final List<MainRetailCategory> mainCatList;
        public LayoutInflater inflater;
        public Activity activity;
        private Context context;

        public CategoryExpandAdapter (Context context, List<MainRetailCategory> mainCatList) {
            this.context = context;
            //activity = act;
            this.mainCatList = mainCatList;
            //inflater = act.getLayoutInflater();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            List<SubRetailCategory> subCatList = mainCatList.get(groupPosition).getSubCatList();
            return subCatList.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View view, ViewGroup parent) {

            SubRetailCategory detailInfo = (SubRetailCategory) getChild(groupPosition, childPosition);
            if (view == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = infalInflater.inflate(R.layout.sub_cat_row_group, null);
            }

            TextView childItem = (TextView) view.findViewById(R.id.childItem);
            childItem.setText(detailInfo.getDesc().trim());

            return view;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            List<SubRetailCategory> subList = mainCatList.get(groupPosition).getSubCatList();
            return subList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mainCatList.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return mainCatList.size();
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            super.onGroupExpanded(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        MainRetailCategory headerInfo = (MainRetailCategory) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.main_cat_row_group, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getDesc().trim());

        return view;
    }


        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
}
