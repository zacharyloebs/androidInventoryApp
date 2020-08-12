package com.example.projecttwo;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;

import Model.Items;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name
    public static final String DATABASE_NAME = "inventory.db";

    // Table for user names and passwords
    public static final String TABLE_USERS = "users";
    // Columns for table
    public static final String COL_ID_USERS = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    // Table for items and quantity
    public static final String TABLE_INVENTORY = "inventory";
    // Columns for table
    public static final String COL_ID_INVENTORY = "id";
    public static final String COL_ITEM = "item";
    public static final String COL_QUANTITY = "quantity";

    // Create users table
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + " (" + COL_ID_USERS + " INTEGER PRIMARY KEY, "
            + COL_USERNAME + " TEXT unique,"
            + COL_PASSWORD + " TEXT" + ") ";

    //Create inventory table
    private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE "
            + TABLE_INVENTORY + " (" + COL_ID_INVENTORY + " INTEGER PRIMARY KEY, "
            + COL_ITEM + " TEXT unique,"
            + COL_QUANTITY + " TEXT" + ") ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_INVENTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        onCreate(db);
    }

    public boolean createUsers(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, user);
        contentValues.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null,contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUser(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Select * from " + TABLE_USERS + " where " + COL_USERNAME + " = " + "'" + user + '\'' + " and " + COL_PASSWORD + " = " + "'" + pass + '\'';
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean createItem(String item, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ITEM, item);
        contentValues.put(COL_QUANTITY, quantity);

        long result = db.insert(TABLE_INVENTORY, null,contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Items> getAllData() {

        ArrayList<Items> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM inventory", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            Items items = new Items(name, age);

            arrayList.add(items);
        }

        return arrayList;
    }
}
