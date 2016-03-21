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
    public static final String COL_FAVORITE_STATUS = "favoriteStatus";
    public static final String COL_INFORMATION = "information";
    public static final String COL_LOGO_IMAGE = "logoImage";
    public static final String COL_HEADER_IMAGE = "headerImage";
    public static final String COL_MAP_IMAGE = "mapImage";


    public static final String[] TABLE_COLUMNS = {COL_ID,COL_TYPE,COL_NAME,COL_GENERAL_LOCATION,COL_FAVORITE_STATUS,COL_INFORMATION,COL_LOGO_IMAGE,COL_HEADER_IMAGE,COL_MAP_IMAGE};
    public static final String DROP_HPWORLD_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String CREATE_HPWORLD_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TYPE + " TEXT, " +
                    COL_NAME + " TEXT, " +
                    COL_GENERAL_LOCATION + " TEXT, " +
                    COL_FAVORITE_STATUS + " TEXT, " +
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

    public void insert(String type, String name, String generalLocation, String favoriteStatus, int information, int logoImage, int headerImage, int mapImage){
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, type);
        values.put(COL_NAME, name);
        values.put(COL_GENERAL_LOCATION, generalLocation);
        values.put(COL_FAVORITE_STATUS, favoriteStatus);
        values.put(COL_INFORMATION, information);
        values.put(COL_LOGO_IMAGE, logoImage);
        values.put(COL_HEADER_IMAGE, headerImage);
        values.put(COL_MAP_IMAGE, mapImage);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateFavoriteStatus(int _id, String favoriteStatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE_STATUS, favoriteStatus);

        db.update(TABLE_NAME, values, "_id=" + _id, null);
        db.close();
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

    public Cursor getFoodRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Food"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor getRidesRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Rides"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor getShowsRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Shows"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor getShoppingRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Shopping"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }


    public Cursor getFavoriteRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_FAVORITE_STATUS + " = ?",
                new String[]{"true"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchEntireTable(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " LIKE ? OR " + COL_NAME + " LIKE ? OR " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                null,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchFoodRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Food' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Food' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchRidesRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Rides' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Rides' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchShowsRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Shows' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Shows' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchShoppingRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Shopping' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Shopping' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Cursor searchFavoritesRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_FAVORITE_STATUS + "= 'true' AND " + COL_TYPE + " LIKE ? OR " + COL_NAME + " LIKE ? OR " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%"},
                COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    public Categories createObject(int _id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                TABLE_COLUMNS, // b. column names
                COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(_id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        cursor.moveToFirst();
        String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String generalLocation = cursor.getString(cursor.getColumnIndex(COL_GENERAL_LOCATION));
        String favoriteStatus = cursor.getString(cursor.getColumnIndex(COL_FAVORITE_STATUS));
        int informationStringRID = cursor.getInt(cursor.getColumnIndex(COL_INFORMATION));
        int logoImageRID = cursor.getInt(cursor.getColumnIndex(COL_LOGO_IMAGE));
        int headerImageRID = cursor.getInt(cursor.getColumnIndex(COL_HEADER_IMAGE));
        int mapImageRID = cursor.getInt(cursor.getColumnIndex(COL_MAP_IMAGE));
        return new Categories(type, name, generalLocation, favoriteStatus, informationStringRID,logoImageRID, headerImageRID, mapImageRID);
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
