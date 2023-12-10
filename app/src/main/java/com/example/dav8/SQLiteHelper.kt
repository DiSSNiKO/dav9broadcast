package com.example.dav8

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val queryOne = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                MAIL_COL + " TEXT," +
                NUM_COL + " TEXT," +
                LOC_COL + " TEXT" + ")")
        val queryBroadcast = ("CREATE TABLE " + BROADCAST_TABLE + " ("
                + ID_BR_COL + " INTEGER PRIMARY KEY, " +
                LOG_COL + " TEXT"
                + ")")
        // we are calling sqlite
        // method for executing our query
        db.execSQL(queryOne)
        db.execSQL(queryBroadcast)
    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + BROADCAST_TABLE)
        onCreate(db)
    }
    // This method is for adding data in our database
    fun addItem(name : String, email : String, num : String, loc : String ){
        // below we are creating
        // a content values variable
        val values = ContentValues()
        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(MAIL_COL, email)
        values.put(NUM_COL, num)
        values.put(LOC_COL, loc)
        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase
        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)
        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
    fun getBroadcastData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + BROADCAST_TABLE, null)
    }
    fun addBroadcastLog(log : String){
        val values = ContentValues()

        values.put(LOG_COL, log)

        val db = this.writableDatabase

        db.insert(BROADCAST_TABLE, null, values)

        db.close()
    }
    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "davdeebee"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "addables"

        //DAV 9 TABLE
        val BROADCAST_TABLE = "broadcast_log"
        val LOG_COL = "broadcast_details"
        val ID_BR_COL = "id"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"
        // below is the variable for age column
        val MAIL_COL = "email"
        val NUM_COL = "number"
        val LOC_COL = "location"

    }
}