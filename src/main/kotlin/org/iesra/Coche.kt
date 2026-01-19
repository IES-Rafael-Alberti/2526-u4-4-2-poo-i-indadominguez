package org.iesra

class Coche(
    marca: String?,
    modelo: String?,
    val numeroCaballos: Int?,
    val numeroPuertas: Int?,
    val matricula: String?,
    color: String?
) {

    val marca: String
    val modelo: String

    var color: String = run {
        requireNotNull(color) { "El color no puede ser nulo" }
        color
    }
        set(value) {
            requireNotNull(value) { "El color no puede ser nulo" }
            field = value
        }

    init {
        require(!marca.isNullOrBlank()) { "La marca no puede ser nula ni vacía" }
        this.marca = marca.substring(0, 1).uppercase() + marca.substring(1)

        require(!modelo.isNullOrBlank()) { "El modelo no puede ser nulo ni vacío" }
        this.modelo = modelo.substring(0, 1).uppercase() + modelo.substring(1)

        requireNotNull(numeroCaballos) { "El número de caballos no puede ser nulo" }
        require(numeroCaballos in 70..700)

        requireNotNull(numeroPuertas) { "El número de puertas no puede ser nulo" }
        require(numeroPuertas in 3..5)

        requireNotNull(matricula) { "La matrícula no puede ser nula" }
        require(matricula.length == 7)
    }

    override fun toString(): String {
        return "Coche(marca='$marca', modelo='$modelo', caballos=$numeroCaballos, puertas=$numeroPuertas, matrícula='$matricula', color='$color')"
    }
}


fun main() {

    // Creación de un coche correcto
    try {
        val coche1 = Coche(
            marca = "ford",
            modelo = "focus",
            numeroCaballos = 120,
            numeroPuertas = 5,
            matricula = "1234ABC",
            color = "Rojo"
        )
        println(coche1) // Se imprime correctamente
    } catch (ex: IllegalArgumentException) {
        println(ex.message)
    }

    // Intento de crear un coche con marca y modelo nulos o vacíos
    try {
        Coche(
            marca = null,
            modelo = "",
            numeroCaballos = 100,
            numeroPuertas = 5,
            matricula = "1234ABC",
            color = "Azul"
        )
    } catch (ex: IllegalArgumentException) {
        println(ex.message) // Se captura el error: marca/modelo no pueden ser nulos ni vacíos
    }

    // Intento de crear un coche con número de caballos fuera de rango (<70)
    try {
        Coche(
            marca = "Seat",
            modelo = "Ibiza",
            numeroCaballos = 50,
            numeroPuertas = 5,
            matricula = "2345BCD",
            color = "Blanco"
        )
    } catch (ex: IllegalArgumentException) {
        println(ex.message) // Se captura el error de caballos fuera de rango
    }

    // Intento de crear un coche con número de puertas fuera de rango (<3)
    try {
        Coche(
            marca = "Audi",
            modelo = "A3",
            numeroCaballos = 150,
            numeroPuertas = 2,
            matricula = "3456CDE",
            color = "Negro"
        )
    } catch (ex: IllegalArgumentException) {
        println(ex.message) // Se captura el error de puertas fuera de rango
    }

    // Intento de crear un coche con matrícula incorrecta (longitud != 7)
    try {
        Coche(
            marca = "BMW",
            modelo = "Serie1",
            numeroCaballos = 180,
            numeroPuertas = 5,
            matricula = "ABC123",
            color = "Gris"
        )
    } catch (ex: IllegalArgumentException) {
        println(ex.message) // Se captura el error de matrícula
    }

    // Creación de un coche correcto y prueba final
    try {
        val coche2 = Coche(
            marca = "Toyota",
            modelo = "Corolla",
            numeroCaballos = 140,
            numeroPuertas = 4,
            matricula = "4567DEF",
            color = "Verde"
        )
        println(coche2) // Se imprime correctamente
    } catch (ex: IllegalArgumentException) {
        println(ex.message)
    }
}
