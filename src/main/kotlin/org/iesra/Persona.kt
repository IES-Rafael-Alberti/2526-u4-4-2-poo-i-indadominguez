package org.iesra

class Persona(
    var peso: Double,
    var altura: Double
) {
    var nombre: String = ""

    val imc: Double
        get() = peso / (altura * altura)

    constructor(nombre: String, peso: Double, altura: Double) : this(peso, altura) {
        this.nombre = nombre
    }

    private enum class RangoIMC(val descripcion: String) {
        PESO_INSUFICIENTE("peso insuficiente"),
        PESO_SALUDABLE("peso saludable"),
        SOBREPESO("sobrepeso"),
        OBESIDAD("obesidad")
    }


    fun saludar(): String {
        return "Hola, soy $nombre"
    }

    fun alturaEncimaMedia(): Boolean {
        return altura >= 1.75
    }

    fun pesoEncimaMedia(): Boolean {
        return peso >= 70
    }

    private fun obtenerDescImc(): String {
        return when {
            imc < 18.5 -> RangoIMC.PESO_INSUFICIENTE.descripcion
            imc < 25.0 -> RangoIMC.PESO_SALUDABLE.descripcion
            imc < 30.0 -> RangoIMC.SOBREPESO.descripcion
            else -> RangoIMC.OBESIDAD.descripcion
        }
    }

    fun obtenerDesc(): String {
        val alturaDesc = if (alturaEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        val pesoDesc = if (pesoEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        return "$nombre con una altura de ${"%.2f".format(altura)}m ($alturaDesc) " +
                "y un peso ${"%.1f".format(peso)}kg ($pesoDesc) " +
                "tiene un IMC de ${"%.2f".format(imc)} (${obtenerDescImc()})"
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', peso=$peso, altura=$altura, imc=${"%.2f".format(imc)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Persona) return false
        return this.peso == other.peso && this.altura == other.altura
    }
}


fun main() {
    val personas = listOf(
        Persona("Julia", 64.7, 1.72),
        Persona("Indalecio", 70.0, 1.77),
        Persona("Guillermo", 86.4, 1.90),
        Persona("Ana", 55.0, 1.68),
        Persona("Carlos", 95.0, 1.82)
    )

    for (persona in personas) {
        println(persona.saludar())
        println(persona.obtenerDesc())
        println("------------------------------------------------")
    }
}
