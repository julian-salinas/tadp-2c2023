package modelo.torneo

import modelo.competidor.Vikingo

trait CriterioGanador extends (List[Vikingo] => Option[Vikingo])

case object PrimeroGanador extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[Vikingo] = {
    competidores.headOption
  }
}

case object UltimoGanador extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[Vikingo] = {
    competidores.lastOption
  }
}