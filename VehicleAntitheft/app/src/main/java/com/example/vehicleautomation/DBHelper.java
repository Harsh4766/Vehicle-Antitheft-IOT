package com.example.vehicleautomation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Userdb.db";
    public DBHelper(Context context) {
        super(context, "Userdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Userinfo(Name TEXT not null,Email TEXT not null,MobileNo INTEGER primary key not null,Password TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Userinfo");
    }

    public Boolean insertData(String name, String email,String number,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("MobileNo", number);
        contentValues.put("Password", password);
        long result = MyDB.insert("Userinfo", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }


    public Boolean checknumberpassword(String number, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Userinfo where MobileNo = ? and Password = ?", new String[] {number,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}