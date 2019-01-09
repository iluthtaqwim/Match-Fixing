package com.example.iluth.finalprojectpam.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "tanggal";
    private static final String COL4 = "skor";
    private static final String COL5 = "homeClub";
    private static final String COL6 = "awayClub";


    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + "TEXT" + COL3 + "TEXT" + COL4 + "INTEGER" + COL5 + "TEXT" + COL6 + "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding" + item +"to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == 1){
            return false;
        }else{
            return true;
        }
    }
}
