package com.example.mybd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.IOException;

public class DBinfo extends AppCompatActivity {

    SQLiteDatabase sdb;
    HealthyBD healthyBD;
    Cursor cursor;
    TextView lw;
    MyDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbinfo);
        healthyBD = new HealthyBD(this);
        sdb = healthyBD.getWritableDatabase();
        lw = findViewById(R.id.info_tw);

        String s = getIntent().getStringExtra(MainActivity.KEY);
        lw.setText(s + "\n");

        myDataBase = new MyDataBase(this);
        try{
            myDataBase.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        try{
            sdb = myDataBase.getWritableDatabase();
        }catch (SQLException mSQLException)
        {
            throw mSQLException;
        }
        String query = "SELECT * FROM table_kalor WHERE name = '" + s + "';";
        cursor = sdb.rawQuery(query,
                null);
        cursor.moveToFirst();
        lw.append(cursor.getString(1) + "\n");
        lw.append(cursor.getDouble(2) + "\n");
        lw.append(cursor.getDouble(3) + "\n");
        lw.append(cursor.getDouble(4) + "\n");
        cursor.close();


       // String query = "SELECT* FROM " + HealthyBD.TABLE_NAME + ";";
       // sdb.rawQuery(query, null);
        //cursor = sdb.rawQuery(query, null);
        //String [] from = {HealthyBD.COLUMN_NAME,
        //        HealthyBD.COLUMN_BELKY, HealthyBD.COLUMN_JYR,
        //        HealthyBD.COLUMN_UGLEVOD, HealthyBD.COLUMN_KALOR};
        //int [] to = {R.id.name_info, R.id.belky_info,
       //         R.id.jyr_info, R.id.uglevod_info, R.id.kalor_info};


        //cursor.getCount() - проверяет, есть ли ответ на запрос
        // (существует ли объект с таким запросом)


        //SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter
        //        (this, R.layout.list_item, cursor, from, to,
        //                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //lw.setAdapter(cursorAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}