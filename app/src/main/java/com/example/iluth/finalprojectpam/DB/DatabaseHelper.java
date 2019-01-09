package com.example.iluth.finalprojectpam.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    //private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "favorit";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    //private static final String COL3 = "tanggal";
    //private static final String COL4 = "skor";
    //private static final String COL5 = "homeClub";
    //private static final String COL6 = "awayClub";


    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + "TEXT)";// + COL3 + "TEXT" + COL4 + "INTEGER" + COL5 + "TEXT" + COL6 + "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP IF TABLE EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding" + item +"to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public Cursor getItemID(String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL1 +" FROM "+ TABLE_NAME +" WHERE "+ COL2 +" = '"+ tanggal +"'";

        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public void updateName(String newTanggal, int id, String oldTanggal){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+ TABLE_NAME +" SET "+ COL2 + " = '"+ newTanggal +"'WHERE "+ COL1 +" = '"+ id +"' AND "+ COL2 +" ='"
                + oldTanggal +"'";
        db.execSQL(query);
    }

    public void deleteTitle(int id, String tanggal){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME +" WHERE "+ COL1 +" = '"+ id +"' AND "+ COL2 +" = '"+ tanggal +"'";
        db.execSQL(query);

    }
}
