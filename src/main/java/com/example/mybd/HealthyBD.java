package com.example.mybd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HealthyBD extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "healthy.db";
    static final String TABLE_NAME = "tablo";
    static final int DATABASE_VERSION = 1;
    static final String COLUMN_NAME = "name";
    static final String COLUMN_BELKY = "belky";
    static final String COLUMN_JYR = "jyr";
    static final String COLUMN_UGLEVOD = "uglevod";
    static final String COLUMN_KALOR = "kalor";

    public HealthyBD(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME + " integer primary key autoincrement, " +
                COLUMN_BELKY + " real, " +
                COLUMN_JYR + " real, " +
                COLUMN_UGLEVOD + " real, " +
                COLUMN_KALOR + " real);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
