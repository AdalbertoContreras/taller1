package com.empresa.finanzas1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.empresa.finanzas1.R
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.modelo.Tipo_movimiento

class ItemMovimiento : RecyclerView.Adapter<ItemMovimiento.ViewHolder> {
    private lateinit var lista : ArrayList<Movimiento>
    private var posicion : Int = 0
    private lateinit var view : View
    constructor(lista: ArrayList<Movimiento>)
    {
        this.lista = lista
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_movimiento, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setdatos(lista.get(position))
    }

    class ViewHolder : RecyclerView.ViewHolder {

        lateinit var itemView : View
        lateinit var valorMovimiento : TextView
        lateinit var tipoMovimiento : TextView
        constructor(itemView : View): super(itemView)
        {
            valorMovimiento = itemView.findViewById(R.id.valorMovimiento)
            tipoMovimiento = itemView.findViewById(R.id.tipoMovimiento)
        }

        fun setdatos(movimiento: Movimiento)
        {
            valorMovimiento.text = "${movimiento.valorMovimiento}"
        }

    }


}