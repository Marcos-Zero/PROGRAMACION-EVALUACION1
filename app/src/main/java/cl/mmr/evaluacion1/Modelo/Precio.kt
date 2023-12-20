package cl.mmr.evaluacion1.Modelo

class ItemMenu(val nombre: String, val precio: Double)

class ItemMesa(val platillo: ItemMenu, var cantidad: Int = 0) {
    fun calcularSubtotal(): Double = cantidad * platillo.precio
}

class CuentaMesa {
    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000.0)
    private val cazuela = ItemMenu("Cazuela", 10000.0)

    private val listaPedidos = mutableListOf<ItemMesa>()

    fun agregarPedido(pedido: ItemMesa) {
        listaPedidos.add(pedido)
    }

    fun calcularTotalSinPropina(): Double {
        return listaPedidos.sumByDouble { it.calcularSubtotal() }
    }

    fun calcularPropina(): Double {
        return if (propinaActivada) {
            calcularTotalSinPropina() * 0.1
        } else {
            0.0
        }
    }

    fun calcularTotalConPropina(): Double {
        return calcularTotalSinPropina() + calcularPropina()
    }

    var propinaActivada: Boolean = true
}