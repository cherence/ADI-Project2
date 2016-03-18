package com.example.cher.cherproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leisforkokomo on 3/16/16.
 */
public class HPSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HPWorld.db";
    public static final String TABLE_NAME = "hpWorld";

    public static final String COL_ID = "_id";
    public static final String COL_TYPE = "type";
    public static final String COL_NAME = "name";
    public static final String COL_GENERAL_LOCATION = "generalLocation";
    public static final String COL_INFORMATION = "information";
    public static final String  COL_LOGO_IMAGE = "logoImage";
    public static final String COL_HEADER_IMAGE = "headerImage";
    public static final String COL_MAP_IMAGE = "mapImage";

    public static final String[] TABLE_COLUMNS = {COL_ID,COL_TYPE,COL_NAME,COL_GENERAL_LOCATION,COL_INFORMATION,COL_LOGO_IMAGE,COL_HEADER_IMAGE,COL_MAP_IMAGE};
    public static final String DROP_HPWORLD_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String CREATE_HPWORLD_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TYPE + " TEXT, " +
                    COL_NAME + " TEXT, " +
                    COL_GENERAL_LOCATION + " TEXT, " +
                    COL_INFORMATION + " INTEGER, " +
                    COL_LOGO_IMAGE + " INTEGER, " +
                    COL_HEADER_IMAGE + " INTEGER, " +
                    COL_MAP_IMAGE + " INTEGER)";

    private static HPSQLiteHelper mInstance;

    public static HPSQLiteHelper getmInstance(Context context){
        if(mInstance == null){
            mInstance = new HPSQLiteHelper(context.getApplicationContext());
        } return mInstance;
    }

    public HPSQLiteHelper(Context context) { //set to private at the end b/c of mInstance
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HPWORLD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HPWORLD_TABLE);
        onCreate(db); //might have to make it with "this"
    }

    public void insert(String type, String name, String generalLocation, String information, int logoImage, int headerImage, int mapImage){
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, type);
        values.put(COL_NAME, name);
        values.put(COL_GENERAL_LOCATION, generalLocation);
        values.put(COL_INFORMATION, information);
        values.put(COL_LOGO_IMAGE, logoImage);
        values.put(COL_HEADER_IMAGE, headerImage);
        values.put(COL_MAP_IMAGE, mapImage);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor getEntireTable(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                null,
                null,
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public String getNameByID(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[]{COL_NAME}, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(_id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_NAME));
        } else {
            return "No Name Found";
        }
    }

    public String getTypeByID(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[]{COL_TYPE}, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(_id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_TYPE));
        } else {
            return "No Type Found";
        }
    }

    public String getLocationByID(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[]{COL_GENERAL_LOCATION}, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(_id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_GENERAL_LOCATION));
        } else {
            return "No Location Found";
        }
    }

    public int getInfoByID(int _id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                new String[]{COL_INFORMATION}, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(_id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(COL_INFORMATION));
        } else {
            return -22;
        }
    }




}
