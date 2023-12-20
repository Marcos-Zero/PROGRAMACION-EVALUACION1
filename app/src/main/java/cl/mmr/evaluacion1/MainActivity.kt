package cl.mmr.evaluacion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import cl.mmr.evaluacion1.Modelo.CuentaMesa
import cl.mmr.evaluacion1.Modelo.ItemMenu
import cl.mmr.evaluacion1.Modelo.ItemMesa
val formatoPeso = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("es", "CL"))

class MainActivity : AppCompatActivity() {

    private var calcularAutomatico: Switch? = null
    var etunidad1: EditText? = null
    var etunidad2: EditText? = null
    var tvpreciopastel: TextView? = null
    var tvpreciocazuela: TextView? = null
    var tvtotalSinPropina: TextView? = null
    var tvmontopropina: TextView? = null
    var tvtotalConPropina: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        calcularAutomatico = findViewById<Switch>(R.id.calcularAutomatico)
        etunidad1 = findViewById<EditText>(R.id.etunidad1)
        etunidad2 = findViewById<EditText>(R.id.etunidad2)
        tvpreciopastel = findViewById<TextView>(R.id.tvpreciopastel)
        tvpreciocazuela = findViewById<TextView>(R.id.tvpreciocazuela)
        tvtotalSinPropina = findViewById<TextView>(R.id.tvtotalSinPropina)
        tvmontopropina = findViewById<TextView>(R.id.tvmontopropina)
        tvtotalConPropina = findViewById<TextView>(R.id.tvtotalConPropina)


        calcularAutomatico?.setOnClickListener {
            val cuentaMesa = CuentaMesa()
            val etunidad1 = etunidad1?.text.toString().toIntOrNull() ?: 0
            val etunidad2 = etunidad2?.text.toString().toIntOrNull() ?: 0
            val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000.0)
            val cazuela = ItemMenu("Cazuela", 10000.0)

            // Agregar los pedidos a la cuenta
            cuentaMesa.agregarPedido(ItemMesa(pastelDeChoclo, etunidad1))
            cuentaMesa.agregarPedido(ItemMesa(cazuela, etunidad2))

            // Calcular totales
            val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
            val propina = cuentaMesa.calcularPropina()
            val totalConPropina = cuentaMesa.calcularTotalConPropina()

            // Mostrar los resultados en los TextView
            tvtotalSinPropina?.text = formatoPeso.format(totalSinPropina)
            tvmontopropina?.text = formatoPeso.format(propina)
            tvtotalConPropina?.text = formatoPeso.format(totalConPropina)
            tvpreciopastel?.text = formatoPeso.format(pastelDeChoclo.precio)
            tvpreciocazuela?.text = formatoPeso.format(cazuela.precio)
        }
    }

}



