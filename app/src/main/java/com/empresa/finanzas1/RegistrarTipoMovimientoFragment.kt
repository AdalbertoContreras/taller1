package com.empresa.finanzas1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.empresa.finanzas1.modelo.Tipo_movimiento
import com.empresa.finanzas1.sqlite.TipoMovimientoSqlite

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrarTipoMovimientoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrarTipoMovimientoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var nombre_tipo : EditText
    lateinit var regitrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_registrar_tipo_movimiento, container, false)
        nombre_tipo = root.findViewById(R.id.nomre_tipo_movimiento_EditText)
        regitrar = root.findViewById(R.id.registrar_button)
        regitrar.setOnClickListener{
            if(nombre_tipo.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Ingrese el nombre de tipo de movimiento", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val tipo_movimientos = TipoMovimientoSqlite().consultarPorNombre(requireContext(), nombre_tipo.text.toString())
                if(tipo_movimientos.isEmpty())
                {
                    var modelo : Tipo_movimiento = Tipo_movimiento()
                    modelo.nombreTipoMovimiento = nombre_tipo.text.toString()
                    val registrado = TipoMovimientoSqlite().agregar(requireContext(), modelo)
                    if(registrado)
                    {
                        Toast.makeText(requireContext(), "Tipo de movimiento registrado", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(requireContext(), "Error al registrar tipo de movimiento", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(requireContext(), "Ya existe un tipo de movimiento con este nombre", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrarTipoMovimientoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrarTipoMovimientoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}