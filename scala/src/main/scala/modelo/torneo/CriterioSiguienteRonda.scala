package modelo.torneo

import modelo.competidor.Competidor

trait CriterioSiguienteRonda extends (List[Competidor] => List[Competidor])

case object PrimeraMitad extends CriterioSiguienteRonda {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    competidores.take(competidores.length / 2)
  }
}

case object UltimaMitad extends CriterioSiguienteRonda {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    competidores.takeRight(competidores.length / 2)
  }
}