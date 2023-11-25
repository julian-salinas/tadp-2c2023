package modelo.torneo

import modelo.competidor.Vikingo

trait CriterioGanador extends (List[Vikingo] => Option[GanadorTorneo])

case object PrimeroGanador extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[GanadorTorneo] = {
    competidores.headOption
  }
}

case object UltimoGanador extends CriterioGanador {
  def apply(competidores: List[Vikingo]): Option[GanadorTorneo] = {
    competidores.lastOption
  }
}