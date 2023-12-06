package modelo.dragon

import modelo.competidor.{SistemaDeVuelo, Vikingo}
import modelo.dragon.RequisitosMontura.{RequisitoMontura, SinRequisitosAdicionales, VikingoDebeTenerSistemaDeVuelo}

class FuriaNocturna(
  velocidadBase: Double,
  danioBase: Double,
  peso: Double,
  barbarosidadNecesariaParaMontarlo: Double,
  requisitosAdicionalesParaMontarlo: List[RequisitoMontura] = SinRequisitosAdicionales
  ) extends Dragon(
    velocidadBase,
    danioBase,
    peso,
    barbarosidadNecesariaParaMontarlo,
    requisitosAdicionalesParaMontarlo: List[RequisitoMontura]) {

  override def velocidad(): Double = {
    super.velocidad() * 3
  }
}

object Chimuelo extends FuriaNocturna(
  50,
  20,
  100,
  80,
  List(RequisitosMontura.VikingoDebeTenerSistemaDeVuelo)
) {
}
