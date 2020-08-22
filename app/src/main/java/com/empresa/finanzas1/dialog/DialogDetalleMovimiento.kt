package com.empresa.finanzas1.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.empresa.finanzas1.R
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.modelo.Tipo_movimiento

class DialogDetalleMovimiento : DialogFragment() {

    var tipo_movimiento: Tipo_movimiento? = null
    lateinit var movimiento: Movimiento

    companion object{
        fun nueva_instancia(tipo_movimiento: Tipo_movimiento?, movimiento: Movimiento): DialogDetalleMovimiento
        {
            val detalle =  DialogDetalleMovimiento()
            detalle.tipo_movimiento = tipo_movimiento
            detalle.movimiento = movimiento
            return detalle
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = layoutInflater.inflate(R.layout.dialog_detalle_movimiento, null)
        val valor_textView :TextView = inflater.findViewById(R.id.valor_textView)
        val tipo_movimiento_textView :TextView = inflater.findViewById(R.id.tipo_movimiento_textView)
        val fecha_textView :TextView = inflater.findViewById(R.id.fecha_textView)
        valor_textView.text = "$ ${movimiento.valorMovimiento}"
        if(movimiento.flujo == 1)
        {
            valor_textView.setBackgroundResource(R.color.colorEntrada)
        }
        else
        {
            valor_textView.setBackgroundResource(R.color.colorGasto)
        }
        if(tipo_movimiento == null)
        {
            tipo_movimiento_textView.text = "###"
        }
        else
        {
            tipo_movimiento_textView.text = tipo_movimiento!!.nombreTipoMovimiento
        }
        fecha_textView.text = movimiento.fechaMovimiento
        builder.setView(inflater)
        return builder.create()
    }
}