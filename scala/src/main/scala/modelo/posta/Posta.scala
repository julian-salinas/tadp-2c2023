package modelo.posta

import modelo.{Competidor, Vikingo}

trait Posta extends (List[Competidor] => List[Competidor])

trait Actividad extends ((Competidor, Competidor) => Boolean)

case object CrearPosta {
  def apply(criterio: Actividad, competidores: List[Competidor]): List[Competidor] = {
    competidores.sortWith((unCompetidor, otroCompetidor) => criterio(unCompetidor, otroCompetidor))
  }
}

case object ParticiparEnPosta extends Posta {
  def apply(competidores: List[Competidor]): List[Competidor] = {
    ??? // map con cansancio segun la posta
  }
}