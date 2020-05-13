package com.example.addtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE = "data";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String COUNTRY = "country";

    public static final int DB_VER = 1;
    public static final String DB = "datastore";

    private static final String CREATE_TABLE = "create table " + TABLE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + MOBILE + " TEXT NOT NULL, " +
            COUNTRY + " TEXT NOT NULL);";


    public DBHelper(@Nullable Context context) {
        super(context, DB, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertNote(String name, String mobile, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAME, name);
        contentValue.put(DBHelper.MOBILE, mobile);
        contentValue.put(DBHelper.COUNTRY, country);
        try {
            db.insert(DBHelper.TABLE, null, contentValue);
        }catch (Exception e){
            Log.d("TAG",e.getMessage());
        }
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                notes.add(new Note(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        return notes;
    }

    public void deleteNote(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBHelper.TABLE, DBHelper._ID + "=" + _id, null);
    }

}
