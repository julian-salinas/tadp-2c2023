package modelo.torneo

import modelo.competidor.Competidor

trait CriterioGanador extends (List[Competidor] => Competidor)

case object Primero extends CriterioGanador {
  def apply(competidores: List[Competidor]): Competidor = {
    competidores.head
  }
}

case object Ultimo extends CriterioGanador {
  def apply(competidores: List[Competidor]): Competidor = {
    competidores.last
  }
}