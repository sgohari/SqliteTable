package com.example.nasir.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewContent extends AppCompatActivity {

    DbHelper myDB;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myDB= new DbHelper(this);


        ListView lsv = (ListView)findViewById(R.id.myLstV);

        ArrayList<String> theList=new ArrayList<>();

        Cursor data= myDB.getAllData();

        if (data.getCount()==0){
            Toast.makeText(ListViewContent.this,"Database was empty",Toast.LENGTH_LONG).show();

        }
        else {
            while (data.moveToNext()){
                theList.add(data.getString(1));

                ListAdapter listAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                lsv.setAdapter(listAdapter);
            }
        }

    }
}
