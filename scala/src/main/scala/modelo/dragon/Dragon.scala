package modelo.dragon

import modelo.competidor.{SistemaDeVuelo, Vikingo}
import modelo.dragon.RequisitosMontura.RequisitoMontura

/* [Correción] Este modelo no contempla la capacidad de los dragones deberian tener de definir sus condiciones
    de montura al definirlos, adicional a la condicion obligatoria o las condiciones de la especie.

 */

case class Dragon (
                    velocidadBase: Double = 60.0,
                    danioBase: Double,
                    peso: Double,
                    barbarosidadNecesariaParaMontarlo: Double,
                    requisitosAdicionalesParaMontarlo: List[RequisitoMontura] = RequisitosMontura.SinRequisitosAdicionales
                  ) {

  def puedeSerMontadoPor(vikingo: Vikingo): Boolean = {
    vikingo.barbarosidad > barbarosidadNecesariaParaMontarlo &&
      vikingo.peso <= pesoMaximoCarga() &&
      requisitosAdicionalesParaMontarlo.forall(requisito => requisito(this, vikingo))
  }

  def pesoMaximoCarga(): Double = {
    peso * 0.2
  }

  def danio(): Double = danioBase

  def velocidad(): Double = {
    velocidadBase - peso
  }
}

object RequisitosMontura {
  type RequisitoMontura = (Dragon, Vikingo) => Boolean

  val SinRequisitosAdicionales: List[RequisitoMontura] = List((_, _) => true)
  object VikingoDebeTenerSistemaDeVuelo extends (RequisitoMontura) {
    def apply(dragon: Dragon, vikingo: Vikingo): Boolean = {
      vikingo match {
        case Vikingo(_, _, _, Some(SistemaDeVuelo), _, _) => true
        case _ => false
      }
    }
  }

  object VikingoHaceMenosDanioQueDragon extends ((Dragon, Vikingo) => Boolean) {
    def apply(dragon: Dragon, vikingo: Vikingo): Boolean = vikingo.danio < dragon.danio
  }
}