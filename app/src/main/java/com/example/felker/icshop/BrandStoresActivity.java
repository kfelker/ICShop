package com.example.felker.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BrandStoresActivity extends AppCompatActivity {
    private List<Store> Stores = new ArrayList<Store>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_stores);

        Intent i= getIntent();
        String id = i.getStringExtra("brandID");
        String strBrand = i.getStringExtra("brandName");

        //Toast.makeText(getApplicationContext(), id,
            //    Toast.LENGTH_LONG).show();


        db = new MyDatabase(this);
        Stores = db.getStoresByBrand(id); // you would not typically call this on the main thread

        StoresListAdapter adapter = new StoresListAdapter(this, Stores);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        TextView Brand = (TextView) this.findViewById(R.id.headertext);
        Brand.setText("Stores for:" + strBrand);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Store clickedObject = (Store) parent.getAdapter().getItem(position);

                String strId = String.valueOf(clickedObject.getID());
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                // sending data to new activity
                i.putExtra("storeID", strId);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case  R.id.action_Brand:
                //Create Intent for Shopping List Activity
                Intent listIntent = new Intent(this,BrandListActivity.class);
                //Start Product Activity
                startActivity(listIntent);
                return true;
            case R.id.action_Category:
                //Create Intent for Product Activity
                //Intent productIntent = new Intent(this,ProduceListActivity.class);
                //Start Product Activity
                //startActivity(productIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
