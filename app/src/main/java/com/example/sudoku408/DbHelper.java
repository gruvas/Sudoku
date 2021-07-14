package com.example.sudoku408;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "King_of_DataBas";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SCORE = "score";
    Context mContext;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            DATABASE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
            KEY_SCORE + "INTEGER";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DATABASE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("+ KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_NAME + " text," + KEY_SCORE + " integer" + ")");
        ContentValues values = new ContentValues();
        values.put(KEY_ID, "1"); values.put(KEY_NAME, "MixBee"); values.put(KEY_SCORE, "120");
        db.insert(TABLE_CONTACTS, null, values);
        values.put(KEY_ID, "2"); values.put(KEY_NAME, "Elite"); values.put(KEY_SCORE, "1120");
        db.insert(TABLE_CONTACTS, null, values);
        values.put(KEY_ID, "3"); values.put(KEY_NAME, "WindKing"); values.put(KEY_SCORE, "12");
        db.insert(TABLE_CONTACTS, null, values);
        values.put(KEY_ID, "4"); values.put(KEY_NAME, "Princess"); values.put(KEY_SCORE, "998");
        db.insert(TABLE_CONTACTS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        onCreate(db);
    }
}