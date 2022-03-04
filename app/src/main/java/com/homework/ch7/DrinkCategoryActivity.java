package com.homework.ch7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DrinkCategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_drinks_category);

        ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<>(DrinkCategoryActivity.this, android.R.layout.simple_list_item_1, Drink.drinks);

        ListView listView = (ListView) findViewById(R.id.drink_cate_list_view);
        listView.setAdapter(arrayAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DrinkCategoryActivity.this,DrinkDetailActivity.class);
                intent.putExtra(DrinkDetailActivity.EXTRA_ID,(int) l);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }
}
