package com.example.sois.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper  extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME="healthcare.db";
    public static final String DATABASE_TABLE="details";
    public static final String C1="name";
    public static final String C2="pass";
    //public static final String C3="confirm";

    public static final String tname="patientdetails";
    public static final String detail_c1= "pid";
    public static final String detail_c2= "age";
    public static final String detail_c3= "gender";
    public static final String detail_c4= "mobile";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE details(name TEXT PRIMARY KEY,pass TEXT)");
        db.execSQL("CREATE TABLE patientdetails(pid Text,age integer,gender Text,mobile integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS details");
        db.execSQL("DROP TABLE IF EXISTS patientdetails");
        onCreate(db);

    }
    public boolean insertData(String name ,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content= new ContentValues();

        content.put(C1,name);
        content.put(C2,pass);
        //content.put(C3,confirm);



        long res =db.insert(DATABASE_TABLE,null,content);
        if(res == -1)
            return  false;
        else
            return  true;




    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+DATABASE_TABLE,null);
        return res;
    }

    //public boolean checkid(String id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cs = db.rawQuery("select * from details where id=?", new String[]{id});
//        if (cs.getCount() > 1)
//            return false;
//        else
//            return true;
//
//    }
    public boolean validate(String name, String pass) {
        Log.v("validate", name);
        Log.v("validate", pass);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from details where name=? and pass=?", new String[]{name, pass});
        if (cs.getCount() > 0)
            return true;
        else
            return false;

    }


    public String fetchByID(String id) {
        String sql = "SELECT "+C1+" FROM "+DATABASE_TABLE+" WHERE "+C2+"='"+id+"' LIMIT 1";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            cursor.close();
            return name;
        } else {
            cursor.close();
            return null;
        }
    }

    public boolean insertDataIntoPd(String pid, int age, String gender, int mobile) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(detail_c1,pid);
      values.put(detail_c2,age);
      values.put(detail_c3,gender);
      values.put(detail_c4,mobile);
      db.insert("patientdetails",null,values);
      db.close();
      return true;
    }

    public String fetchBypatientId(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from patientdetails where pid=?", new String[]{ id });
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            String name = cursor.getString(0)+"\n" + cursor.getString(1) +"\n"+cursor.getString(2)+ "\n";
            cursor.close();
            return name;
        }else {
            cursor.close();
            return null;
        }
    }
}
