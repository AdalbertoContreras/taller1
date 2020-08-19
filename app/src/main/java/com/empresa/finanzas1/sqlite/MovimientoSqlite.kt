package com.empresa.finanzas1.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.empresa.finanzas1.modelo.Movimiento
import java.util.*

class MovimientoSqlite : Movimientoutilidades {
    fun agregar(context: Context?, movimiento: Movimiento?): Boolean {
        val values: ContentValues = toContentvalues(movimiento)
        return SqliteInterfas().insert(context, TABLE_NAME, Movimiento.ID_MOVIMIENTO, values)
    }

    fun consultar(context: Context?): ArrayList<Movimiento>? {
        val cursor = SqliteInterfas().select(context, consulta)
        return recorrerCursor(cursor)
    }

    fun borrar(context: Context?, modelo: Movimiento): Boolean {
        val parametros =
            arrayOf<String?>(modelo.idMovimiento.toString() + "")
        return SqliteInterfas().delete(
            context,
            TABLE_NAME,
            Movimiento.ID_MOVIMIENTO + "=?",
            parametros
        )
    }

    fun borrarTodo(context: Context?): Boolean {
        return SqliteInterfas().delete(context, TABLE_NAME, "", null)
    }

    private fun recorrerCursor(cursor: Cursor): ArrayList<Movimiento> {
        val abonos: ArrayList<Movimiento> = ArrayList<Movimiento>()
        if (cursor.moveToFirst()) {
            do {
                val abono: Movimiento = cursorToCobrador(cursor)
                abonos.add(abono)
            } while (cursor.moveToNext())
        }
        return abonos
    }

    private fun toContentvalues(modelo: Movimiento?): ContentValues {
        val values = ContentValues()
        if(modelo != null)
        {
            values.put(Movimiento.ID_MOVIMIENTO, modelo.idMovimiento)
            values.put(Movimiento.VALOR_MOVIMIENTO, modelo.valorMovimiento)
            values.put(Movimiento.DESCRIPCION, modelo.descripcion)
            values.put(Movimiento.FECHA_MOVIMIENTO, modelo.fechaMovimiento)
            values.put(Movimiento.TIPO_MOVIMIENTO, modelo.tipoMovimiento)
        }
        return values
    }

    private  fun cursorToCobrador(cursor: Cursor): Movimiento {
        val modelo = Movimiento()
        modelo.idMovimiento = cursor.getInt(0)
        modelo.valorMovimiento = cursor.getDouble(1)
        modelo.descripcion = cursor.getString(2)
        modelo.fechaMovimiento = cursor.getString(3)
        modelo.tipoMovimiento = cursor.getInt(4)
        return modelo
    }
}