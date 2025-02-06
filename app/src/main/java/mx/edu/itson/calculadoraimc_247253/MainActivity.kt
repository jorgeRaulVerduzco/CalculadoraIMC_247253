package mx.edu.itson.calculadoraimc_247253

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etPeso: EditText
    private lateinit var etEstatura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Asignación de variables a los componentes del layout
        etPeso = findViewById(R.id.etPeso)
        etEstatura = findViewById(R.id.etEstatura)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)  // Asegúrate de agregar un TextView en el XML para mostrar el resultado

        // Evento para el botón
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
           }

    //funcion imc
    private fun calcularIMC() {
        val peso = etPeso.text.toString().toFloatOrNull()
        val estatura = etEstatura.text.toString().toFloatOrNull()

        if (peso != null && estatura != null && estatura != 0f) {
            val imc = peso / (estatura * estatura)
            mostrarResultado(imc)
        } else {
            tvResultado.text = "Ingresa valores validos"
        }
    }

    private fun mostrarResultado(imc: Float) {
        val mensaje = when {
            imc < 18.5 -> "Bajo peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }
        tvResultado.text = "IMC: ${"%.2f".format(imc)}\n$mensaje"
    }
}