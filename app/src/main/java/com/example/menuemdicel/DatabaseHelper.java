package com.example.menuemdicel;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "login.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(correo text primary key, contraseña text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    // insertar la base de datos
    public boolean insert (String correo, String contraseña){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("correo",correo);
        contentValues.put("contraseña",contraseña);
        long ins = db.insert("user",null,contentValues);
        if (ins ==-1)return false;
        else return true;
    }
    ///////////////////////////////////////////
    public Boolean chkcorreo(String correo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where correo=?", new String[]{correo});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    /////////comprobar el correo y la contraseña
    public Boolean correocontraseña(String correo, String contraseña){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where correo=? and contraseña=?", new String[]{correo,contraseña});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
