package com.example.oumaima.lastproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper4 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "card.db";
    public static final String TABLE_NAME = "produitpay";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "livraison";
    public static final String COL_3 = "adress";
    public static final String COL_4 = "ville";
    public static final String COL_5 = "Card_number";
    public static final String COL_6 = "Card_expiry_date";
    public static final String COL_7 = "Card_CVV";
    public static final String COL_8 = "Postal_code";
    public static final String COL_10 ="Phone_number";
    public DatabaseHelper4(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,livraison TEXT,adress TEXT,ville TEXT,Card_number TEXT,Card_expiry_date TEXT,Card_CVV TEXT,Postal_code TEXT,Phone_number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String livraison, String adress, String ville){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,livraison);
        contentValues.put(COL_3,adress);
        contentValues.put(COL_4,ville);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getDatauser(String[] title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where NAME =?",new String[]{String.valueOf(title)});
        return res;
    }
    //$sql = 'SELECT SUM(price*Quantity) as totalPrix FROM product where add_card=1';
   public Cursor gettotal() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select sum(price*Quantity) as totalPrix from "+TABLE_NAME,null);
        return res;
    }
   /*  */
    public boolean updateData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "name = ?",new String[] { name });
        return true;
    }

    public Integer deleteData (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {String.valueOf(id)});
    }
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COL_1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_3 + " = ?";

        // selection arguments
        String[] selectionArgs = {email};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
