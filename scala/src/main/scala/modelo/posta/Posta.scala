package modelo.posta

import modelo.Competidor

trait Posta extends (List[Competidor] => List[Competidor])

trait Actividad extends ((Competidor, Competidor) => Boolean)

case object CrearPosta {
  def apply(criterio: Actividad, competidores: List[Competidor]): List[Competidor] = {
    competidores.sortWith((unCompetidor, otroCompetidor) => criterio(unCompetidor, otroCompetidor))
  }
}

case object ParticiparEnPosta extends ((Posta, List[Competidor]) => List[Competidor]) {
  def apply(posta: Posta, competidores: List[Competidor]): List[Competidor] = {
    val competidoresConHambreAumentado = posta match {
      case Pesca =>
        competidores.map(_.incrementarHambre(5))
      case Combate =>
        competidores.map(_.incrementarHambre(10))
      case Carrera =>
        competidores.map(_.incrementarHambre(1))
      case _ => competidores
    }
    posta(competidoresConHambreAumentado)
  }
}