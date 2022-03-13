package com.homework.ch7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "drink_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        int drinkID = (int) getIntent().getExtras().get(EXTRA_ID);

        ImageView imageView = findViewById(R.id.image_view);
        TextView textView1 = findViewById(R.id.text_view1);
        TextView textView2 = findViewById(R.id.text_view2);

        SQLiteOpenHelper helper =new Ch7DatabaseHelper(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("CH7",
                    new String[]{"NAME","DESCRIPTION","IMAGEID"},
                    "_id=?",
                    new String[]{Integer.toString(drinkID)},
                    null,null,null);

            if (cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descText = cursor.getString(1);
                int imgID = cursor.getInt(2);

                textView1.setText(nameText);
                textView2.setText(descText);
                imageView.setImageResource(imgID);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(this, "Database Unavaliable", Toast.LENGTH_SHORT).show();
        }
    }
}