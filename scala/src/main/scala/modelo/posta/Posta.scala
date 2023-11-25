package modelo.posta

import modelo.competidor.Competidor

class Posta(
             criterioPuntaje: Competidor => Double,
             criterioAdmision: Competidor => Boolean = _ => true,
             efectos: Competidor => Competidor) {
  def ordenarSegunResultado (competidores: List[Competidor]): List[Competidor] = {
    competidores.sortWith((a, b) => esMejorQue(a, b))
  }

  def esMejorQue(unCompetidor: Competidor, otroCompetidor: Competidor): Boolean = {
    obtenerPuntaje(unCompetidor) > obtenerPuntaje(otroCompetidor)
  }

  def obtenerPuntaje(competidor: Competidor): Double = {
    criterioPuntaje(competidor)
  }

  def puedeParticipar(competidor: Competidor): Boolean = {
    criterioAdmision(competidor) && efectos(competidor).hambre() < 100
  }

  def competir(competidores: List[Competidor]): List[Competidor] = {
    ordenarSegunResultado(competidores.filter(puedeParticipar)).map(efectos)
  }

}
