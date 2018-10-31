package com.example.ani.buddayremind_v_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "Birth.db";
    public static final String Table_Name = "friends";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Contact_no";
    public static final String COL_4 = "DOB";
    public static final String COL_5 = "Time";
    public static final String COL_6 = "Alarm";



    public DatabaseHelper(Context c){
        super(c,DatabaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+Table_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name Text,Contact_no Text,DOB Text,Time Text,Alarm Text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
    }
    public boolean insertData(String name,String contact_no,String dob,String time,String alarm){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,contact_no);
        cv.put(COL_4,dob);
        cv.put(COL_5,time);
        cv.put(COL_6,alarm);
        long result=db.insert(Table_Name,null,cv);
        db.close();
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getAlldata(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result=db.rawQuery("Select * from "+Table_Name,null);
        // db.close();
        return result;
    }
    public Cursor getSingledata(String ID){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result=db.rawQuery("Select * from "+Table_Name+" where ID="+ID,null);
        // db.close();
        return result;
    }

    public Integer deleteData(String ID) {
        SQLiteDatabase db= this.getWritableDatabase();
        int i=db.delete(Table_Name,"ID=?",new String[]{ID});
        db.close();
        return i;
    }

    public boolean updateData(String ID,String name,String contact_no,String dob,String time,String alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, contact_no);
        cv.put(COL_4,dob);
        cv.put(COL_5,time);
        cv.put(COL_6,alarm);
        int result = db.update(Table_Name, cv, "ID=?", new String[]{ID});
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}