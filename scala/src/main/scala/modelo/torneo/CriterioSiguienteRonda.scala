package modelo.torneo

import modelo.competidor.Vikingo

trait CriterioSiguienteRonda extends (List[Vikingo] => List[Vikingo])

case object PrimeraMitadPasaDeRonda extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.take(competidores.length / 2)
  }
}

case object UltimaMitadPasaDeRonda extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.takeRight(competidores.length / 2)
  }
}