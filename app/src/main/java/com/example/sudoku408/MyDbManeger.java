package com.example.sudoku408;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class MyDbManeger {
    private Context context;
    private static DbHelper myDbHelper;
    private static SQLiteDatabase db;

//    Счетчик для добавления 100 пользователю в счет
    private String name_dbScore = "User";
    private int score_db = 4;

    final String LOG_TAG = "myLogs";

    public MyDbManeger(Context context) {
        this.context = context;
        myDbHelper = new DbHelper(context);
    }

    public static void openDb(){
        db = myDbHelper.getWritableDatabase();
    }
    public void insertToDb(String name, String score){
        ContentValues cv =  new ContentValues();
        cv.put(MyConstants.KEY_NAME, name);
        cv.put(MyConstants.KEY_SCORE, score);

        db.insert(MyConstants.TABLE_CONTACTS, null, cv);
    }

    public void updateToDb(String name, String score, String id){
        ContentValues cv =  new ContentValues();

        int updCount = db.update(MyConstants.TABLE_CONTACTS, cv, "id = 1", null);
        Log.d(LOG_TAG, "deleted rows count = " + updCount);

    }

    public void del_and_insert_db() {
        ContentValues cv = new ContentValues();
//      Код на удаление и добавление записи в бд

        MyConstants.test += 12;

        int delCount = db.delete(MyConstants.TABLE_CONTACTS, "id = " + Integer.toString(score_db), null);
        Log.d(LOG_TAG, "deleted rows count = " + delCount);

        cv.put(MyConstants.KEY_NAME, name_dbScore);
        cv.put(MyConstants.KEY_SCORE, MyConstants.test);
        db.insert(MyConstants.TABLE_CONTACTS, null, cv);

        score_db++;

        String b = String.valueOf(score_db);
        Log.d(LOG_TAG, b);
    }

    public List<String> getFromOb(){
        List<String> tempList = new ArrayList<>();

        Cursor cursor = db.query(
                MyConstants.TABLE_CONTACTS,
                null,
                null,
                null,
                null,
                null,
                MyConstants.KEY_SCORE + " DESC"
        );


        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(MyConstants.KEY_NAME));
            tempList.add(name);
        }
        cursor.close();
        return tempList;
    }

    public List<String> getFromScore(){
        List<String> tempList = new ArrayList<>();

        Cursor cursor = db.query(
                MyConstants.TABLE_CONTACTS,
                null,
                null,
                null,
                null,
                null,
                MyConstants.KEY_SCORE + " DESC" // ASC

        );

        while(cursor.moveToNext()){
            String score = cursor.getString(cursor.getColumnIndex(MyConstants.KEY_SCORE));
            tempList.add(score);
        }
        cursor.close();
        return tempList;
    }

    public static void closeDb(){
        myDbHelper.close();
    }
}
