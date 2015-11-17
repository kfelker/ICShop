package com.example.felker.icshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryExpandActivity extends AppCompatActivity {

    private List<MainRetailCategory> mainList = new ArrayList<MainRetailCategory>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_expand);

        mainList = createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        CategoryExpandAdapter adapter = new CategoryExpandAdapter(this,mainList);
        listView.setAdapter(adapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                // selected item
                //get the main group
                MainRetailCategory headerInfo = mainList.get(groupPosition);
                //get the child info
                SubRetailCategory detailInfo =  headerInfo.getSubCatList().get(childPosition);
                //display it or do something with it

                //System.err.println("child clicked");
                Toast.makeText(getApplicationContext(), detailInfo.toString()+ detailInfo.getID(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_expand, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<MainRetailCategory> createData() {
        List<MainRetailCategory> mList = new ArrayList<MainRetailCategory>();
        List<SubRetailCategory> subList = new ArrayList<SubRetailCategory>();
        db = new MyDatabase(getApplicationContext());
        mList = db.getAllMainCategories();

        for (int i = 0; i < mList.size(); i++) {
            String id = String.valueOf(mList.get(i).getID());
            subList = db.getAllSubCategories(id);
            mList.get(i).setSubCatList(subList);


        /*for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }*/
        }
        return mList;
    }
}
