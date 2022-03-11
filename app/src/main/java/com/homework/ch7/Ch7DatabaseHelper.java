package com.homework.ch7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Ch7DatabaseHelper extends SQLiteOpenHelper {
    private static final String db_name = "starbuzz";
    private static final int db_version = 2;

    public Ch7DatabaseHelper(@Nullable Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UpdateMyDatabase(db, 0, db_version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        UpdateMyDatabase(sqLiteDatabase, i, i1);
    }

    private void UpdateMyDatabase(SQLiteDatabase db, int old_version, int new_version) {
        if (old_version < 1) {
            db.execSQL("CREATE TABLE CH7(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME STRING,"
                    + "DESCRIPTION STRING,"
                    + "IMAGEID INTEGER);");
            insertData(db, "RE4", "Leon", R.drawable.re);
            insertData(db, "GoW", "Kratos", R.drawable.gow);
            insertData(db, "AC", "Ubisoft", R.drawable.ac);
        }
        if (old_version < 2) {
            db.execSQL("ALTER TABLE CH7 ADD COLUMN FAVORITE NUMERIC;");
        }
    }


    private void insertData(SQLiteDatabase db, String name, String description, int id) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DESCRIPTION", description);
        values.put("IMAGEID", id);
        db.insert("CH7", null, values);
    }
}
