package com.daylermorais.android.mynotes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.daylermorais.android.mynotes.data.MyNotesContract.SubjectEntry;

import java.sql.SQLException;

public class MyNotesProvider {

    private MyNotesDbHelper dbHelper;
    private Context ourContext;
    private SQLiteDatabase database;

    public MyNotesProvider(Context c){

        ourContext = c;
    }

    public MyNotesProvider open() throws SQLException{
        dbHelper = new MyNotesDbHelper(ourContext);
        database = dbHelper.getReadableDatabase();
        return this;
    }

    public void  close() {

        dbHelper.close();
    }

    // Getting Cursor to read data from table
    public Cursor readData() {
        String[] allColumns = new String[]{SubjectEntry._ID, SubjectEntry.COLUMN_SUBJECT_DESCRIPTION};
        Cursor c = database.query(SubjectEntry.TABLE_NAME, allColumns, null, null, null, null, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    //Inserting Data into Table
    public void insertSubject(String subject){
        ContentValues cv = new ContentValues();
        cv.put(SubjectEntry.COLUMN_SUBJECT_DESCRIPTION, subject);
        database.insert(SubjectEntry.TABLE_NAME, null, cv);
    }

    //Updating record data into table by id
    public int updateData(long subjectID, String subjectDescription) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(SubjectEntry.COLUMN_SUBJECT_DESCRIPTION, subjectDescription);
        int i = database.update(SubjectEntry.TABLE_NAME, cvUpdate,
                SubjectEntry._ID + " = " + subjectID, null);
        return i;
    }

    // Deleting record data from table by id
    public void deleteData(long memberID) {
        database.delete(SubjectEntry.TABLE_NAME, SubjectEntry._ID + "="
                + memberID, null);
    }
}
