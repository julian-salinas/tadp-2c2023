package modelo.torneo

import modelo.competidor.Vikingo

abstract class CriterioSiguienteRonda extends (List[Vikingo] => List[Vikingo])

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

case class PrimerosNPasanDeRonda(n: Int) extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.take(n)
  }
}

