package modelo.torneo

import modelo.competidor.Vikingo

trait CriterioSiguienteRonda extends (List[Vikingo] => List[Vikingo])

case object PrimeraMitad extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.take(competidores.length / 2)
  }
}

case object UltimaMitad extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.takeRight(competidores.length / 2)
  }
}