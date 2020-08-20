package com.empresa.finanzas1.ui.vista_general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.empresa.finanzas1.R
import com.empresa.finanzas1.sqlite.MovimientoSqlite

class VistaGeneralFragment : Fragment() {

    private lateinit var vistaGeneralViewModel: VistaGeneralViewModel
    private lateinit var valor_total_textView: TextView
    private lateinit var total_entrada_textView: TextView
    private lateinit var total_gasto_textView: TextView
    private lateinit var root: View
    var valorTotal = 0.0
    var valorGasto = 0.0
    var valorEntrada = 0.0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        vistaGeneralViewModel =
                ViewModelProviders.of(this).get(VistaGeneralViewModel::class.java)
        root = inflater.inflate(R.layout.vista_general, container, false)
        iniciarVariables()
        consultarMovimientos()
        imprimir()
        return root
    }

    fun iniciarVariables()
    {
        val textView: TextView = root.findViewById(R.id.text_home)
        vistaGeneralViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        valor_total_textView = root.findViewById(R.id.valor_total_textView)
        total_entrada_textView = root.findViewById(R.id.total_entrada_textView)
        total_gasto_textView = root.findViewById(R.id.total_gasto_textView)
    }

    fun consultarMovimientos()
    {
        valorTotal = 0.0
        valorGasto = 0.0
        valorEntrada = 0.0
        var movimientos = MovimientoSqlite().consultar(context)
        for(item in movimientos!!.iterator())
        {
            valorTotal += item.valorMovimiento
            if(item.flujo == 1)
            {
                valorEntrada += item.valorMovimiento
            }
            else
            {
                valorGasto += item.valorMovimiento
            }
        }
    }

    fun imprimir()
    {
        valor_total_textView.text = String.format("$ %,.0f", valorTotal)
        total_gasto_textView.text = String.format("$ %,.0f", valorGasto)
        total_entrada_textView.text = String.format("$ %,.0f", valorEntrada)
    }
}