package modelo.torneo

import modelo.competidor.Vikingo

abstract class CriterioSiguienteRonda extends (List[Vikingo] => List[Vikingo])

case object PrimeraMitadPasaDeRonda extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.take((competidores.length.toFloat / 2).ceil.toInt)
  }
}

case object UltimaMitadPasaDeRonda extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.takeRight((competidores.length.toFloat / 2).ceil.toInt)
  }
}

case class UltimosNSeQuedanAfuera(n: Int) extends CriterioSiguienteRonda {
  def apply(competidores: List[Vikingo]): List[Vikingo] = {
    competidores.dropRight(n)
  }
}