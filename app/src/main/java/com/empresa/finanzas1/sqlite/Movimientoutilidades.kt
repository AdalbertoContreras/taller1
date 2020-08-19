package com.empresa.finanzas1.sqlite

import com.empresa.finanzas1.modelo.Movimiento

interface Movimientoutilidades {
    val TABLE_NAME: String
        get() = "movimiento"

    val consulta: String
        get() = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Movimiento.ID_MOVIMIENTO + " DESC"

    val ultimo_abono: String
        get() = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Movimiento.ID_MOVIMIENTO + " DESC LIMIT 1"

    val drop_table: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"

    val SQL_CREATE_ENTRIES: String
        get() = "CREATE TABLE $TABLE_NAME "+
            "(${Movimiento.ID_MOVIMIENTO} INT PRIMARY KEY AUTOINCREMENT," +
            "(${Movimiento.VALOR_MOVIMIENTO} REAL NOT NULL, " +
            "(${Movimiento.DESCRIPCION} TEXT NOT NULL, " +
            "(${Movimiento.FECHA_MOVIMIENTO} NUMERIC NOT NULL, " +
            "(${Movimiento.TIPO_MOVIMIENTO} TEXT NOT NULL) "

    val CAMPOS : String
            get() = " (" +
            "${Movimiento.ID_MOVIMIENTO}, " +
            "${Movimiento.VALOR_MOVIMIENTO}, " +
            "${Movimiento.DESCRIPCION}, " +
            "${Movimiento.FECHA_MOVIMIENTO}, " +
            "${Movimiento.TIPO_MOVIMIENTO} ) "

    fun valores(modelo: Movimiento): String? {
        return "( ${modelo.idMovimiento}, " +
                "${modelo.valorMovimiento}, " +
                "'${modelo.descripcion}', " +
                "'${modelo.fechaMovimiento}', " +
                "'${modelo.tipoMovimiento})"
    }
}