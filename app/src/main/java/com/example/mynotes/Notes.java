package com.example.mynotes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Notes extends SQLiteOpenHelper {

    private static final String DB_Name ="MyNotes.db";
    private static final String DB_Table ="NotesT";
    private static final String Name="Notes";
    private static final String ID="ID";
    private static final String CT ="Create Table "+DB_Table+" ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+Name+" TEXT " + " )";

    public Notes(Context context){
        super(context , DB_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase ) {
        sqLiteDatabase.execSQL(CT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("Drop Table If EXists "+ DB_Table);

        onCreate(sqLiteDatabase);
    }
    public Boolean insertData(String name){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(Name ,name);
        long result = db.insert(DB_Table,null,contentValues);
        return  result !=-1;
    }
    public Cursor viewdata(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="Select * from "+ DB_Table;
        Cursor cursor =db.rawQuery(query,null);
        return cursor;
    }
    public Boolean deletedata(){
        SQLiteDatabase db =this.getWritableDatabase();
        long result = db.delete(DB_Table,null,null);
        return  result !=-1;
    }
}
