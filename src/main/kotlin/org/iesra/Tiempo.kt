
package org.iesra

class Tiempo(h: Int, m: Int = 0, s: Int = 0) {

    var hora: Int
        private set
    var minuto: Int
        private set
    var segundo: Int
        private set

    init {
        var totalSegundos = h * 3600 + m * 60 + s

        segundo = totalSegundos % 60
        totalSegundos /= 60
        minuto = totalSegundos % 60
        totalSegundos /= 60
        hora = totalSegundos

        require(hora in 0..23) { "La hora debe ser menor que 24" }
    }

    override fun toString(): String {
        return "${hora}h ${minuto}m ${segundo}s"
    }

    private fun totalSegundos(): Int {
        return hora * 3600 + minuto * 60 + segundo
    }

    private fun fromSegundos(ts: Int) {
        var total = ts
        segundo = total % 60
        total /= 60
        minuto = total % 60
        total /= 60
        hora = total
    }

    fun incrementar(t: Tiempo): Boolean {
        val suma = totalSegundos() + t.totalSegundos()
        if (suma > 23 * 3600 + 59 * 60 + 59) return false
        fromSegundos(suma)
        return true
    }

    fun decrementar(t: Tiempo): Boolean {
        val resta = totalSegundos() - t.totalSegundos()
        if (resta < 0) return false
        fromSegundos(resta)
        return true
    }

    fun comparar(t: Tiempo): Int {
        return totalSegundos().compareTo(t.totalSegundos())
    }

    fun copiar(): Tiempo {
        return Tiempo(hora, minuto, segundo)
    }

    fun copiar(t: Tiempo) {
        this.hora = t.hora
        this.minuto = t.minuto
        this.segundo = t.segundo
    }

    fun sumar(t: Tiempo): Tiempo? {
        val suma = totalSegundos() + t.totalSegundos()
        if (suma > 23 * 3600 + 59 * 60 + 59) return null
        val resultado = Tiempo(0, 0, 0)
        resultado.fromSegundos(suma)
        return resultado
    }

    fun restar(t: Tiempo): Tiempo? {
        val resta = totalSegundos() - t.totalSegundos()
        if (resta < 0) return null
        val resultado = Tiempo(0, 0, 0)
        resultado.fromSegundos(resta)
        return resultado
    }

    fun esMayorQue(t: Tiempo): Boolean {
        return totalSegundos() > t.totalSegundos()
    }

    fun esMenorQue(t: Tiempo): Boolean {
        return totalSegundos() < t.totalSegundos()
    }
}

fun main() {

    fun leerNumero(mensaje: String, valorPorDefecto: Int = 0): Int {
        print(mensaje)
        val linea = readLine()
        return if (linea.isNullOrBlank()) valorPorDefecto else linea.toInt()
    }

    println("Introduce el tiempo principal:")
    val h1 = leerNumero("Hora (0-23): ")
    val m1 = leerNumero("Minuto (0-59, opcional): ", 0)
    val s1 = leerNumero("Segundo (0-59, opcional): ", 0)

    val t1 = Tiempo(h1, m1, s1)
    println("Tiempo principal: $t1\n")

    println("Introduce otro tiempo para operaciones:")
    val h2 = leerNumero("Hora (0-23): ")
    val m2 = leerNumero("Minuto (0-59, opcional): ", 0)
    val s2 = leerNumero("Segundo (0-59, opcional): ", 0)

    val t2 = Tiempo(h2, m2, s2)
    println("Tiempo secundario: $t2\n")

    println("=== INCREMENTAR ===")
    if (t1.incrementar(t2)) println("Después de incrementar: $t1")
    else println("Error: no se puede incrementar más allá de 23:59:59")

    println("\n=== DECREMENTAR ===")
    if (t1.decrementar(t2)) println("Después de decrementar: $t1")
    else println("Error: no se puede decrementar por debajo de 00:00:00")

    println("\n=== COMPARAR ===")
    val cmp = t1.comparar(t2)
    when (cmp) {
        -1 -> println("$t1 es menor que $t2")
        0 -> println("$t1 es igual que $t2")
        1 -> println("$t1 es mayor que $t2")
    }

    println("\n=== COPIAR (nuevo objeto) ===")
    val copia = t1.copiar()
    println("Copia de t1: $copia")

    println("\n=== COPIAR (sobrescribir t1 con t2) ===")
    t1.copiar(t2)
    println("t1 ahora copia t2: $t1")

    println("\n=== SUMAR ===")
    val suma = t1.sumar(t2)
    if (suma != null) println("$t1 + $t2 = $suma")
    else println("Error: la suma supera 23:59:59")

    println("\n=== RESTAR ===")
    val resta = t1.restar(t2)
    if (resta != null) println("$t1 - $t2 = $resta")
    else println("Error: la resta sería menor que 00:00:00")

    println("\n=== ES MAYOR QUE ===")
    println("$t1 es mayor que $t2? ${t1.esMayorQue(t2)}")

    println("\n=== ES MENOR QUE ===")
    println("$t1 es menor que $t2? ${t1.esMenorQue(t2)}")
}
