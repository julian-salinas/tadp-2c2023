package modelo.posta

import modelo.Competidor

case object Combate extends Posta {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    CrearPosta(PuedeHacerMasDanio, competidores)
  }
}

case object PuedeHacerMasDanio extends Actividad {
  def apply(unCompetidor: Competidor, otroCompetidor: Competidor): Boolean = {
    unCompetidor.danio() > otroCompetidor.danio()
    // todo: danio + danio item?
  }
}