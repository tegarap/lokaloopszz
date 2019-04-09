package com.tegarap.lokaloops.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tegarap.lokaloops.models.Checkout

class DbHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "CHCKOUT.db"

        //Tabel
        private val TABLE_NAME = "Item"
        private val COL_ID = "Id"
        private val COL_NAME = "Item_Name"
        private val COL_PRICE = "Price"
        private val COL_QUANTITY = "Quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_PRICE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val checkoutItem:List<Checkout>
        get() {
            val checkoutyuk = ArrayList<Checkout>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor:Cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()){
                do {
                    val checkout = Checkout()
                    checkout.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    checkout.namaItem = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    checkout.price = cursor.getString(cursor.getColumnIndex(COL_PRICE))
                    checkout.quantity = cursor.getString(cursor.getColumnIndex(COL_QUANTITY))

                    checkoutyuk.add(checkout)
                } while (cursor.moveToNext())
            }
            return checkoutyuk
        }

    fun createCheckout(checkout: Checkout){
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, checkout.id)
        values.put(COL_NAME, checkout.namaItem)
        values.put(COL_PRICE, checkout.price)
        values.put(COL_QUANTITY, checkout.quantity)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateCheckout(checkout: Checkout):Int{
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, checkout.id)
        values.put(COL_NAME, checkout.namaItem)
        values.put(COL_PRICE, checkout.price)
        values.put(COL_QUANTITY, checkout.quantity)

        return db.update(TABLE_NAME, values, "$COL_ID", arrayOf(checkout.id.toString()))
    }

    fun deleteCheckout(checkout: Checkout){
        val db:SQLiteDatabase = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(checkout.id.toString()))
        db.close()
    }
}