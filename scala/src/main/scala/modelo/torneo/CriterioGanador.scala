package modelo.torneo

import modelo.competidor.{Vikingo, Vikingo}

trait CriterioGanador extends (List[Vikingo] => Vikingo)

case object Primero extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Vikingo = {
    competidores.head
  }
}

case object Ultimo extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Vikingo = {
    competidores.last
  }
}