package com.empresa.finanzas1.sqlite

import android.util.Log
import com.empresa.finanzas1.modelo.Movimiento

interface Movimientoutilidades {
    val TABLE_NAME: String
        get() = "movimiento"

    val consulta: String
        get() = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Movimiento.ID_MOVIMIENTO + " ASC"

    fun porFlujo(flujo: Int): String? {
        return "SELECT * FROM " + TABLE_NAME + " where " + Movimiento.FLUJO + "=" + flujo + " ORDER BY " + Movimiento.ID_MOVIMIENTO + " DESC"
    }

    fun porRangoDeFecha(fechaA: String, fechaB: String): String? {
        val consulta = "SELECT * FROM $TABLE_NAME where date(${Movimiento.FECHA_MOVIMIENTO}) >= date('$fechaA') AND date(${Movimiento.FECHA_MOVIMIENTO}) <= date('$fechaB') ORDER BY ${Movimiento.ID_MOVIMIENTO} DESC"
        return consulta
    }

    fun porFecha(fechaA: String): String? {
        val consulta = "SELECT * FROM $TABLE_NAME where date(${Movimiento.FECHA_MOVIMIENTO}) = date('$fechaA') ORDER BY ${Movimiento.ID_MOVIMIENTO} DESC"
        Log.d("Consulta", consulta)
        return consulta
    }

    val drop_table: String
        get() = "DROP TABLE IF EXISTS $TABLE_NAME"

    val SQL_CREATE_ENTRIES: String
        get() = "CREATE TABLE $TABLE_NAME "+
            "(${Movimiento.ID_MOVIMIENTO} PRIMARY KEY," +
            "${Movimiento.VALOR_MOVIMIENTO} REAL NOT NULL, " +
            "${Movimiento.DESCRIPCION} TEXT NOT NULL, " +
            "${Movimiento.FECHA_MOVIMIENTO} TEXT NOT NULL, " +
            "${Movimiento.TIPO_MOVIMIENTO} INT NOT NULL, " +
            "${Movimiento.FLUJO} INT NOT NULL) "

    val CAMPOS : String
            get() = " (" +
            "${Movimiento.ID_MOVIMIENTO}, " +
            "${Movimiento.VALOR_MOVIMIENTO}, " +
            "${Movimiento.DESCRIPCION}, " +
            "${Movimiento.FECHA_MOVIMIENTO}, " +
            "${Movimiento.TIPO_MOVIMIENTO},"+
            "${Movimiento.FLUJO}) "

    fun valores(modelo: Movimiento): String? {
        return "( ${modelo.idMovimiento}, " +
                "${modelo.valorMovimiento}, " +
                "'${modelo.descripcion}', " +
                "'${modelo.fechaMovimiento}', " +
                "'${modelo.tipoMovimiento}, " +
                "'${modelo.flujo})"
    }
}