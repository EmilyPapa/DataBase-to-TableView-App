package com.emily.mydbapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EMILYDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "PERSON";
    public static final String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME STRING, AGE INTEGER, JOB STRING)";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE);
        //Create tables again
        onCreate(db);
    }

    public void insertData( String NAME, Integer AGE, String JOB  ){

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("JOHN", NAME);
            values.put(String.valueOf(25), AGE);
            values.put("DEVELOPER",JOB);

            // Insert Row
            long i = db.insert(TABLE_NAME, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }


}
