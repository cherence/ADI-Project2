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

    //region Private Variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HPWorld.db";
    private static final String TABLE_NAME = "hpWorld";
    private static final String REVIEW_TABLE_NAME = "attractionReviews";
    //endregion Private Variables

    //region Public Variables
    public static final String COL_ID = "_id";
    public static final String COL_TYPE = "type";
    public static final String COL_NAME = "name";
    public static final String COL_GENERAL_LOCATION = "generalLocation";
    public static final String COL_FAVORITE_STATUS = "favoriteStatus";
    public static final String COL_INFORMATION = "information";
    public static final String COL_LOGO_IMAGE = "logoImage";
    public static final String COL_HEADER_IMAGE = "headerImage";
    public static final String COL_MAP_IMAGE = "mapImage";
    public static final String COL_ID_REVIEW_TABLE = "_id";
    public static final String COL_ATTRACTION_ID = "attractionID";
    public static final String COL_REVIEW = "review";
    //endregion Public Variables

    //region Private Variables
    private static final String[] TABLE_COLUMNS = {COL_ID,COL_TYPE,COL_NAME,COL_GENERAL_LOCATION,COL_FAVORITE_STATUS,COL_INFORMATION,COL_LOGO_IMAGE,COL_HEADER_IMAGE,COL_MAP_IMAGE};
    private static final String[] REVIEW_TABLE_COLUMNS = {COL_ID_REVIEW_TABLE, COL_ATTRACTION_ID, COL_REVIEW};

    private static final String DROP_HPWORLD_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String DROP_REVIEW_TABLE = "DROP TABLE IF EXISTS " + REVIEW_TABLE_NAME;

    private static final String CREATE_HPWORLD_TABLE =
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

    private static final String CREATE_REVIEW_TABLE =
            "CREATE TABLE " + REVIEW_TABLE_NAME +
                    "(" +
                    COL_ID_REVIEW_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ATTRACTION_ID + " INTEGER, " +
                    COL_REVIEW + " TEXT, " +
                    "FOREIGN KEY (" + COL_ATTRACTION_ID + ") REFERENCES " + TABLE_NAME + "(" + COL_ID + "))";

    private static HPSQLiteHelper mInstance;
    //endregion Private Variables

    /**
     * Creates an instance (singleton) of the HPSQLiteHelper.
     * @param context
     * @return
     */

    public static HPSQLiteHelper getmInstance(Context context){
        if(mInstance == null){
            mInstance = new HPSQLiteHelper(context.getApplicationContext());
        } return mInstance;
    }

    /**
     * HPSQLiteHelper constructor. Set to private because a HPSQLiteHelper singleton was made.
     * @param context
     */

    private HPSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method creates the tables.
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HPWORLD_TABLE);
        db.execSQL(CREATE_REVIEW_TABLE);
    }

    /**
     * If a later table version is available, this method will delete the old table and replace it
     * with the new one.
     * @param db
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HPWORLD_TABLE);
        db.execSQL(DROP_REVIEW_TABLE);
        onCreate(db);
    }

    /**
     * This method inserts rows (new attractions) into the HP Table (#TABLE_NAME).
     * @param type
     * @param name
     * @param generalLocation
     * @param favoriteStatus
     * @param information
     * @param logoImage
     * @param headerImage
     * @param mapImage
     */

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

    /**
     * This method inserts review rows into the review table (#REVIEW_TABLE_NAME)
     * @param attractionID
     * @param review
     */

    public void insertReviews(int attractionID, String review){
        ContentValues values = new ContentValues();
        values.put(COL_ATTRACTION_ID, attractionID);
        values.put(COL_REVIEW, review);


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(REVIEW_TABLE_NAME, null, values);
        db.close();
    }

    /**
     * This method updates the favoriteStatus column of the row matching the specific #_id.
     * @param _id
     * @param favoriteStatus
     */

    public void updateFavoriteStatus(int _id, String favoriteStatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE_STATUS, favoriteStatus);

        db.update(TABLE_NAME, values, "_id=" + _id, null);
        db.close();
    }

    /**
     * This method will return the cursor for a query of all the rows in the table.
     * @return
     */

    public Cursor getEntireTable(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                null,
                null,
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all the rows with type food.
     * @return
     */

    public Cursor getFoodRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Food"},
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all the rows with type rides.
     * @return
     */

    public Cursor getRidesRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Rides"},
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all the rows with type shows.
     * @return
     */

    public Cursor getShowsRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Shows"},
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all the rows with type shopping.
     * @return
     */

    public Cursor getShoppingRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " = ?",
                new String[]{"Shopping"},
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all the rows marked as favorite.
     * @return
     */

    public Cursor getFavoriteRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_FAVORITE_STATUS + " = ?",
                new String[]{MainActivity.FAVORITE},
                null, //COL_TYPE,
                null,
                null, //COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method will return the cursor for a query of all review rows matching the specific
     * attraction ID of attraction featured on in DetailsActivity.
     * @param attractionID
     * @return
     */

    public Cursor getReviewRows(int attractionID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(REVIEW_TABLE_NAME,
                REVIEW_TABLE_COLUMNS,
                COL_ATTRACTION_ID + " = ?",
                new String[]{String.valueOf(attractionID)},
                null,
                null,
                null,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on the entire table (all categories).
     * It allows the user to search by name, type (category), and general location.
     * @param query
     * @return
     */

    public Cursor searchEntireTable(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + " LIKE ? OR " + COL_NAME + " LIKE ? OR " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                null,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on all attractions in the food category.
     * It allows the user to search within the food category (type) by name and general location.
     * @param query
     * @return
     */

    public Cursor searchFoodRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Food' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Food' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null, //COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on all attractions in the rides category.
     * It allows the user to search within the rides category (type) by name and general location.
     * @param query
     * @return
     */

    public Cursor searchRidesRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Rides' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Rides' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null, //COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on all attractions in the shows category.
     * It allows the user to search within the shows category (type) by name and general location.
     * @param query
     * @return
     */

    public Cursor searchShowsRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Shows' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Shows' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null, //COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on all attractions in the shopping category.
     * It allows the user to search within the shopping category (type) by name and general location.
     * @param query
     * @return
     */

    public Cursor searchShoppingRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_TYPE + "= 'Shopping' AND " + COL_NAME + " LIKE ? OR " + COL_TYPE + "= 'Shopping' AND " + COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null, //COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method returns a cursor from a query performed on all attractions marked as favorite.
     * It allows the user to search their list of favorite attractions by name, type (category), and general location.
     * @param query
     * @return
     */

    public Cursor searchFavoritesRows(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                TABLE_COLUMNS,
                COL_FAVORITE_STATUS + "= '" + MainActivity.FAVORITE + "' AND " + COL_TYPE + " LIKE ? OR " + COL_FAVORITE_STATUS + "= '" + MainActivity.FAVORITE + "' AND " + COL_NAME + " LIKE ? OR " + COL_FAVORITE_STATUS + "= '" + MainActivity.FAVORITE + "' AND "+ COL_GENERAL_LOCATION + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                null, //COL_TYPE,
                null,
                COL_NAME,
                null);
        return cursor;
    }

    /**
     * This method creates an instance of the Attraction object from a given row in the HPTable #TABLE_NAME
     * @param _id
     * @return
     */

    public Attraction createObject(int _id){
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
        return new Attraction(type, name, generalLocation, favoriteStatus, informationStringRID,logoImageRID, headerImageRID, mapImageRID);
    }
}
