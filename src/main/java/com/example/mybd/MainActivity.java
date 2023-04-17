package com.example.mybd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    EditText search;
    ListView lw;
    SimpleAdapter adapter;
    LinkedList<HashMap<String, Object>> adapterProductList = new LinkedList<>();
    public static final String KEY = "Hello";
    MyDataBase myDataBase;
    SQLiteDatabase sdb;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lw = findViewById(R.id.list);
        search = findViewById(R.id.search_et);

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
        cursor = sdb.rawQuery("SELECT * FROM table_kalor", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", cursor.getString(0));
            //map.put("belki", cursor.getDouble(1));
            //map.put("jir", cursor.getDouble(2));
            //map.put("uglevod", cursor.getDouble(3));
            //map.put("kalor", cursor.getDouble(4));
            adapterProductList.add(map);
            //.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        String [] from = {"name"
            //    , "belki",
            //    "jir", "uglevod", "kalor"
        };
        int [] to = {R.id.name_info
           //     , R.id.belki_info, R.id.jir_info,
        //.id.uglevod_info, R.id.kalor_info
        };
        adapter = new SimpleAdapter(this, adapterProductList, R.layout.list_item, from, to);
        lw.setAdapter(adapter);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getAdapter().getItem(position).toString();
                Intent i = new Intent(MainActivity.this, DBinfo.class);
                i.putExtra(KEY, Obrez(s));
                startActivity(i);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public String Obrez(String s)
    {
        String s1 = "";
        boolean flag = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if(flag)
            {
                s1 += s.charAt(i);
            }
            if(s.charAt(i) == '=')
                flag = true;
        }
        return s1;
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