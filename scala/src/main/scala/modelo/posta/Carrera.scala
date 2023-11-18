package modelo.posta

import modelo.Competidor

case object Carrera extends Posta {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    CrearPosta(EsMasRapido, competidores)
  }
}

case object EsMasRapido extends Actividad {
  def apply(unCompetidor: Competidor, otroCompetidor: Competidor): Boolean = {
    unCompetidor.velocidad() > otroCompetidor.velocidad()
  }
}
