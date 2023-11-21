package modelo.posta

import modelo.Competidor

trait Posta extends (List[Competidor] => List[Competidor])

trait Actividad extends ((Competidor, Competidor) => Boolean)

case object CrearPosta {
  def apply(criterio: Actividad, competidores: List[Competidor]): List[Competidor] = {
    competidores.sortWith((unCompetidor, otroCompetidor) => criterio(unCompetidor, otroCompetidor))
  }
}

case object CansancioPosta extends ((Posta) => Double) {
  def apply(posta: Posta): Double = {
    posta match {
      case Pesca => 5
      case Combate => 10
      case Carrera => 1
    }
  }
}

case object CansarVikingosPostPosta extends ((Posta, List[Competidor]) => List[Competidor]) {
  def apply(posta: Posta, competidores: List[Competidor]): List[Competidor] = {
    competidores.map(_.incrementarHambre(CansancioPosta(posta)))
  }
}
