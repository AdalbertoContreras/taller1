package com.empresa.finanzas1.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.modelo.Tipo_movimiento
import java.util.ArrayList

class TipoMovimientoSqlite: TipoMovimientoUtilidades {
    fun agregar(context: Context?, movimiento: Tipo_movimiento?): Boolean {
        val values: ContentValues = toContentvalues(movimiento)
        return SqliteInterfas().insert(context, TABLE_NAME, Movimiento.ID_MOVIMIENTO, values)
    }

    fun consultar(context: Context?): ArrayList<Tipo_movimiento> {
        val cursor = SqliteInterfas().select(context, consulta)
        return recorrerCursor(cursor)
    }

    fun ConsultarPorIdTipoMovimiento(context: Context?, id: Int): Tipo_movimiento? {
        val cursor = SqliteInterfas().select(context, porIdTipoMovimiento(id))
        val lista = recorrerCursor(cursor)
        if(lista.isEmpty())
        {
            return null
        }
        else
        {
            return lista[0]
        }
    }

    fun consultarPorNombre(context: Context?, nombre: String): ArrayList<Tipo_movimiento> {
        val cursor = SqliteInterfas().select(context, porNombre(nombre))
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

    private fun recorrerCursor(cursor: Cursor): ArrayList<Tipo_movimiento> {
        val tipo_movimientos: ArrayList<Tipo_movimiento> = ArrayList<Tipo_movimiento>()
        if (cursor.moveToFirst()) {
            do {
                /*
                Convierto mi cursor a una clase tipo_movimiento
                 */
                val abono: Tipo_movimiento = cursorToTipoMovimiento(cursor)
                tipo_movimientos.add(abono)
            } while (cursor.moveToNext())
        }
        return tipo_movimientos
    }

    private fun toContentvalues(modelo: Tipo_movimiento?): ContentValues {
        val values = ContentValues()
        if(modelo != null)
        {
            values.put(Tipo_movimiento.NOMBRE_TIPO_MOVIMIENTO, modelo.nombreTipoMovimiento)
        }
        return values
    }

    private  fun cursorToTipoMovimiento(cursor: Cursor): Tipo_movimiento {
        val modelo = Tipo_movimiento()
        modelo.idTipoMovimiento = cursor.getInt(0)
        modelo.nombreTipoMovimiento = cursor.getString(1)
        return modelo
    }
}