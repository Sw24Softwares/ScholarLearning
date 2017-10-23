package org.sw24softwares.scholarlearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "results.db";
        public static final String COLUMN_1 = "DATA";
        public static final String COLUMN_2 = "MARKS";
        public String table_name = "marks";
        
        public DatabaseHelper(Context context, String name) {
                super(context, DATABASE_NAME, null, 1);
                
                if (table_name != null)
                        table_name = name;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                String createTable =
                        "CREATE TABLE " + table_name + " (" +
                        "ID INTEGER PRIMARY KEY," +
                        COLUMN_1 + " TEXT," +
                        COLUMN_2 + " TEXT)";

                db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP IF TABLE EXISTS " + table_name);
                onCreate(db);
        }

        public boolean addData(ContentValues contentValues){
                SQLiteDatabase db = this.getWritableDatabase();

                long result = db.insert(table_name, null, contentValues);

                //if date as inserted incorrectly it will return -1
                if (result == -1)
                        return false;
                else
                        return true;
        }

        public Cursor getListContents(){
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor data = db.rawQuery("SELECT * FROM " + table_name, null);
                return data;
        }

}
