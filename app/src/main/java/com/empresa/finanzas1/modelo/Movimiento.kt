package com.empresa.finanzas1.modelo

import java.util.*

class Movimiento {
    constructor()
    {

    }
    var idMovimiento = 0
    var valorMovimiento = 0.0
    var descripcion = ""
    var fechaMovimiento = ""
    var tipoMovimiento = 1
    var flujo = 1//1 entrada 2 salida

    companion object {
        const val ID_MOVIMIENTO = "id_movimiento"
        const val VALOR_MOVIMIENTO = "valor_movimiento"
        const val DESCRIPCION = "descripcion"
        const val FECHA_MOVIMIENTO = "fecha_movimiento"
        const val TIPO_MOVIMIENTO = "tipo_movimiento"
        const val FLUJO = "flujo"
    }
}