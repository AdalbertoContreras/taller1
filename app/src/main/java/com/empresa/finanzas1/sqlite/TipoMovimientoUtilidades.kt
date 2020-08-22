package com.empresa.finanzas1.sqlite

import com.empresa.finanzas1.modelo.Tipo_movimiento

interface TipoMovimientoUtilidades {
    val TABLE_NAME: String
        get() = "tipo_movimiento"

    val consulta: String
        get() = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Tipo_movimiento.ID_TIPO_MOVIMIENTO + " ASC"

    fun porIdTipoMovimiento(id: Int) : String
    {
        return "SELECT * FROM " + TABLE_NAME + " where ${Tipo_movimiento.ID_TIPO_MOVIMIENTO} = $id"
    }

    fun porNombre(nombre: String) : String
    {
        return "SELECT * FROM " + TABLE_NAME + " where ${Tipo_movimiento.NOMBRE_TIPO_MOVIMIENTO} = '$nombre'"
    }

    val drop_table: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"

    val SQL_CREATE_ENTRIES: String
        get() = "CREATE TABLE $TABLE_NAME "+
                "(${Tipo_movimiento.ID_TIPO_MOVIMIENTO} PRIMARY KEY," +
                "${Tipo_movimiento.NOMBRE_TIPO_MOVIMIENTO} REAL NOT NULL) "

    val CAMPOS : String
        get() = " (" +
                "${Tipo_movimiento.ID_TIPO_MOVIMIENTO}, " +
                "${Tipo_movimiento.NOMBRE_TIPO_MOVIMIENTO}) "

    fun valores(modelo: Tipo_movimiento): String? {
        return "( ${Tipo_movimiento.ID_TIPO_MOVIMIENTO}, " +
                "${Tipo_movimiento.NOMBRE_TIPO_MOVIMIENTO})"
    }
}