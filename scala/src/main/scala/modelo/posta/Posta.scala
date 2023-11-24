package modelo.posta

import modelo.competidor.Competidor
import modelo.posta.Posta.criterioAdmisionNulo

class Posta(
             criterioPuntaje: Competidor => Double,
             criterioAdmision: Competidor => Boolean = criterioAdmisionNulo,
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

  def aplicar(competidores: List[Competidor]): List[Competidor] = {
    ordenarSegunResultado(competidores.filter(puedeParticipar)).map(efectos)
  }

}

object Posta {
  def criterioAdmisionNulo: Competidor => Boolean = _ => true
}