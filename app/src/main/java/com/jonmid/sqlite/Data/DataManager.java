package com.jonmid.sqlite.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jonmid.sqlite.Helpers.HelperDataBase;
import com.jonmid.sqlite.Models.ListPost;
import com.jonmid.sqlite.Models.Post;
import com.jonmid.sqlite.Models.User;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    public DataManager(Context context){
        dbHelper = new HelperDataBase(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void createUserPost(User user, Post post){

        ContentValues valuesUser = new ContentValues();
        valuesUser.put(HelperDataBase.COLUMN_U_NAME, user.getName());
        valuesUser.put(HelperDataBase.COLUMN_U_EMAIL, user.getEmail());
        long insertIdUser = database.insert(HelperDataBase.TABLE_USERS, null, valuesUser);

        ContentValues valuesPost = new ContentValues();
        valuesPost.put(HelperDataBase.COLUMN_P_TITLE, post.getTitle());
        valuesPost.put(HelperDataBase.COLUMN_P_STATUS, post.isStatus());
        long insertIdPost = database.insert(HelperDataBase.TABLE_POSTS, null, valuesPost);

        ContentValues valuesUserPost = new ContentValues();
        valuesUserPost.put(HelperDataBase.COLUMN_UP_IDUSER, insertIdUser);
        valuesUserPost.put(HelperDataBase.COLUMN_UP_IDPOST, insertIdPost);
        long insertIdUserPost = database.insert(HelperDataBase.TABLE_USERPOST, null, valuesUserPost);

    }

    public List<ListPost> cursorToList(Cursor cursor){
        List<ListPost> posts = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                ListPost listPost = new ListPost();
                listPost.setId(cursor.getLong(cursor.getColumnIndex("id")));
                listPost.setName_user(cursor.getString(cursor.getColumnIndex("name")));
                listPost.setTitle_post(cursor.getString(cursor.getColumnIndex("email")));

                posts.add(listPost);
            }
        }
        return posts;
    }

    public List<ListPost> findAll(){
        String MY_QUERY = "select u.id,u.name,u.email from users u inner join userpost up on u.id = up.id_user";
        // String MY_QUERY = "select u.id,u.name,u.email from users u inner join userpost up inner join posts p on up.id_post = p.id on u.id = up.id_user where u.id = 1";

        Cursor cursor = database.rawQuery(MY_QUERY, null);
        List<ListPost> misPost = cursorToList(cursor);
        return misPost;
    }
}
