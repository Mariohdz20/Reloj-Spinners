package edu.iest.androidwear

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.androidwear.databinding.ActivityRelojitoBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRelojitoBinding
    private var textoSeleccionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelojitoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSaludo.text = "HOLA AMIGUITO"
        binding.bnCambio.text = "Enviar"


        val adaptador = ArrayAdapter.createFromResource(this,
            R.array.misOpciones, android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.spOpciones.adapter = adaptador
        binding.spOpciones.onItemSelectedListener = this

        binding.bnCambio.setOnClickListener{
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atención, advertencia")
                .setMessage("Quiere enviar el saludo $textoSeleccionado")
                .setCancelable(false)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener{ dialogInterface, i ->
                        //aquí el codigo
                        binding.tvSaludo.text = textoSeleccionado
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener{dialogInterface, i ->
                        Toast.makeText(this, "Una lastima :,C", Toast.LENGTH_SHORT).show()
                    })
                .show()
        }

    }



    override fun onItemSelected(vistaPadre: AdapterView<*>?, vistaRow: View?, posicion: Int, idVista: Long) {
        textoSeleccionado =
            vistaPadre?.getItemAtPosition(posicion).toString()
        Toast.makeText(this,"Elegiste $textoSeleccionado", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}