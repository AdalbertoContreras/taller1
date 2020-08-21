package com.empresa.finanzas1.sqlite

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ConexionSQLiteHelper
/**
 * Create a helper object to create, open, and/or manage a database.
 * This method always returns very quickly.  The database is not actually
 * created or opened until one of [.getWritableDatabase] or
 * [.getReadableDatabase] is called.
 *
 * @param context to use for locating paths to the the database
 * @param factory to use for creating cursor objects, or null for the default
 * [.onUpgrade] will be used to upgrade the database; if the database is
 * newer, [.onDowngrade] will be used to downgrade the database
 */
    (context: Context?, factory: CursorFactory?) : SQLiteOpenHelper(
    context,
    nombre_database,
    factory,
    version_database
) {
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    override fun onCreate(db: SQLiteDatabase) {
        try {
            /*
            Crear tablas
             */
            db.execSQL(MovimientoSqlite().SQL_CREATE_ENTRIES)
            db.execSQL(TipoMovimientoSqlite().SQL_CREATE_ENTRIES)
            var consultas = listOf("insert into movimiento values(1, 12500, '', '2020-08-01', 1, 1)",
                "insert into movimiento values(2, 12500, '', '2020-08-01', 1, 2)",
                "insert into movimiento values(3, 12500, '', '2020-08-01', 2, 1)",
                "insert into movimiento values(4, 12500, '', '2020-08-02', 3, 1)",
                "insert into movimiento values(5, 12500, '', '2020-08-08', 3, 1)",
                "insert into movimiento values(6, 12500, '', '2020-08-08', 2, 1)",
                "insert into movimiento values(7, 12500, '', '2020-08-15', 2, 2)",
                "insert into movimiento values(8, 12500, '', '2020-08-17', 1, 2)",
                "insert into movimiento values(9, 12500, '', '2020-08-20', 1, 2)",
                "insert into movimiento values(10, 12500, '', '2020-08-20', 2, 2)",
                "insert into movimiento values(11, 12500, '', '2020-08-20', 2, 1)",
                "insert into movimiento values(12, 12500, '', '2020-08-20', 1, 2)",
                "insert into tipo_movimiento values(1, 'Transporte')",
                "insert into tipo_movimiento values(2, 'Alimento')",
                "insert into tipo_movimiento values(3, 'Ropa')",
                "insert into tipo_movimiento values(4, 'Deuda')")
            for(consulta in consultas)
            {
                db.execSQL(consulta)
            }
        } catch (e: SQLException) {
            Log.d("sqlite", "base de datos no creada")
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     *
     *
     * The SQLite ALTER TABLE documentation can be found
     * [here](http://sqlite.org/lang_altertable.html). If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     *
     *
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     *
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        /*
        Borrar tablas
         */
        db.execSQL(MovimientoSqlite().drop_table)
        db.execSQL(TipoMovimientoSqlite().drop_table)
        /*
        Crear tablas
         */
        onCreate(db)
    }

    companion object {
        private const val nombre_database = "app_finanza"
        private const val version_database = 11
    }
}