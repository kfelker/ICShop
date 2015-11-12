package com.example.felker.icshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    Button btnSend;
    private List<SearchResultsObject> searchDS = new ArrayList<SearchResultsObject>();
    private MyDatabase db;
    private ListView list;
    private String strSearch;
    public static final int SEARCH_BRAND=1;
    public static final int SEARCH_STORE=2;
    public ArrayAdapter<SearchResultsObject> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //brands =null;
        ListView list = (ListView) findViewById(R.id.listViewBrands);
        ArrayAdapter<SearchResultsObject> adapter = new ArrayAdapter<SearchResultsObject>(this,
          android.R.layout.simple_list_item_1, searchDS);
       list.setAdapter(adapter);
        btnSend = (Button) this.findViewById(R.id.buttonBrandSearch);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText userInput = (EditText) findViewById(R.id.textViewSearch);
                strSearch =userInput.getText().toString();

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.searchGroup);
                //int selectedId = radioGroup.getCheckedRadioButtonId();

                //find the radio button by returned id
               //RadioButton rButton = (RadioButton) findViewById(selectedId);

                db = new MyDatabase(getApplicationContext());
                ListView list = (ListView) findViewById(R.id.listViewBrands);

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbBrand:
                        searchDS = db.getSearchResults(strSearch,SEARCH_BRAND);
                        break;
                    case R.id.rbStore:
                        searchDS = db.getSearchResults(strSearch, SEARCH_STORE);
                        break;
                }
                ArrayAdapter<SearchResultsObject> adapter = new ArrayAdapter<SearchResultsObject>((getApplicationContext()),
                    android.R.layout.simple_list_item_1, searchDS);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
}
