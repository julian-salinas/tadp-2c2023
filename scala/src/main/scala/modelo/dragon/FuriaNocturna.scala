package modelo.dragon

import modelo.competidor.{SistemaDeVuelo, Vikingo}

class FuriaNocturna(
  velocidadBase: Double,
  danioBase: Double,
  peso: Double,
  barbarosidadNecesariaParaMontarlo: Double
  ) extends Dragon(
    velocidadBase,
    danioBase,
    peso,
    barbarosidadNecesariaParaMontarlo) {

  override def velocidad(): Double = {
    super.velocidad() * 3
  }
}

object Chimuelo extends FuriaNocturna(50, 20, 100, 80) {
  override def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    vikingo match {
      case Vikingo(_, _, _, Some(SistemaDeVuelo), _) => super.puedeSerMontadoPor(vikingo)
      case _ => false
    }
  }
}
