package com.jonmid.sqlite.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperDataBase extends SQLiteOpenHelper {

    private static final String LOGTAG = "LOGTAG";
    private static final String DATABASE_NAME = "mypost";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_U_ID = "id";
    public static final String COLUMN_U_NAME = "name";
    public static final String COLUMN_U_EMAIL = "email";

    public static final String TABLE_POSTS = "posts";
    public static final String COLUMN_P_ID = "id";
    public static final String COLUMN_P_TITLE = "title";
    public static final String COLUMN_P_STATUS = "status";

    public static final String TABLE_USERPOST = "userpost";
    public static final String COLUMN_UP_ID = "id";
    public static final String COLUMN_UP_IDUSER = "id_user";
    public static final String COLUMN_UP_IDPOST = "id_post";

    public static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_U_NAME + " TEXT, " +
                    COLUMN_U_EMAIL + " TEXT" +
                    ")";
    public static final String TABLE_CREATE_POSTS =
            "CREATE TABLE " + TABLE_POSTS + " (" +
                    COLUMN_P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_P_TITLE + " TEXT, " +
                    COLUMN_P_STATUS + " TEXT" +
                    ")";
    public static final String TABLE_CREATE_USERPOST =
            "CREATE TABLE " + TABLE_USERPOST + " (" +
                    COLUMN_UP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_UP_IDUSER + " INTEGER, " +
                    COLUMN_UP_IDPOST + " INTEGER" +
                    ")";


    public HelperDataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_POSTS);
        db.execSQL(TABLE_CREATE_USERPOST);
        Log.i(LOGTAG, "Las tablas se crearon correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_USERS);
        db.execSQL("DROP TABLE IF EXIST "+TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXIST "+TABLE_USERPOST);
        onCreate(db);
    }
}
