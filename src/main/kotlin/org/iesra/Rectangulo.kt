package org.iesra

class Rectangulo(
    val base: Int,
    val altura: Int) {

    init {
        require(base > 0) {"La base deberá ser mayor que 0"}
        require(altura > 0) {"La altura deberá ser mayor que 0"}
    }

    fun area(): Int = base * altura

    fun perimetro(): Int = 2 * (base + altura)

    override fun toString(): String {
        return "Rectángulo(Base: $base, altura: $altura"
    }
}

fun main() {
    val r1 = Rectangulo(4, 5)
    val r2 = Rectangulo(6, 3)
    val r3 = Rectangulo(2, 2)

    val rectangulos = listOf(r1, r2, r3)

    for (r in rectangulos) {
        println(r)
        println("Área: ${r.area()}")
        println("Perímetro: ${r.perimetro()}")
        println("-------------------")
    }
}