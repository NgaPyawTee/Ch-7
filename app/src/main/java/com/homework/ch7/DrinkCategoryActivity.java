package com.homework.ch7;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DrinkCategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_drinks_category);

        ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Drink.drinks);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
    }
}
