package modelo.posta

// corregido? el pesoMinimo se defaultea a 0, por lo que la condición se invalida
// [Corrección] condicion no cumple que el peso minimo sea opcional
case class Pesca(
                pesoMinimo: Double = 0.0
                ) extends Posta(
      criterioPuntaje = competidor => competidor.cantidadDePescadoQuePuedeTransportar(),
      efectos = competidor => competidor.incrementarHambrePosta(5),
      criterioAdmision = competidor => competidor.cantidadDePescadoQuePuedeTransportar() > pesoMinimo //TODO test
    ) {}