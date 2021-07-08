package com.example.loginactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbManager_Vault extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Vault";
    private static final String TABLE_CONTACTS = "data";
    private static final String KEY_ID = "id";
    private static final String APPLICATION = "application";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public dbManager_Vault(Context context) {
        super(context, DATABASE_NAME+".db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " + TABLE_CONTACTS + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + APPLICATION + " TEXT, "
                        + USERNAME + " TEXT, "
                        + PASSWORD + " TEXT "
                        + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    void addData(Vault_data_POJO data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APPLICATION,data.application);
        values.put(USERNAME,data.username);
        values.put(PASSWORD,data.password);
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    public List<Vault_data_POJO> getAllData() {
        List<Vault_data_POJO> passwordList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Vault_data_POJO contact = new Vault_data_POJO();
                contact.id = cursor.getString(cursor.getColumnIndex(KEY_ID));
                contact.username = cursor.getString(cursor.getColumnIndex(USERNAME));
                contact.application = cursor.getString(cursor.getColumnIndex(APPLICATION));
                contact.password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                passwordList.add(contact);
            } while (cursor.moveToNext());
        }
        return passwordList;
    }

    void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[] { String.valueOf(id) });
        db.close();
    }


}
