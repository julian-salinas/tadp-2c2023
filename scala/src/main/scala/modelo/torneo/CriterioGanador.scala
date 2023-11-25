package modelo.torneo

import modelo.competidor.Vikingo

trait CriterioGanador extends (List[Vikingo] => Option[Vikingo])

case object Primero extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[Vikingo] = {
    competidores.headOption
  }
}

case object Ultimo extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[Vikingo] = {
    competidores.lastOption
  }
}