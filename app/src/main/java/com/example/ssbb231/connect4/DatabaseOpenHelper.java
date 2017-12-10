package com.example.ssbb231.connect4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created ST on /2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final static String TABLE_NAME = "leaderboard";
    final static String ITEM = "item";
    final static String _ID = "_id";


    final private static String CREATE_CMD =
            "CREATE TABLE leaderboard (" + _ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +ITEM + " TEXT)";

    final private static String NAME = "todo_db";
    final private static Integer VERSION = 2;
    final private Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    void deleteDatabase ( ) {
        context.deleteDatabase(NAME);
    }
}
