package modelo.dragon

import modelo.competidor.Vikingo
import modelo.dragon.RequisitosMontura.{RequisitoMontura, SinRequisitosAdicionales}

class Gronckle(
                     velocidadBase: Double = 60.0,
                     danioBase: Double,
                     peso: Double,
                     barbarosidadNecesariaParaMontarlo: Double,
                     pesoQuePuedeSoportar: Double,
                     requisitosAdicionalesParaMontarlo: List[RequisitoMontura] = SinRequisitosAdicionales
                   ) extends Dragon(
  velocidadBase,
  danioBase,
  peso,
  barbarosidadNecesariaParaMontarlo,
  requisitosAdicionalesParaMontarlo) {

  override def danio(): Double = {
    peso * 5
  }

  override def velocidad(): Double = {
    super.velocidad() / 2
  }

  override def pesoMaximoCarga(): Double = pesoQuePuedeSoportar
}
