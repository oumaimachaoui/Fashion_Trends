package com.example.oumaima.my_fragements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "card.db";
    public static final String TABLE_NAME = "produitcart";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "title";
    public static final String COL_3 = "description";
    public static final String COL_4 = "image";
    public static final String COL_5 = "ratings";
    public static final String COL_6 = "size_product";
    public static final String COL_7 = "color_product";
    public static final String COL_8 = "price";
    public static final String COL_10 = "user_id";
    public static final String COL_11 = "type_product";
    public static final String COL_12 = "Quantity";
    public DatabaseHelper3(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT,image TEXT,color_product TEXT,size_product TEXT,Quantity INTEGER,type_product TEXT,price real,ratings INTEGER,user_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String description, String image, String color_product, String size_product, String Quantity, String type_product, double price, int rating, String user_id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4, String.valueOf(image));
        contentValues.put(COL_5,rating);
        contentValues.put(COL_6,size_product);
        contentValues.put(COL_7,color_product);
        contentValues.put(COL_8,price);
        contentValues.put(COL_10,user_id);
        contentValues.put(COL_11,type_product);
        contentValues.put(COL_12,Quantity);
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
    public boolean updateDataqt(String quantity, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1,id);
        contentValues.put(COL_1,id);
        //contentValues.put(COL_3,surname);
        contentValues.put(COL_12,quantity);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {String.valueOf(id)});
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
