package com.empresa.finanzas1.ui.consultarMovimientosPorFecha

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empresa.finanzas1.R
import com.empresa.finanzas1.adapter.DatePikerFragment
import com.empresa.finanzas1.adapter.ItemMovimiento
import com.empresa.finanzas1.modelo.Movimiento
import com.empresa.finanzas1.sqlite.MovimientoSqlite


class consultarMovimientosPorFechaFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var fechaInicial : EditText
    private lateinit var fechaFinal : EditText
    private lateinit var listaRecycler : RecyclerView
    private lateinit var listaMovimientos : ArrayList<Movimiento>
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_consulta_por_fecha, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        fechaInicial = root.findViewById(R.id.fechaInicio)
        fechaFinal = root.findViewById(R.id.fechaFinal)
        listaRecycler = root.findViewById(R.id.movimientoRecycler)
        fechaInicial.setOnClickListener{
            val newFragment: DatePikerFragment =
                DatePikerFragment.newInstance(OnDateSetListener { datePicker, year, month, day -> // +1 because January is zero
                    val selectedDate : String = "$year-${(if(month < 10) "0" + (month + 1) else (month + 1))}-${(if(day < 10) "0" + day else day)}"
                    fechaInicial.text = Editable.Factory.getInstance().newEditable(selectedDate)
                    consultarMovimientos()
                })

            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }
        fechaFinal.setOnClickListener{
            val newFragment: DatePikerFragment =
                DatePikerFragment.newInstance(OnDateSetListener { datePicker, year, month, day -> // +1 because January is zero
                    val selectedDate : String = "$year-${(if(month < 10) "0" + (month + 1) else (month + 1))}-${(if(day < 10) "0" + day else day)}"
                    fechaFinal.text = Editable.Factory.getInstance().newEditable(selectedDate)
                    consultarMovimientos()
                })

            newFragment.show(requireActivity().supportFragmentManager, "datePicker")

        }
        return root
    }

    fun consultarMovimientos()
    {
        listaMovimientos = MovimientoSqlite().movimientosporRangoDeFecha(requireContext(), fechaInicial.text.toString(), fechaFinal.text.toString())
        //listaMovimientos = MovimientoSqlite().consultar(requireContext())
        listaRecycler.layoutManager = GridLayoutManager(requireContext(), 1)
        var ItemMovimientoAdapter = ItemMovimiento(listaMovimientos, requireActivity().supportFragmentManager)
        listaRecycler.adapter = ItemMovimientoAdapter
    }
}