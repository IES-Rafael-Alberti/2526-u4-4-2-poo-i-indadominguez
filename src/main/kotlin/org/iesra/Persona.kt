package org.iesra
import java.util.Scanner

class Persona(
    var peso: Double,
    var altura: Double
) {
    var nombre: String = ""
    val imc: Double
        get() = peso / (altura * altura) //

    constructor(nombre: String, peso: Double, altura: Double) : this(peso, altura) {
        this.nombre = nombre
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
    val scanner = Scanner(System.`in`)


    val persona1 = Persona(70.0, 1.75)
    val persona2 = Persona("Indalecio", 70.0, 1.77)
    val persona3 = Persona("Guillermo", 86.4, 1.90)


    println("Personas creadas:")
    println(persona1)
    println(persona2)
    println(persona3)
    println("------------------------------------------------")


    var nombre: String
    do {
        print("Introduce el nombre para la persona 1: ")
        nombre = scanner.nextLine()
    } while (nombre.isBlank())
    persona1.nombre = nombre
    println("Persona 1 actualizada:")
    println("Nombre: ${persona1.nombre}, Peso: ${persona1.peso}, Altura: ${persona1.altura}")
    println("------------------------------------------------")

    println("Persona 3 antes de modificar altura:")
    println("Peso: ${persona3.peso}, Altura: ${persona3.altura}, IMC: ${"%.2f".format(persona3.imc)}")

    persona3.altura = 1.80
    println("Persona 3 después de modificar altura:")
    println("Peso: ${persona3.peso}, Altura: ${persona3.altura}, IMC: ${"%.2f".format(persona3.imc)}")
    println("------------------------------------------------")

    persona2.altura = persona3.altura
    println("Persona 2 después de igualar altura a persona 3:")
    println(persona2)
    println("Persona 3:")
    println(persona3)
    println("------------------------------------------------")

    if (persona2 == persona3) {
        println("Persona 2 y Persona 3 son iguales.")
    } else {
        println("Persona 2 y Persona 3 NO son iguales.")
    }
}
