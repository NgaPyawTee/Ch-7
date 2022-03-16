package com.homework.ch7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
        CheckBox checkBox = findViewById(R.id.checkbox);

        SQLiteOpenHelper helper =new Ch7DatabaseHelper(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("CH7",
                    new String[]{"NAME","DESCRIPTION","IMAGEID","FAVORITE"},
                    "_id=?",
                    new String[]{Integer.toString(drinkID)},
                    null,null,null);

            if (cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descText = cursor.getString(1);
                int imgID = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);

                textView1.setText(nameText);
                textView2.setText(descText);
                imageView.setImageResource(imgID);
                checkBox.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(this, "Database Unavaliable", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateDatabase(View view) {
        int drinkID = (int) getIntent().getExtras().get(EXTRA_ID);

        new UpdateAsynTask().execute(drinkID);
    }


    private class UpdateAsynTask extends AsyncTask<Integer,Void,Boolean> {
        private ContentValues values;

        @Override
        protected void onPreExecute() {
            CheckBox checkBox = findViewById(R.id.checkbox);
            values = new ContentValues();
            values.put("FAVORITE",checkBox.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int id = integers[0];
            SQLiteOpenHelper helper = new Ch7DatabaseHelper(DrinkDetailActivity.this);

            try {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.update("CH7",
                        values,
                        "_id=?",
                        new String[]{Integer.toString(id)}
                );
                db.close();
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success){
                Toast.makeText(DrinkDetailActivity.this, "Database updated", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(DrinkDetailActivity.this, "Database unavaliable", Toast.LENGTH_SHORT).show();
        }
    }
}