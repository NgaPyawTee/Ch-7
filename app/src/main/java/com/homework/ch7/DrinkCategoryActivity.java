package com.homework.ch7;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class DrinkCategoryActivity extends Activity {
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_drinks_category);

        ListView listView = (ListView) findViewById(R.id.drink_cate_list_view);

        SQLiteOpenHelper helper = new Ch7DatabaseHelper(this);
        try{
            db = helper.getReadableDatabase();
            cursor = db.query("CH7",
                    new String[]{"_id","NAME"},
                    null,null,null,null,null);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listView.setAdapter(adapter);

        }catch (SQLiteException e){
            Toast.makeText(this, "Database Unavaliable", Toast.LENGTH_SHORT).show();
        }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
