package com.empresa.finanzas1.ui.registrar_movimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.empresa.finanzas1.R
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.modelo.Tipo_movimiento
import com.empresa.finanzas1.sqlite.MovimientoSqlite
import com.empresa.finanzas1.sqlite.TipoMovimientoSqlite
import kotlinx.android.synthetic.main.registrar_movimiento.*
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class RegistrarFragment : Fragment() {
    private lateinit var tipo_movimiento_spinner: Spinner
    private lateinit var varlo_textField: EditText
    private lateinit var registrarViewModel: RegistrarViewModel
    private lateinit var registrar_button: Button
    private lateinit var entrada_RadioButton: RadioButton
    private lateinit var gasto_RadioButton: RadioButton
    private lateinit var tipo_movimientos: ArrayList<Tipo_movimiento>
    private var root = view

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        registrarViewModel =
                ViewModelProviders.of(this).get(RegistrarViewModel::class.java)
        val root = inflater.inflate(R.layout.registrar_movimiento, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        tipo_movimiento_spinner = root.findViewById(R.id.tipo_movimiento_spinner)
        entrada_RadioButton = root.findViewById(R.id.entrada_RadioButton)
        gasto_RadioButton = root.findViewById(R.id.gasto_RadioButton)
        registrar_button = root.findViewById(R.id.registrar_button)
        registrarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        registrar_button.setOnClickListener {

            if(valor_textfield.text.toString().isEmpty()) {
                Toast.makeText(context, "Ingrese el valor del movimiento", Toast.LENGTH_LONG).show()
            } else {
                var movimientoRegistrar = Movimiento()
                try {
                    movimientoRegistrar.valorMovimiento = valor_textfield.text.toString().toDouble()
                    movimientoRegistrar.tipoMovimiento = tipo_movimiento_spinner.selectedItemPosition
                    movimientoRegistrar.flujo = if(entrada_RadioButton.isChecked) 1 else 2
                    val date = Calendar.getInstance()
                    val dateString = "${date.get(Calendar.YEAR)}-${date.get(Calendar.MONTH)}-${date.get(Calendar.DAY_OF_MONTH)}"
                    movimientoRegistrar.fechaMovimiento = dateString
                    if(MovimientoSqlite().agregar(context, movimientoRegistrar))
                    {
                        Toast.makeText(context, "Movimiento registrado con exito", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(context, "Error al registrar movimiento, consulte con el administrador", Toast.LENGTH_LONG).show()
                    }
                }catch (x: NumberFormatException)
                {

                }
            }
        }
        llenar_categorias()
        return root
    }

    private fun llenar_categorias() {
        tipo_movimientos = TipoMovimientoSqlite().consultar(requireContext())
        val lista = toArrayString()
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            lista!!
        )
        tipo_movimiento_spinner.adapter = arrayAdapter
        tipo_movimiento_spinner.setSelection(0)
    }

    fun toArrayString(): ArrayList<String>
    {
        var lista = ArrayList<String>()
        for(item in tipo_movimientos)
        {
            lista.add(item.nombreTipoMovimiento)
        }
        return lista
    }
}