package com.example.heyfoodie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USERINFOTABLE = "userinfotable";
    static String Databasename="usersinfo.db";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String GENDER = "gender";
    public static final String PHONE_NO = "phoneno";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public DataBaseHelper(Context context){
        super(context,Databasename,null,1);
    }

    public boolean isloginvalid(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT " + EMAIL + " , " + PASSWORD + " FROM " + USERINFOTABLE + " WHERE " + EMAIL + " = ?  AND " + PASSWORD + " = ? ", new String[]{ email, password } );
        if(c.getCount() <= 0) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public boolean insertuser(String firstnamevalue,String lastnamevalue,String phonenovalue,String addressvalue,String emailvalue,String passwordvalue, String gendervalue){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, firstnamevalue);
        contentValues.put(LAST_NAME, lastnamevalue);
        contentValues.put(GENDER, gendervalue);
        contentValues.put(PHONE_NO, phonenovalue);
        contentValues.put(ADDRESS, addressvalue);
        contentValues.put(EMAIL, emailvalue);
        contentValues.put(PASSWORD, passwordvalue);

        long result=db.insert(USERINFOTABLE,"",contentValues);
        db.close();
        return result != -1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + " " + USERINFOTABLE + " " + " (" + ID + " INTEGER primary key autoincrement ," + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT,"
                + GENDER + " TEXT," + PHONE_NO + " TEXT," + ADDRESS + " TEXT," + EMAIL + " TEXT," + PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVesion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERINFOTABLE );
        onCreate(db);
    }
}