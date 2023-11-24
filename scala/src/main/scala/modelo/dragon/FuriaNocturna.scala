package modelo.dragon

import modelo.competidor.Vikingo

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

  override def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    super.puedeSerMontadoPor(vikingo)
    // todo: && vikingo.item.isInstanceOf(SistemaDeVuelo) o algo así
  }

  override def velocidad(): Double = {
    super.velocidad() * 3
  }
}
