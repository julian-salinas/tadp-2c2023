package modelo.posta


// Clase ya que puede cambiar el criterio de admisión como se enuncia al final de la hoja 3/5 del enunciado

case class Pesca(
                pesoMinimo: Double = 0.0
                ) extends Posta(
      criterioPuntaje = competidor => competidor.cantidadDePescadoQuePuedeTransportar(),
      efectos = competidor => competidor.incrementarHambrePosta(5),
      criterioAdmision = competidor => competidor.cantidadDePescadoQuePuedeTransportar() > pesoMinimo //TODO test
    ) {}