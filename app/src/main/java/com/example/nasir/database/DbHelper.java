package com.example.nasir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String dbName="std.db";
    public static final String tableName="checking";
    public static final String col1="id";
    public static final String col2="Name";
    public static final String col3="Address";
    public static final String col4="Marks";

    public DbHelper (Context context ) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL("create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, MARKS INTEGER) ");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+tableName);
       onCreate(db);

    }

    public boolean insertData(String names , String address, String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col2,names);
        values.put(col3,address);
        values.put(col4,marks);

        long result=db.insert(tableName,null,values);

        if (result==-1){
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor re = db.rawQuery("select * from "+tableName,null);
        return re;
    }

    public boolean updateData(String id, String name,String address,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col1,id);
        values.put(col2,name);
        values.put(col3,address);
        values.put(col4,marks);

        db.update(tableName,values,"id=?",new String[]{id});

        return true;

    }

    public Integer deleteData(String id){

        SQLiteDatabase database=this.getWritableDatabase();
       return database.delete(tableName,"id= ?", new String[]{id});
    }

}
