package com.example.crudd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperData(private var context: Context): SQLiteOpenHelper(context, DB_NAME,null,
    DB_VERSION) {
    companion object{
        private const val DB_NAME="Edugaon"
        private const val DB_VERSION=1
        private const val TABLE_NAME="Students"
        private const val COL_ID="id"
        private const val COL_NAME="name"
        private const val COL_EMAIL="email"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val createQuery="CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME TEXT,$COL_EMAIL TEXT)"
        p0?.execSQL(createQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val drop="DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(drop)
        onCreate(p0)
    }
    fun insertDataFunction(userModelClass: userMOdelClass){
        val db=writableDatabase
        val value= ContentValues().apply {
            put("name",userModelClass.name)
            put("email",userModelClass.email)
        }
        db.insert(TABLE_NAME,null,value)
    }

    fun viewData():ArrayList<userMOdelClass>{
        val db=writableDatabase
        val array= arrayListOf<userMOdelClass>()
        val selectQuery="SELECT * FROM $TABLE_NAME"
        val x=db.rawQuery(selectQuery,null)

        while (x.moveToNext()){
            val id=x.getInt(x.getColumnIndexOrThrow(COL_ID))
            val name=x.getString(x.getColumnIndexOrThrow(COL_NAME))
            val email=x.getString(x.getColumnIndexOrThrow(COL_EMAIL))

            val model=userMOdelClass(id,name,email)
            array.add(model)
        }
        return array
    }

    fun deletedItemUser(id:Int){
        val array= arrayOf(id.toString())
        val db=writableDatabase
        val where="$COL_ID = ?"
        db.delete(TABLE_NAME,where,array)
    }
    fun updateUserItem(userModelClass: userMOdelClass){
        val db=writableDatabase
        val where="$COL_ID = ?"
        val array= arrayOf(userModelClass.id.toString())
        val value=ContentValues().apply {
            put(COL_ID,userModelClass.id)
            put(COL_NAME,userModelClass.name)
            put(COL_EMAIL,userModelClass.email)
        }
        db.update(TABLE_NAME,value,where,array)
    }
}