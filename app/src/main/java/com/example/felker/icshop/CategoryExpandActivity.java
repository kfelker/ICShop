package com.example.felker.icshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryExpandActivity extends AppCompatActivity {

    private List<MainRetailCategory> mainList = new ArrayList<MainRetailCategory>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_expand);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_expand, menu);

        setContentView(R.layout.activity_category_expand);
        mainList = createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        CategoryExpandAdapter adapter = new CategoryExpandAdapter(this,mainList);
        listView.setAdapter(adapter);

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
