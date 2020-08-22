package com.empresa.finanzas1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.empresa.finanzas1.R
import com.empresa.finanzas1.dialog.DialogDetalleMovimiento
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.modelo.Tipo_movimiento
import com.empresa.finanzas1.sqlite.TipoMovimientoSqlite

class ItemMovimiento : RecyclerView.Adapter<ItemMovimiento.ViewHolder> {
    private lateinit var lista : ArrayList<Movimiento>
    private var posicion : Int = 0
    public lateinit var view : View
    lateinit var manager: FragmentManager
    constructor(lista: ArrayList<Movimiento>, manager: FragmentManager)
    {
        this.manager = manager
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
        holder.setdatos(lista.get(position), view.context, manager)
    }

    class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        lateinit var itemViewGlobal : View
        lateinit var valorMovimiento : TextView
        lateinit var tipoMovimiento : TextView
        var tipo_movimiento: Tipo_movimiento? = null
        lateinit var movimiento: Movimiento
        lateinit var manager: FragmentManager
        constructor(itemView : View): super(itemView)
        {
            this.itemViewGlobal = itemView
            valorMovimiento = itemView.findViewById(R.id.valorMovimiento)
            tipoMovimiento = itemView.findViewById(R.id.tipoMovimiento)
            itemView.setOnClickListener(this)
        }

        fun setdatos(movimiento: Movimiento, context: Context, manager: FragmentManager)
        {
            this.manager = manager
            this.movimiento = movimiento
            valorMovimiento.text = "${movimiento.valorMovimiento}"
            tipo_movimiento = TipoMovimientoSqlite().ConsultarPorIdTipoMovimiento(context, movimiento.tipoMovimiento)
            if(tipo_movimiento != null)
            {
                tipoMovimiento.text = tipo_movimiento!!.nombreTipoMovimiento
            }
            else
            {
                tipoMovimiento.text = "###"
            }
            val contenedor: LinearLayout = itemViewGlobal.findViewById(R.id.contenedor)
            if(movimiento.flujo == 1)
            {
                contenedor.setBackgroundResource(R.color.colorEntrada)
            }
            else
            {
                contenedor.setBackgroundResource(R.color.colorGasto)
            }
        }

        override fun onClick(v: View?) {
            val detalle: DialogDetalleMovimiento = DialogDetalleMovimiento.nueva_instancia(tipo_movimiento, movimiento)
            detalle.show(this.manager, "dialog")
        }
    }


}