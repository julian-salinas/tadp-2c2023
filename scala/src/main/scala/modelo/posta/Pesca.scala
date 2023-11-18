package modelo.posta

import modelo.{Competidor, Vikingo}

case object Pesca extends Posta {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    CrearPosta(PuedeLevantarMas, competidores)
  }
}

case object PuedeLevantarMas extends Actividad {
  def apply(unCompetidor: Competidor, otroCompetidor: Competidor): Boolean = {
    unCompetidor.cantidadDePescadoQuePuedeTransportar() > otroCompetidor.cantidadDePescadoQuePuedeTransportar()
  }
}