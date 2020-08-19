package com.empresa.finanzas1.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class SqliteInterfas {
    fun insert(
        context: Context?,
        TABLE_NAME: String?,
        columHack: String?,
        values: ContentValues?
    ): Boolean {
        val conexionSQLiteHelper = ConexionSQLiteHelper(context, null)
        val sqLiteDatabase = conexionSQLiteHelper.writableDatabase
        val respuesta = sqLiteDatabase.insert(TABLE_NAME, columHack, values)
        Log.d("sqliteInterfas", respuesta.toString() + "Agregado")
        sqLiteDatabase.close()
        return respuesta != -1L
    }

    fun select(
        context: Context?,
        consulta: String?
    ): Cursor {
        val conexionSQLiteHelper = ConexionSQLiteHelper(context, null)
        val sqLiteDatabase = conexionSQLiteHelper.writableDatabase
        return sqLiteDatabase.rawQuery(consulta, arrayOf())
    }

    fun update(
        context: Context?,
        TABLE_NAME: String?,
        values: ContentValues?,
        whereClause: String?,
        parametros: Array<String?>?
    ): Boolean {
        val conexionSQLiteHelper = ConexionSQLiteHelper(context, null)
        val sqLiteDatabase = conexionSQLiteHelper.writableDatabase
        val rowsAfectados = sqLiteDatabase.update(TABLE_NAME, values, whereClause, parametros)
        sqLiteDatabase.close()
        return rowsAfectados > 0
    }

    fun delete(
        context: Context?,
        TABLE_NAME: String?,
        whereClause: String?,
        parametros: Array<String?>?
    ): Boolean {
        val conexionSQLiteHelper = ConexionSQLiteHelper(context, null)
        val sqLiteDatabase = conexionSQLiteHelper.writableDatabase
        val rowsAfectados = sqLiteDatabase.delete(TABLE_NAME, whereClause, parametros)
        sqLiteDatabase.close()
        return rowsAfectados > 0
    }
}