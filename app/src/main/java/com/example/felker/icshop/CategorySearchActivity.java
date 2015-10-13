package com.example.felker.icshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchActivity extends AppCompatActivity {

    private List<MainRetailCategory> mainCats = new ArrayList<MainRetailCategory>();
    private List<SubRetailCategory> subCats = new ArrayList<SubRetailCategory>();
    private MyDatabase db;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);

        list = (ListView) findViewById(R.id.list);

        Spinner catSpinner = (Spinner)findViewById(R.id.MainCatSpinner);
        db = new MyDatabase(this);
        mainCats = db.getAllMainCategories();

        MainCategoryAdapter catAdapter= new MainCategoryAdapter(this,android.R.layout.simple_spinner_dropdown_item,mainCats);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(catAdapter);

        catSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                MainRetailCategory selectedCat = (MainRetailCategory) arg0.getAdapter().getItem(arg2);
                //Toast.makeText(getApplicationContext(), selectedCat.getDesc(),
                       // Toast.LENGTH_LONG).show();
                String CatID = String.valueOf(selectedCat.getID());
                db = new MyDatabase(getApplicationContext());
                subCats = db.getAllSubCategories(CatID);

                ArrayAdapter<SubRetailCategory> adapter = new ArrayAdapter<SubRetailCategory>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, subCats);

                String Cat = adapter.getItem(1).toString();

                Toast.makeText(getApplicationContext(), Cat,
                        Toast.LENGTH_LONG).show();

                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


}


