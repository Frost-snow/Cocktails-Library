package com.CocktailsLibrary.DataAccess.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 27.03.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test";

    public static final String TABLE_NAME = "users";
    public static final String LOGIN = "CocktailsLibraryUser";
    public static final String PASSW = "C0CkTailSlIbRaryPaSsW0Rd";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
            + LOGIN + " TEXT, " + PASSW + " TEXT)";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}