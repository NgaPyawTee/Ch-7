package com.homework.ch7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TopLevelActivity extends Activity {
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        FirstMethod();
        DatabaseMethod();
    }

    private void DatabaseMethod() {
        ListView listView = findViewById(R.id.fav_lv);

        try{
            SQLiteOpenHelper helper = new Ch7DatabaseHelper(this);
            db = helper.getReadableDatabase();

            cursor = db.query("CH7",
                    new String[]{"_id","NAME"},
                    "FAVORITE=1",
                    null,null,null,null);

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listView.setAdapter(cursorAdapter);

        }catch (SQLiteException e){
            Toast.makeText(this,"Database unavaliable", Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TopLevelActivity.this,DrinkDetailActivity.class);
                intent.putExtra(DrinkDetailActivity.EXTRA_ID,(int) l);
                startActivity(intent);
            }
        });

    }

    private void FirstMethod() {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.top_list_view);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ListView listView2 = findViewById(R.id.fav_lv);
        Cursor newCursor = db.query("CH7",
                new String[]{"_id","NAME"},
                "FAVORITE=1",
                null,null,null,null);
        CursorAdapter newAdapter = (CursorAdapter) listView2.getAdapter();
        newAdapter.changeCursor(newCursor);
        cursor=newCursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}